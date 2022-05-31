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
    stage('Uploading Image') {
        echo 'Upload Image..'
            script {
                docker.withRegistry( '', registryCredential ) {
                dockerImage.push()
                }
            }          
   
    }
  }
}