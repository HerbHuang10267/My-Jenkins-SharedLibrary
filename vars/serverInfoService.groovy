#!/usr/bin/env groovy
import com.common.database.DBQueryRunner
import com.serverInfo.dto.ServerInfo

static void main(String[] args) {
    List<ServerInfo> serverInfoList = queryServerInfoList(project: "local", sql: "SELECT * FROM serverinfo;")
    println "==============="
    for (ServerInfo serverInfo : serverInfoList) {
        println serverInfo.toString()
    }

    Map serverInfoGroupMap = serverInfoGroupMap(serverInfoList)
    println "==============="
    serverInfoGroupMap.each { key, value ->
        println "server group: ${key}"
        for (ServerInfo serverInfo : value) {
            println serverInfo.toString()
        }
    }
}

def queryServerInfoList(Map config = [:]) {
    DBQueryRunner db = new DBQueryRunner()
    def data = db.executeSQL(project: config.project, sql: config.sql)
    return parseServerInfoList(data)
}

def updateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    StringBuilder sqlBuilder = new StringBuilder()
    serverInfoMap.each { key, value ->
        sqlBuilder.append("UPDATE serverinfo SET status = ${value}, updatedate = date('now') WHERE hostname = '${key}';")
    }
    DBQueryRunner db = new DBQueryRunner()
    db.executeUpdate(project: config.project, sql: sqlBuilder.toString())
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
        // id|hostname|ip|port|servertype|servergroup|status|tomcatpath|startupshellpath|shutdownshellpath|deployshellpath|updatedate
        def serverInfo = new ServerInfo (
                id: fields[0] as int,            // id
                hostName: fields[1],             // server 名稱
                ip: fields[2],                   // ip
                port: fields[3] as int,          // port
                serverType: fields[4] as int,    // server type
                serverGroup: fields[5],          // server group
                status: fields[6] as int,        // 狀態
                tomcatPath: fields[7],           // tomcat path
                startUpShellPath: fields[8],     // start up shell path
                shutDownShellPath: fields[9],    // shut down shell path
                deployShellPath: fields[10],     // deploy shell path
                updateDate: fields[11]           // 更新日期
        )
        serverInfoList.add(serverInfo)
    }
    return serverInfoList
}

def serverInfoGroupMap(def serverInfoList) {

    if (serverInfoList == null || serverInfoList.isEmpty()) {
        return null
    }
    Map serverInfoGroupMap = [:]
    serverInfoList.each { server ->
        serverInfoGroupMap[server.serverGroup] ?: serverInfoGroupMap.put(server.serverGroup, [])
        List serverInfoStatusList = serverInfoGroupMap.get(server.serverGroup)
        serverInfoStatusList.add(server)
    }
    return serverInfoGroupMap
}