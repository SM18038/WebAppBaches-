pipeline {
    agent any 
    environment {
         registry = "adriansandoval/baches"
         registryCredential = 'dockerhub_id'
         dockerImage = ''
    }
    
stages {
    
    // Building Docker images
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry
        }
      }
    }
    
     // Uploading Docker images into Docker Hub
    stage('Upload Image') {
     steps{    
         script {
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
            }
        }
      }
    }
    
     // Stopping Docker containers for cleaner Docker run
     stage('docker stop container') {
         steps {
            sh 'docker ps -f name=baches:1.0 -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=baches:1.0 -q | xargs -r docker container rm'
         }
       }
    
    
    // Running Docker container
    stage('Docker Run') {
     steps{
         script {
             dockerImage.run("-p 8080:8080 --add-host db:192.168.1.20 --rm --name baches baches:1.0")
         }
      }
    }
  }
}



