#!/usr/bin/env groovy
package com.serverInfo.dao

import com.common.database.DBQueryRunner
import com.serverInfo.dto.ServerInfo
import groovy.transform.Field

@Field DBQueryRunner dbQueryRunner = new DBQueryRunner()

def queryServerInfoList(Map config = [:]) {
    def con = [project: config.project, sql: "SELECT * FROM serverinfo ORDER BY hostname;"]
    def data = dbQueryRunner.executeSQL(con)

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
                deployShellPath: fields[11],     // deploy_server shell path
                filebeatShellPath: fields[12],   // filebeat shell path
                ip2LocationPath: fields[13],     // ip2location path
                updateDate: fields[14]           // 更新日期
        )
        serverInfoList.add(serverInfo)
    }
    return serverInfoList
}

def updateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    StringBuilder sqlBuilder = new StringBuilder()
    serverInfoMap.each { key, value ->
        sqlBuilder.append("UPDATE serverinfo SET status = ${value}, updatedate = date('now') WHERE hostname = '${key}';")
    }
    dbQueryRunner.executeUpdate(project: config.project, sql: sqlBuilder.toString())
}

def queryActiveHostName(Map config = [:]) {
    def con = [project: config.project, sql: "SELECT hostname FROM serverinfo WHERE status = 1 ORDER BY hostname;"]
    def data = dbQueryRunner.executeSQL(con)
    return data
}

return this