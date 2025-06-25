pipeline {
    // Definimos el agente que ejecutará el pipeline.
    // Esta imagen ya contiene JDK 21 y Maven.
    agent {
        docker { image 'maven:3.9.6-eclipse-temurin-21' }
    }

    environment {
        // El nombre del servidor SonarQube que configuraste en Manage Jenkins > Configure System
        SONAR_SERVER = 'SonarQube'
        // El nombre de la credencial que contiene tu token de SonarQube.
        // Asegúrate de que esta credencial exista en Jenkins con este ID exacto.
        SONAR_LOGIN = credentials('sonarqube-token')
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Clonando el repositorio...'
                checkout scm
            }
        }

        stage('Compile, Test & Analyze') {
            steps {
                echo 'Ejecutando build y análisis de SonarQube...'
                // Unificamos la compilación, pruebas y análisis en un solo comando de Maven.
                withSonarQubeEnv(SONAR_SERVER) {
                    sh """
                        ./mvnw clean verify sonar:sonar \
                          -Dsonar.projectKey=banking-api \
                          -Dsonar.host.url=http://sonarqube:9000 \
                          -Dsonar.login=${SONAR_LOGIN}
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo 'Esperando por el Quality Gate de SonarQube...'
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build & Deploy') {
            // Este stage necesita acceso a Docker, por lo que no puede correr dentro del
            // contenedor de Maven. Usamos un agente diferente.
            agent any // Usa el agente principal de Jenkins, que tiene acceso al socket de Docker.

            steps {
                echo 'Construyendo imagen Docker y desplegando con Docker Compose...'
                script {
                    // Primero construimos la imagen de la app.
                    // Usamos el build number de Jenkins como tag para un versionado único.
                    def imageName = "banking-api:${env.BUILD_NUMBER}"
                    docker.build(imageName, "--build-arg APP_VERSION=${env.BUILD_NUMBER} -f Dockerfile .")

                    // Ahora, usamos docker-compose para desplegar.
                    // El --build solo reconstruirá el servicio 'banking-api'
                    sh 'docker-compose -f docker-compose.yml up -d --build banking-api'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}