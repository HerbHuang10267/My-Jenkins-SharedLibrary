#!/usr/bin/env groovy

def call(Map config = [:]) {
    def awsBucketName = config.awsBucketName
    def awsProfile = config.awsProfile
    sh """

        mkdir -p target
        rm -f ./target/ROOT.war
        rm -rf ./target/ROOT
        ls -al ./target

        aws s3 cp s3://$awsBucketName/ROOT.war ./target --profile $awsProfile
        unzip -oq ./target/ROOT.war -d ./target/ROOT
        ls -al ./target
    """
}
