#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

    docker.image('eclipse-temurin:17-jdk-focal').inside() {
        stage('check java') {
            sh "java -version"
        }

        stage('clean') {
            sh "chmod +x mvnw"
            sh "./mvnw -ntp clean"
        }

        stage('test') {
            try {
                sh "./mvnw -ntp verify"
            } catch(err) {
                throw err
            } /* finally {
                junit '**//* target/surefire-reports/TEST-*.xml,**//* target/failsafe-reports/TEST-*.xml'
            } */
        }

        stage('packaging') {
            sh "./mvnw -ntp verify -DskipTests"
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }

        stage('quality analysis') {
            withSonarQubeEnv('sonar') {
                sh "./mvnw -ntp -Psonar initialize sonar:sonar"
            }
        }
    }

    def dockerImage
    stage('native build docker') {
      sh "./mvnw -ntp -Pnative-image verify spring-boot:build-image"
    }
}