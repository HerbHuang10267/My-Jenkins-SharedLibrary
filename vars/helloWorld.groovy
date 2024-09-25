import com.serverInfo.dto.ServerInfo
import com.serverInfo.dao.ServerInfoDAO

def test1() {
    println("Hello World! test1 ")
}

def test2() {
    println("Hello World! test2 ")
    List<ServerInfo> serverInfoList = ServerInfoDAO.findServerInfo('SELECT * FROM serverinfo')
}