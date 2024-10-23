#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {
    sh """
        ssh -p56123 root@$serverInfo.ip sh $serverInfo.deployShellPath
        
        scp -p56123 root@$serverInfo.ip ls -alh $serverInfo.tomcatPath
    """
}
