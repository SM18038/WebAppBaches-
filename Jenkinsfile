pipeline{
    agent any
    environment {
        PATH = "$PATH:/usr/share/maven/bin"
    }
    stages{        
       stage('Build'){
            steps{
                //sh 'mvn clean package'
            }
         }
       stage('SonarQube analysis') {
        steps{
            withSonarQubeEnv('Sonarqube 8.9.2') { 
            sh "mvn clean verify sonar:sonar -Dsonar.projectKey=bachesTPIBackend"
                }
            }
        }
       
    }
}