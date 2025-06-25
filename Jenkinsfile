pipeline {
	agent any // El checkout se hace en el agente principal
    stages {
		stage('Checkout Code') {
			steps {
				echo 'Limpiando el workspace antes de empezar...'
                cleanWs() // Forzamos una limpieza para empezar de cero

                echo 'Clonando el repositorio...'
                checkout scm
            }
        }
        stage('Build inside Docker') {
			// Ahora, este stage específico se ejecutará dentro del contenedor Docker
            agent {
				docker { image 'maven:3.9.6-eclipse-temurin-21' }
            }
            steps {
				echo 'Dentro del contenedor Docker. Compilando el proyecto...'
                // Ahora ejecutamos el build completo
                sh './mvnw clean install -DskipTests' // Saltamos tests por ahora para ir más rápido
            }
        }
    }
}