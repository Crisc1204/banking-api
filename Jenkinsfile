pipeline {
	// Definimos el agente principal para todo el pipeline.
    // Este agente es el contenedor de Jenkins que ya hemos preparado con el cliente de Docker.
    agent any

    // Definimos las variables de entorno para todo el pipeline
    environment {
		SONAR_SERVER = 'SonarQube'
        SONAR_LOGIN  = credentials('sonarqube-token')
        // Definimos la imagen de Docker que usaremos para construir
        BUILDER_IMAGE = 'mi-maven-builder:latest'
    }

    stages {
		stage('Checkout Code') {
			steps {
				// Limpiamos cualquier workspace anterior para asegurar un estado limpio
                cleanWs()
                // Clonamos el código en el workspace del agente principal
                checkout scm
            }
        }

        stage('Build, Test & Analyze') {
			steps {
				// Aquí está la magia: Usamos Scripted Pipeline dentro de Declarative
                script {
					// Le decimos a Docker que use la imagen que definimos
                    docker.image(BUILDER_IMAGE).inside {
						// Todos los comandos dentro de este bloque se ejecutan DENTRO del contenedor
                        echo 'Ejecutando build, pruebas y análisis de SonarQube...'

                        // withSonarQubeEnv se encarga de la conexión
                        withSonarQubeEnv(SONAR_SERVER) {
							sh """
                                ./mvnw clean verify sonar:sonar \
                                  -Dsonar.projectKey=banking-api \
                                  -Dsonar.host.url=http://sonarqube:9000 \
                                  -Dsonar.login=${SONAR_LOGIN}
                            """
                        }
                    } // El contenedor se detiene y se elimina automáticamente al salir de este bloque
                }
            }
        }

        stage('Quality Gate') {
			steps {
				// Este paso se ejecuta de nuevo en el agente principal (agent any)
                echo 'Esperando por el Quality Gate de SonarQube...'
                timeout(time: 1, unit: 'MINUTES') {
					waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Image & Deploy App') {
			steps {
				// Este paso también se ejecuta en el agente principal
                echo 'Construyendo imagen final y desplegando...'

                // No necesitamos otro checkout, el workspace del agente principal ya tiene el código.

                // Usamos docker-compose para construir la imagen de la app y levantar los servicios.
                sh 'docker-compose -f docker-compose.yml up -d --build banking-api'
            }
        }
    }

    post {
		always {
			// Este cleanWs() se ejecuta al final en el agente principal
            echo 'Limpiando el workspace al final del pipeline...'
            cleanWs()
        }
    }
}