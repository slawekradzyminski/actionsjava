pipeline {
    agent { docker 'maven:3.9.5-eclipse-temurin-21' }
    stages {
        stage('Json Placeholder tests') {
            steps {
                sh 'mvn -B clean test -Dtest=JsonPlaceholderTest'
            }
        }
    }
}