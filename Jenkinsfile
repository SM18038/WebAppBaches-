pipeline {
    agent any 
    environment {
        //once you sign up for Docker hub, use that user_id here
         registry = "adriansandoval/baches"
        //- update your credentials ID after creating credentials for connecting to Docker Hub
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
            dockerImage.run("docker run --name baches --rm -p 8090:8080 --add-host db:192.168.1.16 baches:1.0")
         }
      }
    }
  }
}