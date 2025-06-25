pipeline {
	agent any // El pipeline se inicia en el agente principal de Jenkins

    stages {
		stage('Build & Analyze') {
			// Este stage se ejecuta dentro de un contenedor Docker limpio
            agent {
				docker { image 'maven:3.9.6-eclipse-temurin-21' }
            }
            steps {
				// Hacemos el checkout del código dentro del contenedor
                checkout scm

                echo 'Ejecutando build, pruebas y análisis de SonarQube...'
                environment {
					SONAR_LOGIN = credentials('sonarqube-token')
                }
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
			// Este stage se ejecuta de nuevo en el agente principal
            steps {
				echo 'Esperando por el Quality Gate de SonarQube...'
                timeout(time: 1, unit: 'MINUTES') {
					waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Image & Deploy App') {
			// Este stage también se ejecuta en el agente principal para tener acceso a Docker y docker-compose
            steps {
				echo 'Construyendo imagen final y desplegando...'

                // Hacemos el checkout de nuevo en este agente para tener los Dockerfiles.
                // Es la forma más segura de asegurar que los archivos están presentes.
                checkout scm

                // Usamos docker-compose para construir la imagen de la app y levantar los servicios.
                // El --build solo reconstruirá el servicio 'banking-api' si es necesario.
                sh 'docker-compose -f docker-compose.yml up -d --build banking-api'
            }
        }
    }

    post {
		always {
			// Limpia el workspace al final de la ejecución.
            cleanWs()
        }
    }
}