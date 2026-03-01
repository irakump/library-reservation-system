pipeline {
    agent any

    /* tools {
        jdk 'JDK 21' // java set to jenkins settings
        maven 'Maven3' // maven set to jenkins settings
    } */

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'docker_hub' // docker credentials in jenkins settings
        DOCKERHUB_REPO_BACKEND = 'library-reservation-system-backend'
        DOCKERHUB_REPO_FRONTEND = 'library-reservation-system-frontend'
        DOCKERHUB_REPO_DATABASE = 'library-reservation-system-database'
        DOCKER_IMAGE_TAG = 'latest'
        BACKEND_DIRECTORY = 'backend'
        FRONTEND_DIRECTORY = 'frontend'
    }

    stages {
        // only use "Check" stage with "Pipeline script" in jenkins, comment out if using the Jenkinsfile in repository
        /* stage ('Check') {
            steps {
                // can change branch if needed, useful for testing features
                git url: 'https://github.com/Nurha20-24/library-reservation-system.git', credentialsId: 'GitHub-pat', branch: 'jenkins-integration'
            }
        } */

        /* Backend */
        stage('Backend: Build') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-21'
                    args '-v maven-cache:/root/.m2 --user root'
                    reuseNode true
                }
            }
            steps {
                dir(BACKEND_DIRECTORY) {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Backend: Run Tests') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-21'
                    args '-v maven-cache:/root/.m2 --user root'
                    reuseNode true
                }
            }
            steps {
                dir(BACKEND_DIRECTORY) {
                    sh 'mvn test'
                }
            }
        }

        stage ('Backend: Generate and Publish Code Coverage Report'){
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-21'
                    args '-v maven-cache:/root/.m2 --user root'
                    reuseNode true
                }
            }
            steps {
                dir(BACKEND_DIRECTORY) {
                    sh 'mvn jacoco:report'
                }
            }
            post {
                always {
                    jacoco()
                }
            }
        }

        /* Frontend */
        stage('Frontend: Build') {
            agent {
                docker {
                    image 'node:20-alpine'
                    args '--user root'
                    reuseNode true
                }
            }
            steps {
                dir(FRONTEND_DIRECTORY) {
                    sh 'npm install'
                }
            }
        }

        stage('Frontend: Run Tests') {
            agent {
                docker {
                    image 'node:20-alpine'
                    args '--user root'
                    reuseNode true
                }
            }
            steps {
                dir(FRONTEND_DIRECTORY) {
                    sh 'npm run test:once'
                }
            }
        }

        /* Docker */
        stage('Build Docker Images') {
            steps {
                sh 'docker --version'
                sh 'docker compose -f compose.yml build'
            }
        }

        stage('Push Docker Images to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS_ID, usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    sh """
                        echo \$DOCKERHUB_PASSWORD | docker login -u \$DOCKERHUB_USERNAME --password-stdin

                        docker tag ${DOCKERHUB_REPO_BACKEND}:${DOCKER_IMAGE_TAG} \$DOCKERHUB_USERNAME/${DOCKERHUB_REPO_BACKEND}:${DOCKER_IMAGE_TAG}
                        docker push \$DOCKERHUB_USERNAME/${DOCKERHUB_REPO_BACKEND}:${DOCKER_IMAGE_TAG}

                        docker tag ${DOCKERHUB_REPO_FRONTEND}:${DOCKER_IMAGE_TAG} \$DOCKERHUB_USERNAME/${DOCKERHUB_REPO_FRONTEND}:${DOCKER_IMAGE_TAG}
                        docker push \$DOCKERHUB_USERNAME/${DOCKERHUB_REPO_FRONTEND}:${DOCKER_IMAGE_TAG}

                        docker tag ${DOCKERHUB_REPO_DATABASE}:${DOCKER_IMAGE_TAG} \$DOCKERHUB_USERNAME/${DOCKERHUB_REPO_DATABASE}:${DOCKER_IMAGE_TAG}

                        docker push \$DOCKERHUB_USERNAME/${DOCKERHUB_REPO_DATABASE}:${DOCKER_IMAGE_TAG}

                        docker logout
                    """
                }
            }
        }

    }

    /* stage('Diagnostics') {
        steps {
            sh 'java -version'
            sh 'mvn --version'
            sh 'echo $JAVA_HOME'
            sh 'docker version'
            sh "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ${BACKEND_DIRECTORY}/"
        }
    } */

}