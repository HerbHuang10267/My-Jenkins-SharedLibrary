#!/usr/bin/env groovy
import com.serverInfo.dto.ServerGroupInfo

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
    def data = executeSQL(project: config.project, sql: config.sql)
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

/*
 * Execute SQL query
 * @param config.project: project name
 * @param config.sql: SQL
 */
def executeSQL(Map config = [:]) {
    try {
        def dbPath = getDataBasePath(config.project)
        def sql = config.sql
        println("dbPath: ${dbPath}")
        println("sql: ${sql}")
        def command = ["sqlite3", dbPath, ".headers on", sql]
        def proc = command.execute()
        def sout = new StringBuilder(), serr = new StringBuilder()
        proc.consumeProcessOutput(sout, serr)
        proc.waitForOrKill(10 * 1000)
        println("sout: ${sout}")
        return sout.toString()
    } catch (Exception e) {
        println("SQLite excute SQL failed: ${e.getMessage()} ")
    }
    return null
}

/*
 * Execute SQL query
 * @param config.project: project name
 * @param config.sql: SQL
 */
def executeUpdate(Map config = [:]) {
    StringBuilder sqlBuilder = new StringBuilder()
    sqlBuilder.append("BEGIN TRANSACTION;")
    sqlBuilder.append(config.sql)
    sqlBuilder.append("COMMIT;")
    executeSQL(project: config.project, sql: sqlBuilder.toString())
}


def getDataBasePath(String project) {
    switch (project) {
        case "local":
            return 'C:/Users/SAHerbHuangT14/9w-db.sqlite'
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