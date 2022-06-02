pipeline {
    agent any 
    environment {
         //registry = "adriansandoval/baches"
         registryCredential = 'dockerhub_id'
         //dockerImage = ''
    }
    
    stages {
    stage('Docker Build ') {
           steps {
                sh 'echo '
                sh 'docker build -t baches:1.0 --build-arg USER_DB=${USER_DB} --build-arg PASSWORD_DB=${PASSWORD_DB} --build-arg NAME_DB=${NAME_DB} ./'
                sh 'docker tag baches adriansandoval/baches:latest'
               
          }
        }
         stage('Run Docker container on remote hosts') {
             
            steps {
                sh "docker -H ssh://adrian@192.168.1.20 run -d --add-host db:192.168.1.20 -p 8090:8080 adriansandoval/baches:1.0"

            }
        }

  }
}



