pipeline {
    agent any

    tools {
        maven 'Maven' // لازم يكون الاسم ده مطابق للي مسميه في Jenkins > Global Tool Configuration
        jdk 'JDK'     // نفس الكلام بالنسبة للـ JDK
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Build & Tests passed successfully!'
        }
        failure {
            echo 'Build or Tests failed. Check the logs.'
        }
    }
}