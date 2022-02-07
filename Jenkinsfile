node {
    stage("CleanWorkspace") {
        sh "rm -r *"
    }
    stage("CloneRepos") {
        sh '''
        git branch: 'victor', url: 'https://github.com/oulanbator/mspr-jenkins.git'
        '''
    }
    stage("Build") {
        sh '''
        ls
        tree .
        '''
    }
    stage("Artifact") {
        sh '''

        '''
    }
}
