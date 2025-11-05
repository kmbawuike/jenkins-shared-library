#!/user/bin/env groovy
package com.example

class Docker implements Serializable {

  def script
  Docker(script) {
    this.script = script
  }

  def buildDockerImage(String imageName) {
    script.echo 'building the docker image..'
    script.sh "docker build -t $imageName ."
  }

  def dockerLogin(String imageName) {
    script.echo 'docker authentication..'
    script.withCredentials([script.usernamePassword(credentialsId: 'jenkins-docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
      script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
    }
  }

  def dockerPush(String imageName) {
    script.echo 'pushing the docker image..'
    script.sh "docker push $imageName"
  }

}
