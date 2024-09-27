
import com.serverInfo.dto.ServerInfo

static void main(String[] args) {

    def data = executeSQL(project: "local", sql: "SELECT * FROM serverinfo;")
    def serverInfoList = data

    serverInfoList.each { server ->
        println "ID: ${server.id}, hostName: ${server.hostName}, IP: ${server.ip}, Port: ${server.port}, serverType: ${server.serverType}, serverTypeName: ${server.serverTypeName}, Status: ${server.status}, updateDate: ${server.updateDate}"
    }
}

def executeSQL(Map config = [:]) {
    def dbPath = getDataBasePath(config.project)
    def sql = config.sql
    try {
//        def result = sh(script: "sqlite3 ${dbPath} \".headers ON\" \"${sql}\"", returnStdout: true)

        def command = ["sqlite3", dbPath, ".headers on", sql]
        def proc = command.execute()
        def sout = new StringBuilder(), serr = new StringBuilder()
        proc.consumeProcessOutput(sout, serr)
        proc.waitForOrKill(60 * 1000)

        println("sout: ${sout}")
        def result = '''
        id|hostname|ip|port|servertype|servertypename|status|updatedate
        1|Player Server|10.100.10.14|80|7|PLAYER/AGENT/MANAGER|0|2024-09-25 15:27:21
        2|Betfair Api Server|10.100.10.15|8888|32|API_SERVER|0|2024-09-25 15:27:22
        3|Betfair Result Server|10.100.10.15|9099|8|RESULT_SERVER|0|2024-09-25 15:27:22
        4|Transaction Server|10.100.10.15|9090|16|TRANSACTION_SERVER|0|2024-09-25 15:27:23
        5|Sportradar Api Server|10.100.10.19|8888|32|API_SERVER|0|2024-09-25 15:27:23
        6|Sportradar Result Server|10.100.10.19|9099|8|RESULT_SERVER|0|2024-09-25 15:27:24
        7|TS Api Server|10.100.10.23|8888|32|API_SERVER|1|2024-09-25 15:27:24
        8|Cache Server|10.100.10.23|9090|2048|CACHE_SERVER|0|2024-09-25 15:27:24
        9|Vendor Result Server|10.100.10.21|9099|8|RESULT_SERVER|0|2024-09-25 15:27:25
        10|Player Server|10.100.10.193|80|1|PLAYER|0|2024-09-25 15:27:26
        11|Exposure Server01|10.100.10.24|8085|4096|EXPOSURE_SERVER|0|2024-09-25 15:27:26
        12|Exposure Server02|10.100.10.25|8085|4096|EXPOSURE_SERVER|1|2024-09-25 15:27:26
        13|Api Provider Server01|10.100.10.26|8084|8192|API_PROVIDER_SERVER|0|2024-09-25 15:27:26
        14|Api Provider Server02|10.100.10.27|8084|8192|API_PROVIDER_SERVER|0|2024-09-25 15:27:26
        15|Elk Server01|10.100.10.28|9098|16384|ELK_SERVER|0|2024-09-25 15:27:27
        16|Transaction Server - Player Txn|10.100.10.29|9090|16|TRANSACTION_SERVER|0|2024-09-25 15:27:28
        17|Betting server|10.100.10.225|8088|64|BETTING_SERVER|0|2024-09-25 15:27:28
        '''
        return parseServerInfo(sout.toString())
    } catch (Exception e) {
        println("SQLite excute SQL failed: ${e.getMessage()} ")
    }
    return null
}

def getDataBasePath(String project) {
    switch (project) {
        case "local":
            return 'C:/Users/SAHerbHuangT14/initial-db.sqlite'
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

def parseServerInfo(def data) {

    if (data == null || data.isEmpty()) {
        return null
    }

    def serverInfoList = []
    // 去除 header 及分隔線
    def lines = data.readLines().drop(2)
    lines.each { line ->
        def fields = line.split(/\|/, -1) // 使用 | 分隔資料
        println fields
        if (fields.size() >= 8) {
            // id|hostname|ip|port|servertype|servertypename|status|updatedate
            def serverInfo = new ServerInfo (
                    id: fields[0] as int,            // id
                    hostName: fields[1],                 // server 名稱
                    ip: fields[2],                   // ip
                    port: fields[3] as int,          // port
                    serverType: fields[4] as int,    // server type
                    serverTypeName: fields[5],        // server type name
                    status: fields[6] as int,        // 狀態
                    updateDate: fields[7]            // 更新日期
            )
            serverInfoList.add(serverInfo)
        }
    }
    return serverInfoList
}



