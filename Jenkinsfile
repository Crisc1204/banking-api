pipeline {
	agent any
    stages {
		stage('Checkout Code') {
			steps {
				echo 'Limpiando el workspace antes de empezar...'
                cleanWs() // Forzamos una limpieza para empezar de cero

                echo 'Clonando el repositorio...'
                checkout scm
            }
        }
        stage('Verify Maven Wrapper') {
			steps {
				echo 'Verificando que mvnw es ejecutable...'
                // Este comando es seguro y no compila nada, solo muestra la versión de Maven.
                sh './mvnw --version'
            }
        }
    }
}