package test
import com.serverInfo.dto.ServerInfo

static void main(String[] args) {

    def data = executeSQL("SELECT * FROM serverinfo;")
    def serverInfoList = data

    serverInfoList.each { server ->
        println "ID: ${server.id}, Name: ${server.name}, IP: ${server.ip}, Port: ${server.port}, serverType: ${server.serverType}, Status: ${server.status}, UpdateDate: ${server.UpdateDate}"
    }
}

def executeSQL(String sql) {
    def dbPath = 'C:/Users/SAHerbHuangT14/initial-db.sqlite'
    try {
//        def result = sh(script: "sqlite3 ${dbPath} \".headers ON\" \"${sql}\"", returnStdout: true)
        def result = '''
        id|name|ip|port|servertype|status|updatedate
        1|Player Server|10.100.10.14|80|7|0|
        2|Betfair Api Server|10.100.10.15|8888|32|0|
        3|Betfair Result Server|10.100.10.15|9099|8|0|
        4|Transaction Server|10.100.10.15|9090|16|0|
        5|Sportradar Api Server|10.100.10.19|8888|32|0|
        6|Sportradar Result Server|10.100.10.19|9099|8|0|
        7|TS Api Server|10.100.10.23|8888|32|1|
        8|Cache Server|10.100.10.23|9090|2048|0|
        9|Vendor Result Server|10.100.10.21|9099|8|0|
        10|Player Server|10.100.10.193|80|1|0|
        11|Exposure Server01|10.100.10.24|8085|4096|0|
        12|Exposure Server02|10.100.10.25|8085|4096|1|
        13|Api Provider Server01|10.100.10.26|8084|8192|0|
        14|Api Provider Server02|10.100.10.27|8084|8192|0|
        15|Elk Server01|10.100.10.28|9098|16384|0|
        16|Exposure Server Local|10.10.56.38|8081|4096|1|
        17|Transaction Server - Player Txn|10.100.10.29|9090|16|0|
        18|Betting server|10.100.10.225|8088|64|0|
        '''
        return parseServerInfo(result)
    } catch (Exception e) {
        println("SQLite excute SQL failed: ${e.getMessage()} ")
    }
    return null
}

def parseServerInfo(def data) {

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
                    serverType: fields[4] as int,    // server type
                    status: fields[5] as int,        // 狀態
                    updateDate: fields[6]            // 更新日期
            )
            serverInfoList.add(serverInfo)
        }
    }
    return serverInfoList
}



