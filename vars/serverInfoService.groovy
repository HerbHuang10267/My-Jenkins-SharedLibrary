#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo
import com.serverInfo.model.ServerInfoBO
import groovy.transform.Field

@Field ServerInfoBO serverInfoBO = new ServerInfoBO()

static void main(String[] args) {
    println "START"
    List<ServerInfo> serverInfoList = queryServerInfoList(project: "local")
    println "==============="
    for (ServerInfo serverInfo : serverInfoList) {
        println serverInfo.toString()
    }
}

def queryServerInfoList(Map config = [:]) {
    return serverInfoBO.queryServerInfoList(config)
}

def updateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    return serverInfoBO.updateServerInfoStatus(config, serverInfoMap)
}

def genServerStatusOptionHtml(Map config = [:]) {
    return serverInfoBO.genServerStatusOptionHtml(config)
}

def queryActiveHostName(Map config = [:]) {
    return serverInfoBO.queryActiveHostName(config)
}

//return this