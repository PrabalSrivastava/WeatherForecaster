pipeline {
    agent any

    environment {
        OPEN_WEATHER_API_KEY = credentials('OPEN_WEATHER_API_KEY')
        DOCKER_USR = credentials('DOCKER_USR')
        DOCKER_PSW = credentials('DOCKER_PSW')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                sh '/opt/homebrew/bin/mvn clean package -DskipDocker -DskipFrontendTests -DskipTests'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh '/opt/homebrew/bin/mvn clean package -DskipDocker -DskipFrontendTests'
            }
        }

        stage('Run Frontend Tests') {
            steps {
                sh '/opt/homebrew/bin/mvn clean package -DskipDocker -DskipTests'
            }
        }

        stage('Publish Docker Image') {
            steps {
                sh '/usr/local/bin/docker login -u $DOCKER_USR -p $DOCKER_PSW'
                sh '/opt/homebrew/bin/mvn package -DskipTests -DskipFrontendTests'
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
