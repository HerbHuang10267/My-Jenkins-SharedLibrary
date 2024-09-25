package com.serverInfo.dao

@Grapes(
    @Grab(group='org.xerial', module='sqlite-jdbc', version='3.8.9.1')
)
import groovy.sql.Sql
import com.serverInfo.dto.ServerInfo

class ServerInfoDAO implements Serializable {

    def script

    ServerInfoDAO(script) {
        this.script=script
    }

    def findServerInfo(String sql) {
        script.println("do findServerInfo")
//        def dbPath = 'C:/Users/SAHerbHuangT14/initial-db.sqlite'
//        // def dbPath = '/var/jenkins_home/sqlite_data/initial-db.sqlite'
//        def url = "jdbc:sqlite:$dbPath"
//        def sqlInstance = Sql.newInstance(url, "org.sqlite.JDBC")
//
//        List<ServerInfo> serverInfoList = new ArrayList<>();
//        sqlInstance.eachRow(sql) { row ->
//            ServerInfo serverInfo = new ServerInfo()
//
//            serverInfo.setId(row.id)
//            serverInfo.setServerType(row.servertype)
//            serverInfo.setName(row.name)
//            serverInfo.setIp(row.ip)
//            serverInfo.setPort(row.port)
//            serverInfo.setStatus(row.status)
//            serverInfo.setUpdateDate(row.updatedate)
//
//            serverInfoList.add(serverInfo)
//
//            println serverInfo.toString()
//        }
//
//        sqlInstance.close()
//        return serverInfoList
        script.println(sql)
        return null
    }

}
