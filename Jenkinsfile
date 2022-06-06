pipeline {
    agent any
    environment {
        WS = "${WORKSPACE}"
    }
    stages {
        stage('compile') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2 -u root'
                }
            }
            steps {
                sh 'cd ${WS} && mvn clean package -s "/root/.m2/settings.xml"  -DskipTests'
            }
        }
        stage('Build') {
            steps {
                sh 'docker build -t chatServer:${BUILD_NUMBER} .'
            }

        }
        stage('Deploy') {
            steps {
                sh 'if [[ -n $(docker ps -q -f "name=chatServer") ]];then docker rm -f "chatServer"; else echo "container not exist"; fi'
                sh 'docker run -d -u root -p 10800:10800 --name chatServer -v /usr/local/chat_upload:/upload chatServer:${BUILD_NUMBER}'
            }
        }
    }
}