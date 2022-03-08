#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
      jdk 'jdk17'
      maven 'maven3'
    }

    environment {
         imageTag = 'registry.heroku.com/hateoas-fullstack-ui/web'
         registryCredential = 'heroku-registry'
         registryUrl = 'heroku-registry'
         dockerImage = ''
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

      stage('publish native image') {
        steps {
            withCredentials([usernamePassword(credentialsId: registryCredential, passwordVariable: 'DOCKER_REGISTRY_PWD', usernameVariable: 'DOCKER_REGISTRY_USER')]) {
              sh 'mvn -ntp -Pnative-image spring-boot:build-image -DskipTests'
            }
        }
      }
    }

    post {
      always {
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
      }
    }
}