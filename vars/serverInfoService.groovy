#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def executeSQL(Map config = [:]) {

    try {
        def dbPath = getDataBasePath(config.project)
        def sql = config.sql
        def command = ["sqlite3", dbPath, ".headers on", sql]
        def proc = command.execute()
        def sout = new StringBuilder(), serr = new StringBuilder()
        proc.consumeProcessOutput(sout, serr)
        proc.waitForOrKill(60 * 1000)
        return parseServerInfo(sout)
    } catch (Exception e) {
        println("SQLite excute SQL failed: ${e.getMessage()} ")
    }
    return null
}

def batchUpdateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    StringBuilder sqlBuilder = new StringBuilder()
    sqlBuilder.append("BEGIN TRANSACTION;")
    serverInfoMap.each { key, value ->
        sqlBuilder.append("UPDATE serverinfo SET status = ${value}, updatedate = date('now') WHERE hostname = '${key}';")
    }
    sqlBuilder.append("COMMIT;")
    executeSQL(project: config.project, sql: sqlBuilder.toString())
}

def getDataBasePath(String project) {
    switch (project) {
        case "9w":
            return '/var/jenkins_home/sqlite_data/9w-db.sqlite'
        case "vk":
            return '/var/jenkins_home/sqlite_data/vk-db.sqlite'
        case "mini":
            return '/var/jenkins_home/sqlite_data/mini-db.sqlite'
        default:
            return null
    }
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