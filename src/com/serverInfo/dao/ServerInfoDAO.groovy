package com.serverInfo.dao

@Grapes(
    @Grab(group='org.xerial', module='sqlite-jdbc', version='3.46.1.0')
)
import groovy.sql.Sql
import com.serverInfo.dto.ServerInfo

class ServerInfoDAO implements Serializable{

    static List<ServerInfo> findServerInfo(String sql) {
        // def dbPath = 'C:/Users/SAHerbHuangT14/initial-db.sqlite'
        def dbPath = '/var/jenkins_home/sqlite_data/initial-db.sqlite'
    def findServerInfo(String sql) {
        def url = "jdbc:sqlite:$dbPath"
        def sqlInstance = Sql.newInstance(url, "org.sqlite.JDBC")

        List<ServerInfo> serverInfoList = new ArrayList<>();
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
        return serverInfoList
    }

}
