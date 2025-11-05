#!/user/bin/env groovy
package com.example

class Docker implements Serializable {
  def script
  Docker(script){
    this.script = script
  }

  def buildDockerImage(String imageName){
    script.echo "deploying the docker image.."
    script.withCredentials([script.usernamePassword(credentialsId: 'jenkins-docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]){
    script.sh "docker build -t $imageName ."
    script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
    script.sh "docker push $imageName"
  }
}
}