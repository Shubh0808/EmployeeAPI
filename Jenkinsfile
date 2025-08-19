pipeline {
    agent any

    tools {
        maven 'Maven_3'             // Maven configured on Jenkins Global Tools
        jdk 'JDK_17'                // JDK configured on Jenkins
    }

    environment {
        SONARQUBE_SERVER = 'MySonarQube'         // Name from Jenkins → Configure System
        SONAR_PROJECT_KEY = 'employeeapi'
        SONAR_PROJECT_NAME = 'EmployeeAPI'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://your-repo-url.git'   // <-- replace with your Git repo or remove if using freestyle
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
            post {
                always {
                    jacoco execPattern: 'target/jacoco.exec'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONARQUBE_SERVER}") {
                    sh """
                      mvn sonar:sonar -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                                      -Dsonar.projectName=${SONAR_PROJECT_NAME} \
                                      -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 3, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/site/jacoco/**', fingerprint: true
        }
    }
}
