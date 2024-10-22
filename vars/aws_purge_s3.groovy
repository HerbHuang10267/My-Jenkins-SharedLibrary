#!/usr/bin/env groovy

def call(Map config = [:]) {

    def awsProfile = config.awsProfile
    def awsPurgeOriginDomainNameList = config.awsPurgeOriginDomainNameList

    // copy bucket from main bucket to sub buckets
    for (def awsPurgeOriginDomainName : awsPurgeOriginDomainNameList) {
        sh """
            
            # bucket 9wprod、9wprod-2 依 origin 內的 domainName 取得 distributions id list
            output=\$(aws cloudfront list-distributions --profile $awsProfile --query "DistributionList.Items[*].{id:Id,origin:Origins.Items[0].DomainName}[?origin=='$awsPurgeOriginDomainName'].id" --output text | tr '\\n' '\\t')
            
            # split str list by tab
            IFS=\$'\\t' read -r -a distributionsIdList <<< "\$output"
            
            for distributionId in "\${distributionsIdList[@]}"
            do
                aws cloudfront create-invalidation --profile $awsProfile --output table --distribution-id \$distributionId --paths "/*"
            done

        """
    }
}
