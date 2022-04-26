pipeline {
    agent any
    tools {
        maven "3.8.4"
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Test') {
            steps {
                //echo 'Testing..'
                sh "mvn clean compile test"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}