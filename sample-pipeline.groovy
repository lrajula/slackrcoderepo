@Library('slackcoderepo') _
pipeline {
    agent { label 'master' }
    stages {
        stage('build') {
            steps {
                script{
				echo "hello world"
				currentBuild.result = 'SUCCESS'
				env.STAGE = "SUCCESS"
                }
            }
        }
    }
	post {
     success {
        notifyBuild("SUCCESS", env.STAGE)
     }
     failure {
        notifyBuild('FAILED', env.STAGE)
     }
     aborted {
        notifyBuild('ABORTED', env.STAGE)
     }
     unstable {
        notifyBuild('UNSTABLE', env.STAGE)
     }
  }
}
