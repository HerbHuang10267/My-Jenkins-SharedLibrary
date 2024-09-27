#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

//static void main(String[] args) {
//    List<ServerInfo> serverInfoList = queryServerInfoList(project: "local", sql: "SELECT * FROM serverinfo;")
//    println "==============="
//    for (ServerInfo serverInfo : serverInfoList) {
//        println serverInfo.toString()
//    }
//}

def queryServerInfoList(Map config = [:]) {
    def data = DBQueryRunner.executeSQL(project: config.project, sql: config.sql)
    return parseServerInfoList(data)
}

def updateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    StringBuilder sqlBuilder = new StringBuilder()
    serverInfoMap.each { key, value ->
        sqlBuilder.append("UPDATE serverinfo SET status = ${value}, updatedate = date('now') WHERE hostname = '${key}';")
    }
    DBQueryRunner.executeUpdate(project: config.project, sql: sqlBuilder.toString())
}

def parseServerInfoList(String data) {

    if (data == null || data.isEmpty()) {
        return null
    }

    List<ServerInfo> serverInfoList = new ArrayList()
    // 去除 header 及分隔線
    def lines = data.readLines().drop(1)
    lines.each { line ->
        def fields = line.split(/\|/, -1) // 使用 | 分隔資料
        if (fields.size() >= 8) {
            // id|hostname|ip|port|servertype|servertypename|status|updatedate
            def serverInfo = new ServerInfo (
                    id: fields[0] as int,            // id
                    hostName: fields[1],             // server 名稱
                    ip: fields[2],                   // ip
                    port: fields[3] as int,          // port
                    serverType: fields[4] as int,    // server type
                    serverTypeName: fields[5],       // server type name
                    status: fields[6] as int,        // 狀態
                    updateDate: fields[7]            // 更新日期
            )
            serverInfoList.add(serverInfo)
        }
    }
    return serverInfoList
}

def serverInfoNameMap(def serverInfoList) {

    if (serverInfoList == null || serverInfoList.isEmpty()) {
        return null
    }
    Map serverInfoNameMap = [:]
    serverInfoList.each { server ->
        serverInfoNameMap[server.serverTypeName] ?: serverInfoNameMap.put(server.serverTypeName, [])
        List serverInfoStatusList = serverInfoNameMap.get(server.serverTypeName)
        serverInfoStatusList.add(server)
    }
    return serverInfoNameMap
}