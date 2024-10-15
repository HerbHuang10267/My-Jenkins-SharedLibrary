#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(List<ServerInfo> serverInfoList) {
    log.logServerInfo(serverInfoList)
}
