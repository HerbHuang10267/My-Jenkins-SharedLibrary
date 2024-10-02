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

def queryActiveHostName(Map config = [:]) {
    def data = serverInfoDAO.queryActiveHostName(config)

    if (data == null || data.isEmpty()) {
        return null
    }

    List hostNameList = []
    // 去除 header 及分隔線
    def lines = data.readLines().drop(1)
    lines.each { line ->
        def fields = line.split(/\|/, -1) // 使用 | 分隔資料
        if (fields.size() >= 1) {
            hostNameList.add(fields[0])
        }
    }
    return hostNameList
}

def genServerStatusOptionHtml(Map config = [:]) {

    List<ServerInfo> serverInfoList = serverInfoDAO.queryServerInfoList(config)

    if (serverInfoList == null || serverInfoList.isEmpty()) {
        return "[No Data Found]"
    }

    serverInfoList.sort { it.serverGroup }

    def optionResult =  new StringBuilder()
    def previousServerType = -1
    for (ServerInfo serverInfo : serverInfoList) {
        optionResult.append("<option value=\"${serverInfo.hostName}\">${serverInfo.hostName}</option>")

        if (previousServerType != serverInfo.getServerType()) {
            optionResult.append("<br><br><h5> ================= ${serverInfo.getServerGroup()} ================= </h5>")
        }

        def checkClass = (serverInfo.getStatus() == 1 ? "checked" : "")
        optionResult.append("<input name='value' value='${serverInfo.getHostName()}' type='hidden'>")
                .append("<input id='${serverInfo.getId()}' name='value' value='${serverInfo.getHostName()}' type='checkbox' ${checkClass}>")
                .append("<label for='${serverInfo.getId()}'>${serverInfo.getHostName()}</label>")

        previousServerType = serverInfo.getServerType()
    }
}

return this