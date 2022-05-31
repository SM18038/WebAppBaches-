pipeline {
    agent any 
    tools {
        maven "3.8.5"
    }

    environment {
         registry = "adriansandoval/baches:1.0"
         registryCredential = 'dockerhub_id'
         dockerImage = ''
    }
    
    stages {

   //Build project
    stage('Build') {
            steps {
                echo 'Building..'
            }
        }

    stage('Unit Test') {
            steps {
                echo 'Testing..'
                sh "mvn clean compile test"
            }
        }

    stage('SonarQube Analysis') {
        steps {
            sh "mvn clean verify sonar:sonar -Dsonar.projectKey=bachesTPIBackend"
                
            }
        }
    
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
             dockerImage.run("docker run --name baches --rm -p 8090:8080 --add-host db:192.168.1.16 baches:1.0")
         }
      }
    }
  }
}