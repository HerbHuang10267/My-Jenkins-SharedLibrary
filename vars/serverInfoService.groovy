#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def executeSQL(Map config = [:]) {
    def dbPath = getDataBasePath(config.project)
    def sql = config.sql
    try {
        String result = sh(script: "sqlite3 ${dbPath} \".headers ON\" \"${sql}\"", returnStdout: true)
        return parseServerInfo(result)
    } catch (Exception e) {
        println("SQLite excute SQL failed: ${e.getMessage()} ")
    }
    return null
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