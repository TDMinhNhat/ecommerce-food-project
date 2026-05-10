pipeline {
    agent {
        label 'ecommerce-food-project-node'
    }

    stages {
        // Build Frontend Website
        stage("Build Frontend") {
            when {
                branch 'frontend'
            }

            steps {
                echo 'Building frontend'
            }
        }

        // Build Backend Modules
        stage("Build Modules") {
                when {
                    branch 'backend'
                }
				steps {
				    dir("ecommerce-food-backend") {
						sh 'pwd'
						echo 'Building backend modules'
						sh './gradlew clean build'
					}
				}

        }

        // Build image and deploy them into docker
        stage("Deployment") {
            steps {
                sh 'docker compose up -d --build'
            }
        }
    }

    post {
        success {
            echo "Jenkins: Build successfully"
        }

        failure {
            echo "Jenkins: Build failed"
        }
    }
}