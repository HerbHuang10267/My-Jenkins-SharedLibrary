#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {
    sh """
        scp -P56123 ./target/ROOT.war root@$serverInfo.ip:$serverInfo.tomcatPath/ROOT.zip
    """
}
