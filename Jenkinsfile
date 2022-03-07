#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
      nodejs 'node14'
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

      def dockerImage
      stage('Build Docker') {
        steps {
          script {
            dockerImage = docker.build 'web'
          }
        }
      }
    }
}