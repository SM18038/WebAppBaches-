node {

    environment {
         registry = "adriansandoval/baches:1.0"
         registryCredential = 'dockerhub_id'
         dockerImage = ''
    }

  stage('SCM') {
    checkout scm
  }
  stage('Build') {
       echo 'Building..'
        
  }
   stage('Unit Test') {
        echo 'Testing...'
            sh "mvn compile test"           
    }

  stage('SonarQube Analysis') {
    def mvn = tool '3.8.5';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=bachesTPIBackend"
    }
  }

    // Building Docker images
    stage('Building image') {
          dockerImage = docker.build registry
      }
    
    // Uploading Docker images into Docker Hub
    stage('Upload Image') {  
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
        }
      
    }

    // Running Docker container
    stage('Docker Run') {
             dockerImage.run("docker run --name baches --rm -p 8090:8080 --add-host db:192.168.1.16 baches:1.0")
      
    }
}