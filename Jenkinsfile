pipeline {
    // Definimos el agente que ejecutará el pipeline.
    agent {
        docker { image 'maven:3.9.6-eclipse-temurin-21' }
    }

    tools {
        // Nos aseguramos de tener SonarQube Scanner configurado en Manage Jenkins > Global Tool Configuration
        // con el nombre 'SonarScanner'
        jdk 'jdk17'
        maven 'maven3'
    }

    environment {
        // El nombre del servidor SonarQube que configuramos en Manage Jenkins > Configure System
        SONAR_SERVER = 'SonarQube'
        // El nombre de la credencial que contiene el token de SonarQube
        SONAR_LOGIN = credentials('jenkins')
    }

    stages {
        stage('Checkout') {
            steps {
                // Clona el código de tu repositorio Git
                echo 'Clonando el repositorio...'
                checkout scm
            }
        }

        stage('Compile & Test with Coverage') {
            steps {
                // Compila, ejecuta pruebas y genera el informe de cobertura con JaCoCo.
                // Usamos 'install' para asegurar que todos los pasos del ciclo de vida se ejecuten.
                // El comando 'prepare-agent' de JaCoCo se ejecuta automáticamente.
                echo 'Compilando y ejecutando pruebas...'
                sh './mvnw clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Ejecuta el análisis de SonarQube
                echo 'Iniciando análisis de SonarQube...'
                withSonarQubeEnv(SONAR_SERVER) {
                    sh """
                        ./mvnw sonar:sonar \
                          -Dsonar.projectKey=banking-api \
                          -Dsonar.host.url=http://sonarqube:9000 \
                          -Dsonar.login=${SONAR_LOGIN}
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                // Espera el resultado del análisis de SonarQube y falla el pipeline si no cumple.
                // El timeout es por si SonarQube tarda en procesar el informe.
                echo 'Esperando por el Quality Gate de SonarQube...'
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Docker Image') {
            // Este paso necesita acceso al demonio de Docker.
            // Lo ejecutamos en un agente diferente que tenga Docker instalado.
            agent any // Usa el agente principal de Jenkins, que tiene acceso al socket de Docker.

            steps {
                echo 'Construyendo la imagen Docker...'
                // Usamos el build number de Jenkins como tag para un versionado único.
                script {
                    def imageName = "banking-api:${env.BUILD_NUMBER}"
                    docker.build(imageName, '.')
                }
            }
        }

        stage('Deploy Application') {
            agent any
            steps {
                echo 'Desplegando la aplicación con Docker Compose...'
                // Ejecuta tu docker-compose.yml para levantar la app y la BBDD.
                // Esto detendrá y reemplazará cualquier versión anterior que esté corriendo.
                sh 'docker-compose -f docker-compose.yml up -d --build banking-api'
            }
        }
    }

    post {
        // Siempre, al final del pipeline (falle o no), limpia el workspace.
        always {
            cleanWs()
        }
    }
}