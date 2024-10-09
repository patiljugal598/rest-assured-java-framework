pipeline {
    agent any
/*    tools {
        maven 'Maven'
    }*/

    tools {
        dockerTool 'Docker'
    }
    stages {
        stage('Build') {
            steps {
           //     bat "mvn clean install -DskipTests"
                bat "docker build -t rest-assured-testng ."
            }
        }

        stage('Test') {
            steps {
                script {
                    catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                     //   bat "mvn clean test -P${profile}"
                        bat "docker run -e MAVEN_PROFILE=${profile} -v \$(pwd)/allure-results:/app/allure-results rest-assured-testng"
                    }
                }
            }
        }

        stage('Reports') {
            steps {
                script {
                    allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
                }
            }
        }

    }

}