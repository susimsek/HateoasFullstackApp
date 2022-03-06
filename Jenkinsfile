#!/usr/bin/env groovy

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
            sh "./mvnw clean"
        }

        stage('test') {
            try {
                sh "./mvnw verify"
            } catch(err) {
                throw err
            } /* finally {
                junit '**//* target/surefire-reports/TEST-*.xml,**//* target/failsafe-reports/TEST-*.xml'
            } */
        }

        stage('packaging') {
            sh "./mvnw -DskipTests verify"
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }

        stage('quality analysis') {
            withSonarQubeEnv('sonar') {
                sh "./mvnw -Psonar initialize sonar:sonar"
            }
        }
    }

    def dockerImage
    stage('native build docker') {
        sh "./mvnw -Pnative-image -DskipTests spring-boot:build-image"
    }
}