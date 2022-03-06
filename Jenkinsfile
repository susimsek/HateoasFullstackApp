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
         script {
           try {
               sh 'mvn -ntp verify'
           } catch(err) {
               throw err
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

/*
node {
    stage('checkout') {
        checkout scm
    }

    docker.image('eclipse-temurin:17-jdk-alpine').inside() {
        stage('check java') {
            sh "java -version"
        }

        stage('clean') {
            sh "chmod +x mvnw"
            sh "./mvnw -ntp clean"
        }

         */
/* stage('test') {
            try {
                sh "./mvnw -ntp -Pnative verify"
            } catch(err) {
                throw err
            }  *//*
 */
/* finally {
                junit '**//*
 */
/*  *//*
 */
/* target/surefire-reports/TEST-*.xml,**//*
 */
/*  *//*
 */
/* target/failsafe-reports/TEST-*.xml'
            } *//*
 */
/*
        }

        stage('packaging') {
            sh "./mvnw -ntp -Pnative verify -DskipTests"
            archiveArtifacts artifacts: '**//*
 */
/* target *//*
 */
/*.jar', fingerprint: true
        }

        stage('quality analysis') {
            withSonarQubeEnv('sonar') {
                sh "./mvnw -ntp -Psonar initialize sonar:sonar"
            }
        }

         stage('clean') {
                    sh "chmod +x mvnw"
                    sh "./mvnw -ntp clean"
         } *//*

    }

     def dockerImage
        stage('build docker') {
            sh "./mvnw -ntp -Pjib compile jib:build -DskipTests"
        }

//    def dockerImage
//     stage('native build docker') {
//       sh "./mvnw -ntp -Pnative-image spring-boot:build-image -DskipTests"
//     }
 */