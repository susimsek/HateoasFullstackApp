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
                 sh 'mvn -ntp verify'
           } catch(err) {
               throw err
             }
           }
        }
       }

       stage('packaging') {
         sh 'mvn -ntp package -DskipTests'
         archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
      }

      stage('quality analysis') {
        withSonarQubeEnv('sonar') {
          sh 'mvn -ntp -Psonar initialize sonar:sonar'
        }
      }
    }
}