node {
    step([$class: 'GitHubSetCommitStatusBuilder'])
    checkout scm
    sh "chmod +x ./gradlew"
    sh "./gradlew clean build publish"
    archive includes: '*/build/libs/*.jar'
    slackSend "Build Finished - ${env.JOB_NAME} - ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
}