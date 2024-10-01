#!/usr/bin/env groovy
package com.serverInfo.service

import com.serverInfo.dto.ServerInfo
import com.serverInfo.model.ServerInfoBO

static void main(String[] args) {
    this.println "START"
    List<ServerInfo> serverInfoList = queryServerInfoList(project: "local")
    println "==============="
    for (ServerInfo serverInfo : serverInfoList) {
        println serverInfo.toString()
    }
}

def static queryServerInfoList(Map config = [:]) {
    return ServerInfoBO.queryServerInfoList(config)
}

def staticupdateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    return ServerInfoBO.updateServerInfoStatus(config, serverInfoMap)
}

def static genServerStatusOptionHtml(Map config = [:]) {
    return ServerInfoBO.genServerStatusOptionHtml(config)
}

return this