pipeline {
	// Declaramos 'agent none' para tener control total sobre dónde se ejecuta cada stage.
    agent none

    stages {
		stage('Compile, Test & Analyze') {
			// Este stage se ejecuta dentro de un contenedor Docker con Maven y JDK 21.
            agent {
				docker { image 'maven:3.9.6-eclipse-temurin-21' }
            }
            steps {
				echo 'Clonando el repositorio...'
                // El checkout se hace dentro de este agente, creando el workspace en el contenedor.
                checkout scm

                echo 'Ejecutando build y análisis de SonarQube...'
                // Definimos la credencial aquí, en el entorno del stage.
                environment {
					SONAR_LOGIN = credentials('sonarqube-token')
                }
                // Usamos withSonarQubeEnv para conectar con el servidor SonarQube.
                withSonarQubeEnv('SonarQube') {
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
			// Este stage también necesita el workspace, así que usamos un agente.
            // Puede ser el mismo agente docker o el agente principal.
            // Para simplicidad, usemos el agente principal.
            agent any
            steps {
				echo 'Esperando por el Quality Gate de SonarQube...'
                // El timeout debe ir dentro de 'steps'.
                timeout(time: 1, unit: 'MINUTES') {
					waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build & Deploy') {
			// Este stage necesita acceso a Docker, por lo que usamos el agente principal de Jenkins.
            agent any

            steps {
				echo 'Construyendo imagen Docker y desplegando con Docker Compose...'

                // Construimos la imagen usando el Dockerfile del workspace.
                docker.build("banking-api:${env.BUILD_NUMBER}", '.')

                // Usamos docker-compose para desplegar.
                // El --build solo reconstruirá el servicio 'banking-api'.
                sh 'docker-compose -f docker-compose.yml up -d --build banking-api'
            }
        }
    }

    post {
		always {
			// Envolvemos cleanWs() en un bloque 'node' para asegurar que tiene un contexto.
            node {
				echo 'Limpiando el workspace...'
                cleanWs()
            }
        }
    }
}