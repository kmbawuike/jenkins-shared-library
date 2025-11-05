#!/user/bin/env groovy
def call(){
  echo "deploying the docker image"
  withCredentials([usernamePassword(credentialsId: 'jenkins-docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]){
    sh 'docker build -t kelz107/nana-projects:3.0 .'
    sh 'echo $PASS | docker login -u $USER --password-stdin'
    sh 'docker push kelz107/nana-projects:3.0'
  }
}