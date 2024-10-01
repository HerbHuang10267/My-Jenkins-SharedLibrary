#!/usr/bin/env groovy
package com.serverGroupInfo.model

import com.serverGroupInfo.dao.ServerGroupInfoDAO
import com.serverGroupInfo.dto.ServerGroupInfo

def queryServerGroupInfoList(Map config = [:]) {

    def data = new ServerGroupInfoDAO().queryServerGroupInfoList(config)

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