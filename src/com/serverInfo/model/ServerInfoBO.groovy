#!/usr/bin/env groovy
package com.serverInfo.model

import com.serverInfo.dao.ServerInfoDAO
import com.serverInfo.dto.ServerInfo

def static queryServerInfoList(Map config = [:]) {
    def data = ServerInfoDAO.queryServerInfoList(project: config.project)

    if (data == null || data.isEmpty()) {
        return null
    }

    List<ServerInfo> serverInfoList = new ArrayList()
    // 去除 header 及分隔線
    def lines = data.readLines().drop(1)
    lines.each { line ->
        def fields = line.split(/\|/, -1) // 使用 | 分隔資料
        // id|hostname|ip|port|servertype|servergroup|status|tomcatpath|filebeatconfigpath|startupshellpath|shutdownshellpath|deployshellpath|filebeatshellpath|ip2locationpath|updatedate
        def serverInfo = new ServerInfo (
                id: fields[0] as int,            // id
                hostName: fields[1],             // server 名稱
                ip: fields[2],                   // ip
                port: fields[3] as int,          // port
                serverType: fields[4] as int,    // server type
                serverGroup: fields[5],          // server group
                status: fields[6] as int,        // 狀態
                tomcatPath: fields[7],           // tomcat path
                filebeatConfigPath: fields[8],   // filebeat config path
                startUpShellPath: fields[9],     // start up shell path
                shutDownShellPath: fields[10],   // shut down shell path
                deployShellPath: fields[11],     // deploy shell path
                filebeatShellPath: fields[12],   // filebeat shell path
                ip2LocationPath: fields[13],     // ip2location path
                updateDate: fields[14]           // 更新日期
        )
        serverInfoList.add(serverInfo)
    }
    return serverInfoList
}

def static updateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    ServerInfoDAO.updateServerInfoStatus(config, serverInfoMap)
}

def static queryActiveHostName(Map config = [:]) {
    def data = ServerInfoDAO.queryActiveHostName(config)

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

def static genServerStatusOptionHtml(Map config = [:]) {
    List<ServerInfo> serverInfoList = queryServerInfoList(config)
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