pipeline {
    agent any 
    tools {
        maven "maven"
    }
    environment {
         registry = "adriansandoval/baches"
         registryCredential = 'dockerhub_id'
         dockerImage = ''
    }
    
stages {
        //Unit Test
        stage('Test') {
            steps {
                withMaven(maven: 'maven') {
                sh 'mvn -f pom.xml clean test'
                }
            }
        }
    stage('sonnar'){
            steps('gates'){
                withMaven {
            sh'mvn --version'
            sh 'mvn clean verify sonar:sonar \
                -Dsonar.projectKey=bachesTPIBackend \
                -Dsonar.host.url=http://localhost:9000 \
                -Dsonar.login=c3f3c55acf5a7e1630ab3a173bdd0808a7776816'
                }             
                
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
    
     // Stopping Docker containers for cleaner Docker run
     stage('docker stop container') {
         steps {
            sh 'docker ps -f name=bachesImage -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=bachesImage -q | xargs -r docker container rm'
         }
       }
    
    
    // Running Docker container, make sure port 8096 is opened in 
    stage('Docker Run') {
     steps{
         script {
            dockerImage.run("-p 9090:8080 --add-host db:192.168.1.20--rm --name bachesImage")
         }
      }
    }
  }
}



