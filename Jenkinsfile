pipeline {
    agent {
        docker {
            image 'maven:3.9.5-eclipse-temurin-21'
            args '-u root'
        }
    }
    stages {
        stage('Json Placeholder tests') {
            steps {
                sh 'mvn -B clean test -Dtest=JsonPlaceholderTest'
            }
        }
    }
}