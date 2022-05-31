pipeline {
    agent any 
    environment {
         registry = "adriansandoval/baches"
        //registryCredential = 'dockerhub_id'
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
   
  }
}