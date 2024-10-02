#!/usr/bin/env groovy
package com.serverGroupInfo.dao

import com.common.database.DBQueryRunner
import com.serverGroupInfo.dto.ServerGroupInfo
import groovy.transform.Field

@Field DBQueryRunner dbQueryRunner = new DBQueryRunner()

def queryServerGroupInfoList(Map config = [:]) {

    def data = dbQueryRunner.executeSQL(project: config.project, sql: "SELECT * FROM servergroupinfo ORDER BY "+config.sort+";")

    if (data == null || data.isEmpty()) {
        return null
    }

    List<ServerGroupInfo> serverGroupInfoList = new ArrayList()
    // 去除 header 及分隔線
    def lines = data.readLines().drop(1)
    lines.each { line ->
        def fields = line.split(/\|/, -1) // 使用 | 分隔資料
        if (fields.size() >= 3) {
            // servergroup|shutdownorder|startuporder
            def serverGroupInfo = new ServerGroupInfo (
                    serverGroup: fields[0],             // server 名稱
                    shutDownOrder: fields[1] as int,    // shut down order
                    startUpOrder: fields[2] as int      // start up order
            )
            serverGroupInfoList.add(serverGroupInfo)
        }
    }

    return serverGroupInfoList
}

return this