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
                git checkout origin/victor
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
        stage('Reports') {
            steps{
            junit allowEmptyResults: true, skipPublishingChecks: true, testResults: '*/target/-reports/*.xml'
                emailext (
      subject: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
      recipientProviders: [['mathisallen@outlook.fr']]
    )
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
