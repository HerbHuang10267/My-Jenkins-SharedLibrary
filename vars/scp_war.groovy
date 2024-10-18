#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {
    sh """
        scp target/ROOT.war root@$serverInfo.ip:$serverInfo.tomcatPath/ROOT.zip
    """
}
