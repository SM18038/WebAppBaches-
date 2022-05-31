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
    // Running Docker container, make sure port 8096 is opened in 
    stage('Docker Run') {
     steps{
         script {
            dockerImage.run("-p 8080:5000 --rm --name baches:1.0")
         }
      }
    }
  }
}