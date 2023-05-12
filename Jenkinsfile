pipeline {
  agent any
 
  stages{
    stage('build') {
      steps {
        sh 'docker build -t ola .'
      }
    }
    
    stage('run') {
      steps {
        sh 'docker run ola'
      }
    }
  }
  }
