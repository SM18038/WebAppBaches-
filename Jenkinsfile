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

    // Running Docker container
    stage('Docker Run') {
     steps{
         script {
             dockerImage.run("docker run --name baches --rm -p 8080:8080 --add-host db:192.168.1.16 baches:1.0")
         }
      }
    }
  }
}