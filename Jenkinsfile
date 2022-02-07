pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh '''
                ls
                tree .
                '''
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
