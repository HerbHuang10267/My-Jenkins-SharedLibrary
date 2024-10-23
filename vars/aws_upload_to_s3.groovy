#!/usr/bin/env groovy

def call(Map config = [:]) {

    def awsBucketName = config.awsBucketName
    def awsProfile = config.awsProfile
    def awsSyncToBucketNameList = config.awsSyncToBucketNameList

    sh """
        
        aws s3 sync ./target/ROOT/ s3://$awsBucketName/ --exclude "*" --include "*.ico" --delete --profile $awsProfile
        aws s3 sync ./target/ROOT/js/ s3://$awsBucketName/js/ --delete --profile $awsProfile
        aws s3 sync ./target/ROOT/images/ s3://$awsBucketName/images/ --delete --profile $awsProfile
        aws s3 sync ./target/ROOT/css/ s3://$awsBucketName/css/ --delete --profile $awsProfile

    """

    // copy bucket from main bucket to sub buckets
    for (def awsSyncToBucketName : awsSyncToBucketNameList) {
        sh """

            aws s3 sync s3://$awsBucketName s3://$awsSyncToBucketName --profile $awsProfile

        """
    }
}
