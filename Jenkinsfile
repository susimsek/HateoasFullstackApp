#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
      jdk 'jdk17'
      maven 'maven3'
    }

    stages {
        stage('checkout scm') {
           steps {
             checkout scm
           }
        }

        stage('check java') {
          steps {
            sh 'java -version'
          }
       }

       stage('clean') {
         steps {
           sh 'mvn -ntp clean'
         }
       }

       stage('test') {
        steps {
         script {
           try {
                 sh 'mvn -ntp test'
           } catch(err) {
               throw err
             }
           }
        }
       }

      stage('packaging') {
        steps {
           sh 'mvn -ntp package -DskipTests'
        }
      }

      stage('quality analysis') {
        steps {
          withSonarQubeEnv('sonar') {
            sh 'mvn -ntp -Psonar initialize sonar:sonar'
          }
        }
      }

      stage('build native image') {
        steps {
          sh 'mvn -ntp -Pnative-image spring-boot:build-image -DskipTests'
        }
      }
    }

    post {
      always {
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
      }
    }
}