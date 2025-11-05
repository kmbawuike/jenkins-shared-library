#!/user/bin/env groovy
def call(){
  echo "building the application for ${env.BRANCH_NAME}"
  sh 'mvn package'
}