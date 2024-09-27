import com.serverInfo.dto.ServerInfo
import com.serverInfo.dao.ServerInfoDAO

static void main(String[] args) {
    List<ServerInfo> serverInfoList = ServerInfoDAO.queryServerInfo('SELECT * FROM serverinfo')
    println "==============="
    for (ServerInfo serverInfo : serverInfoList) {
        println serverInfo.toString()
    }
}



