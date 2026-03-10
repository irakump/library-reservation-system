pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'docker_hub' // docker credentials in jenkins settings
        DOCKERHUB_REPO_BACKEND = 'library-reservation-system-backend'
        DOCKERHUB_REPO_FRONTEND = 'library-reservation-system-frontend'
        DOCKERHUB_REPO_DATABASE = 'library-reservation-system-database'
        DOCKER_IMAGE_TAG = 'latest'
        BACKEND_DIRECTORY = 'backend'
        FRONTEND_DIRECTORY = 'frontend'
        DATABASE_DIRECTORY = 'backend/database'
    }

    stages {
        /* stage('Diagnostics') {
            steps {
                sh 'java -version'
                sh 'mvn --version'
                sh 'echo $JAVA_HOME'
                sh 'docker version'
                sh "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ${BACKEND_DIRECTORY}/"
            }
        } */
    
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

        stage('Backend: Run and Publish Tests') {
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
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
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
        // add multi-platform support, ARM and AMD
        stage('Build and Push Docker Images to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS_ID, usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    sh """
                        echo \$DOCKERHUB_PASSWORD | docker login -u \$DOCKERHUB_USERNAME --password-stdin
                        
                        docker buildx create --name mybuilder || true
                        docker buildx use mybuilder
                        
                        docker buildx build --push --platform linux/arm64,linux/amd64 --tag \$DOCKERHUB_USERNAME/${DOCKERHUB_REPO_BACKEND}:${DOCKER_IMAGE_TAG} ./${BACKEND_DIRECTORY}
                        docker buildx build --push --platform linux/arm64,linux/amd64 --tag \$DOCKERHUB_USERNAME/${DOCKERHUB_REPO_FRONTEND}:${DOCKER_IMAGE_TAG} ./${FRONTEND_DIRECTORY}
                        docker buildx build --push --platform linux/arm64,linux/amd64 --tag \$DOCKERHUB_USERNAME/${DOCKERHUB_REPO_DATABASE}:${DOCKER_IMAGE_TAG} ./${DATABASE_DIRECTORY}
                        
                        docker logout
                    """
                }
            }
        }

    }

}