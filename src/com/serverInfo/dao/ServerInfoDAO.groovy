package com.serverInfo.dao

@Grapes(
    @Grab(group='org.xerial', module='sqlite-jdbc', version='3.8.9.1')
)
import groovy.sql.Sql
import com.serverInfo.dto.ServerInfo

class ServerInfoDAO implements Serializable {

    def script

    ServerInfoDAO(script) {
        this.script = script
    }

    def findServerInfo(String sql) {

//        def dbPath = 'C:/Users/SAHerbHuangT14/initial-db.sqlite'
        def dbPath = '/var/jenkins_home/sqlite_data/initial-db.sqlite'
        script.println(dbPath)
        def url = "jdbc:sqlite:$dbPath"
        script.println(url)
        def sqlInstance = script.Sql.newInstance(url, "org.sqlite.JDBC")
        script.println(sqlInstance)
        List<ServerInfo> serverInfoList = new ArrayList<>();
        script.println(serverInfoList)
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

            script.println serverInfo.toString()
        }

        sqlInstance.close()
        script.println("===================")
        script.println(serverInfoList)
        return serverInfoList
    }

}
