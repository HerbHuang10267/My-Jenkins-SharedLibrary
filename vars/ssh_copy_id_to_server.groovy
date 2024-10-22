#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {
    sh """
        ssh-copy-id -i /root/.ssh/id_rsa.pub -p56123 -o StrictHostKeyChecking=no root@$serverInfo.ip
    """
}
