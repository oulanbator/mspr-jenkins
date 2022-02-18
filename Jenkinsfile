pipeline {
    agent any

    stages {
        stage('SCM') {
            steps{
                checkout scm
            }
        }
        stage('SonarQube Analysis') {
            steps{
                withSonarQubeEnv('sonarQube') {
                sh 'mvn clean verify sonar:sonar'
                }
              }
        }
        stage('Build') {
            steps {
                echo 'Building....'
                sh '''
                git branch
                mvn clean package
                tree .
                '''
            }
        }
        stage('Test') {
            steps {
                echo 'Testing....'
            }
        }
        stage('Rename') {
            steps {
                echo 'Deploying....'
                sh '''
                cp target/*-jar-with-dependencies.jar target/generator.jar
                '''
            }
        }
    }
}
