#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def executeSQL(String sql) {
    def dbPath = '/var/jenkins_home/sqlite_data/initial-db.sqlite'
    try {
        String result = sh(script: "sqlite3 ${dbPath} \".headers ON\" \"${sql}\"", returnStdout: true)
        return parseServerInfo(result)
    } catch (Exception e) {
        println("SQLite excute SQL failed: ${e.getMessage()} ")
    }
    return null
}

def parseServerInfo(String data) {

    if (data == null || data.isEmpty()) {
        return null
    }

    List<ServerInfo> serverInfoList = new ArrayList()
    // 去除 header 及分隔線
    def lines = data.readLines().drop(2)
    lines.each { line ->
        def fields = line.split(/\|/, -1) // 使用 | 分隔資料
        println fields
        if (fields.size() >= 7) {
            println fields[0]
            println fields[1]
            println fields[2]
            println fields[3]
            println fields[4]
            println fields[5]
            println fields[6]
            // id|name|ip|port|servertype|status|updatedate
            def serverInfo = new ServerInfo (
                    id: fields[0] as int,            // id
                    name: fields[1],                 // server 名稱
                    ip: fields[2],                   // ip
                    port: fields[3] as int,          // port
                    servertype: fields[4] as int,    // server type
                    status: fields[5] as int,        // 狀態
                    updatedate: fields[6]            // 更新日期
            )
            serverInfoList.add(serverInfo)
        }
    }
    return serverInfoList
}