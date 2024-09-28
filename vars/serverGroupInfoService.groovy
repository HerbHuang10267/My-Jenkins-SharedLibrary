#!/usr/bin/env groovy
import com.serverInfo.dto.ServerGroupInfo
import com.common.database.DBQueryRunner

static void main(String[] args) {
    List<ServerGroupInfo> serverGroupInfoList = queryServerGroupInfoList(project: "local", sql: "SELECT * FROM servergroupinfo;")
    println "==============="
    for (ServerGroupInfo serverGroupInfo : serverGroupInfoList) {
        println serverGroupInfo.toString()
    }

    List startUpOrderList = serverGroupInfoList.sort { it.startUpOrder }.collect()
    println startUpOrderList

    List shutDownOrderList = serverGroupInfoList.sort { it.shutDownOrder }.collect()
    println shutDownOrderList
    println startUpOrderList
}

def queryServerGroupInfoList(Map config = [:]) {
    DBQueryRunner dbQueryRunner = new DBQueryRunner()
    def data = dbQueryRunner.executeSQL(project: config.project, sql: config.sql)
    return parseServerGroupInfoList(data)
}

def parseServerGroupInfoList(def data) {

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
                shutDownOrder: fields[1] as int,    // port
                startUpOrder: fields[2] as int      // server type
            )
            serverGroupInfoList.add(serverGroupInfo)
        }
    }
    return serverGroupInfoList
}