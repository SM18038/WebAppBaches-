pipeline {
    agent any
    stages {
        stage('SCM') {  
            checkout scm
            }
        stage('SonarQube Analysis') {
            def mvn = tool '3.8.4';
            withSonarQubeEnv() {
            sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=bachesTPIBackend"
           }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh "mvn clean compile test verify"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}