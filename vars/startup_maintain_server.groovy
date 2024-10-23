#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {
    sh """
        ssh -p56123 root@$serverInfo.ip sh $serverInfo.startUpMaintainShellPath

        ssh -p56123 root@$serverInfo.ip ps -ef | grep tomcat
    """
}
