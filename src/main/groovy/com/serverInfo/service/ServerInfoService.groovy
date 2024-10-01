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

def queryServerInfoList(Map config = [:]) {
    return new ServerInfoBO().queryServerInfoList(config)
}

def staticupdateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    return new ServerInfoBO().updateServerInfoStatus(config, serverInfoMap)
}

def genServerStatusOptionHtml(Map config = [:]) {
    return new ServerInfoBO().genServerStatusOptionHtml(config)
}

return this