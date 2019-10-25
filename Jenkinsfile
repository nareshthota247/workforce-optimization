pipeline {
    agent any
    
    stages {
        stage("Git clone") {
            steps {
                //git clone  
                deleteDir()
                echo 'Clone the latest code from the code-base'
                sh 'git clone https://github.com/nareshthota247/workforce-optimization.git'       
            }
            
        }
        stage("Testcases") {
            steps {
                //Execute testcases 
                echo 'Execute test cases'
                dir("workforce-optimization"){
                    sh 'mvn clean test' 
                }               
            }
            
        }
        stage("Maven Build") {
            steps {
                echo 'Execute Maven Build'
                dir("workforce-optimization"){
                    sh 'mvn clean package'
                }
            }
            
        }
        stage("Docker Build") {            
            steps {
                echo 'Execute Docker Build'
                dir("workforce-optimization"){
                    sh "docker build -t workforce-optimization:\"${env.BUILD_NUMBER}\" . "
                    //sh 'docker push'
                    echo "Check the Docker image"
                    sh 'docker images'
                }
            }
            
        }
        stage("Deployment") {
            steps {
                dir("workforce-optimization"){
                    sh "docker run -d -p 89:8082 workforce-optimization:\"${env.BUILD_NUMBER}\""
                }
            }            
        }
      
    }//end stages
}