import com.serverInfo.dto.ServerInfo
import com.serverInfo.dao.ServerInfoDAO

def call() {

    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    echo "Hello World! My first Shared Library"
    ServerInfoDAO serverInfoDAO = ServerInfoDAO()
    List<ServerInfo> serverInfoList = serverInfoDAO.findServerInfo('SELECT * FROM serverinfo')
}