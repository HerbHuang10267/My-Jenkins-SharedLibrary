#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {
    sh """
        ssh root@$serverInfo.ip sh $serverInfo.startUpShellPath
    """
}
