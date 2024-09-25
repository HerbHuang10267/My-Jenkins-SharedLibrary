#!/usr/bin/env groovy
import groovy.sql.Sql
import com.serverInfo.dto.ServerInfo

def test1() {
    println("Hello World! test1 ")
}

def test2() {
    println("Hello World! test2 ")
//    ServerInfoDAO serverInfoDAO = new ServerInfoDAO(this)
    List<ServerInfo> serverInfoList = findServerInfo("SELECT * FROM serverinfo")
    println(serverInfoList.size())
}

def findServerInfo(String sql) {

//        def dbPath = 'C:/Users/SAHerbHuangT14/initial-db.sqlite'
    def dbPath = '/var/jenkins_home/sqlite_data/initial-db.sqlite'
    println(dbPath)
    def url = "jdbc:sqlite:$dbPath"
    println(url)
    def sqlInstance = Sql.newInstance(url, "org.sqlite.JDBC")
    println(sqlInstance)
    List<ServerInfo> serverInfoList = new ArrayList<>();
    println(serverInfoList)
    sqlInstance.eachRow(sql) { row ->
        ServerInfo serverInfo = new ServerInfo()

        serverInfo.setId(row.id)
        serverInfo.setServerType(row.servertype)
        serverInfo.setName(row.name)
        serverInfo.setIp(row.ip)
        serverInfo.setPort(row.port)
        serverInfo.setStatus(row.status)
        serverInfo.setUpdateDate(row.updatedate)

        serverInfoList.add(serverInfo)

        println serverInfo.toString()
    }

    sqlInstance.close()
    println("===================")
    println(serverInfoList)
    return serverInfoList
}