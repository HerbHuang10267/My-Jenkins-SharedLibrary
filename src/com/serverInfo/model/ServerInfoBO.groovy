#!/usr/bin/env groovy
package com.serverInfo.model

import com.serverInfo.dao.ServerInfoDAO
import com.serverInfo.dto.ServerInfo
import groovy.transform.Field

@Field ServerInfoDAO serverInfoDAO = new ServerInfoDAO()

def queryServerInfoList(Map config = [:]) {
    List<ServerInfo> serverInfoList = serverInfoDAO.queryServerInfoList(config)
    return serverInfoList
}

def updateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    serverInfoDAO.updateServerInfoStatus(config, serverInfoMap)
}

return this