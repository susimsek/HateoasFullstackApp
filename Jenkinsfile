#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
      nodejs 'node14'
    }

    environment {
     imageTag = 'registry.heroku.com/hateoas-fullstack-ui/web'
     registryCredential = 'heroku-registry'
     dockerImage = ''
    }

    stages {
        stage('checkout scm') {
           steps {
             checkout scm
           }
        }

        stage('check node') {
          steps {
            sh 'node -v'
          }
       }

       stage('install dependencies') {
         steps {
           sh 'npm install'
         }
       }

      stage('quality analysis') {
        steps {
          withSonarQubeEnv('sonar') {
            sh 'npm run sonar'
          }
        }
      }

      stage('Build Docker') {
        steps {
          script {
            dockerImage = docker.build imageTag
          }
        }
      }

      stage('Publish Docker') {
        steps {
          script {
            docker.withRegistry( 'https://registry.heroku.com', registryCredential ) {
              dockerImage.push()
            }
          }
        }
      }
    }
}