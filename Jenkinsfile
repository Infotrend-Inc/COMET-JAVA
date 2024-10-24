pipeline {
    agent any

    environment {
        AWS_CREDENTIALS_ID = 'aws_credentials'
        AWS_REGION = 'us-east-1' 
        CLUSTER_NAME = 'comet-eks-cluster'
        ECR_REPOSITORY = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/comet-app-repo"
    }

    stages {
        stage('Checkout') {
            steps {
            
                git 'https://github.com/Infotrend-Inc/COMET-JAVA.git'
            }
        }

        stage('Log in to Amazon ECR') {
            steps {
                withCredentials([usernamePassword(credentialsId: AWS_CREDENTIALS_ID, usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                    sh '''
                    aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REPOSITORY
                    '''
                }
            }
        }

        stage('Pull Docker image') {
            steps {
                sh '''
                docker pull $ECR_REPOSITORY:latest
                '''
            }
        }

        stage('Update EKS kubeconfig') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: AWS_CREDENTIALS_ID]]) {
                    sh '''
                    aws eks update-kubeconfig --region $AWS_REGION --name $CLUSTER_NAME
                    '''
                }
            }
        }

        stage('Deploy to EKS') {
            steps {
                sh '''
                kubectl set image deployment/java-app java-container=$ECR_REPOSITORY:latest --namespace=your-namespace
                kubectl rollout status deployment/java-app --namespace=your-namespace
                '''
            }
        }
    }
}
