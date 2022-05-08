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
            withSonarQubeEnv() { 
            sh "mvn clean verify sonar:sonar -Dsonar.projectKey=bachesTPIBackend"
                }
            }
        }
       
    }
}