pipeline {
    agent any

    tools {
        nodejs "node"
        gradle "gradle"
        
    }


    environment {
        ANGULAR_APP_DIR = 'angular-crm-frontend'
        SPRING_BOOT_APP_DIR = 'spring-boot-crm-backend'
        registry = 'johannessiedersberger/crm'
    }


    stages {
        stage('Build Angular App') {
            steps {
                dir("${ANGULAR_APP_DIR}") {
                    script {
                        sh 'npm install'
                        sh 'npm run build'
                    }
                }
            }
        }

        stage('Build Spring Boot App') {
            steps {
                dir("${SPRING_BOOT_APP_DIR}") {
                    script {
                        sh 'chmod +x gradlew'
                        sh './gradlew clean build'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir("${ANGULAR_APP_DIR}") {
                    script {
                        sh 'docker build -t crm/frontend .'
                    }
                }
            }
            
        }

    }

    
}