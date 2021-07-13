job('NodeJS Docker example') {
    scm {
        git('git://github.com/perry9186/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')

        }
    }
    triggers {
        scm('H/5 * * * *')
    }

    steps {
        dockerBuildAndPublish {
            repositoryName('perrye/nodejs')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            buildContext('./basics')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
