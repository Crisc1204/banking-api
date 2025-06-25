pipeline {
	agent none

    stages {
		stage('Compile, Test & Analyze') {
			agent {
				docker { image 'maven:3.9.6-eclipse-temurin-21' }
            }
            steps {
				echo 'Clonando el repositorio...'
                checkout scm

                echo 'Ejecutando build y análisis de SonarQube...'
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
			agent {
				docker { image 'maven:3.9.6-eclipse-temurin-21' }
            }
            steps {
				echo 'Esperando por el Quality Gate de SonarQube...'
                timeout(time: 1, unit: 'MINUTES') {
					waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build & Deploy') {
			agent any

            steps {
				echo 'Construyendo imagen Docker y desplegando con Docker Compose...'
                docker.build("banking-api:${env.BUILD_NUMBER}", '.')
                sh 'docker-compose -f docker-compose.yml up -d --build banking-api'
            }
        }
    }

    post {
		always {
			node {
				echo 'Limpiando el workspace...'
                cleanWs()
            }
        }
    }
}