import com.serverInfo.dto.ServerInfo

def call() {

    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    echo "Hello World! My first Shared Library"
    ServerInfoDAO serverInfoDAO = new com.serverInfo.dao.ServerInfoDAO()
    List<ServerInfo> serverInfoList = serverInfoDAO.findServerInfo('SELECT * FROM serverinfo')
}