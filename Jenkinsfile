node {
  stage('SCM') {
    checkout scm
  }
  stage('Build') {
        steps {
            echo 'Building..'
        }
    }
   stage('Unit Test') {
        steps {
            echo 'Testing..'
            sh "mvn compile test"
        }
    }

  stage('SonarQube Analysis') {
    def mvn = tool '3.8.5';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=bachesTPIBackend"
    }
  }

  stage('Deploy') {
        steps {
            echo 'Deploying..'
        }
   } 
}