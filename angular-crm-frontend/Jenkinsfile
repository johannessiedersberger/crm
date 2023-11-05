pipeline {
    agent any

    tools {
        nodejs "node"
        gradle "gradle"
        dockerTool "docker"
        
    }


    environment {
        ANGULAR_APP_DIR = 'angular-crm-frontend'
        SPRING_BOOT_APP_DIR = 'spring-boot-crm-backend'
        registry = 'johannessiedersberger/crm'
        registryCredential = 'dockerhub'
        dockerImage = ''
    }


    stages {
         stage('Checkout Repo') {
            steps {
               
                    script {
                         checkout([$class: 'GitSCM', branches: [[name: 'main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/johannessiedersberger/crm.git']]])
                        
                    }
                
            }
        }
        
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
        
        stage("Build Docker Image") {
          steps {
                dir("${ANGULAR_APP_DIR}") {
                   script {
                     dockerImage = docker.build registry + ":$BUILD_NUMBER"
                  }
                }
              
            }
        }
        
        stage("Deploy Docker Image") {
          steps {
                dir("${ANGULAR_APP_DIR}") {
                   script {
                      docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                        }
                    }
              
                }
            }
        }

        stage("Remove unused Docker image") {
          steps {
                dir("${ANGULAR_APP_DIR}") {
                   script {
                      sh "docker rmi $registry:$BUILD_NUMBER"
                    }
              
                }
            }
        }


    }
    
}