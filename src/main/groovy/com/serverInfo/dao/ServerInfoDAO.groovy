package com.serverInfo.dao

@Grapes(
    @Grab(group='org.xerial', module='sqlite-jdbc', version='3.8.9.1')
)
import groovy.sql.Sql
import com.serverInfo.dto.ServerInfo

class ServerInfoDAO {

    def static queryServerInfo(String sql) {

//        def dbPath = 'C:/Users/SAHerbHuangT14/initial-db.sqlite'
        def dbPath = '/var/jenkins_home/sqlite_data/9w-db.sqlite'
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
            serverInfo.setServerTypeName(row.servertypename)
            serverInfo.setHostName(row.hostname)
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

}
