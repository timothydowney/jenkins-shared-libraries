# NPM Steps

Example npm build.

```groovy
@Library('my-shared-lib') _
pipeline {
    agent {
        label 'docker'
    }
    stages {
        stage('npm build') {
            steps {
                withNpmrc([npmrcId: 'my-npmrc']) {
                    sh 'npm install && grunt build'
                }
            }
        }
    }
}
```

## With NPMRC Settings

The [withNpmrc](vars/withNpmrc.md) step executes a closure with the npmrc settings file.

```groovy
stage('npm build') {
    steps {
        withNpmrc([npmrcId: 'my-npmrc']) {
            sh 'npm install && grunt build'
        }
    }
}

stage('npm build') {
    steps {
        withNpmrc([npmrcId: 'my-npmrc', image: 'dtr.cdl.es.ad.adp.com/nasdevops/node-grunt-phantomjs:6.10.3']) {
            sh 'npm install'
            sh 'grunt build'
        }
    }
}
```
