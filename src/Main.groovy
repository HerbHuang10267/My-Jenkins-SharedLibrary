@Grapes(
        @Grab(group='org.xerial', module='sqlite-jdbc', version='3.8.9.1')
)
import com.serverInfo.dto.ServerInfo
static void main(String[] args) {
//    List<ServerInfo> serverInfoList =  new com.serverInfo.dao.ServerInfoDAO().findServerInfo('SELECT * FROM serverinfo')
//    println "==============="
//    for (ServerInfo serverInfo : serverInfoList) {
//        println serverInfo.toString()
//    }


    // 原始数据字符串
    def data = '''
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

    // 调用函数，将数据解析为 List<ServerInfo>
    def serverInfoList = parseServerInfo(data)
    println serverInfoList.size()
// 打印结果，验证是否正确
    serverInfoList.each { server ->
        println "ID: ${server.id}, Name: ${server.name}, IP: ${server.ip}, Port: ${server.port}, Type: ${server.servertype}, Status: ${server.status}, Updatedate: ${server.updatedate}"
    }
}

class ServerInfo {

    int id;
    int serverType;
    String serverTypeName;
    String hostName;
    String port;
    String ip;
    int status;
    String updateDate;

//    // 定义一个普通的构造函数
//    ServerInfo(int id, String name, String ip, int port, int servertype, int status, String updatedate) {
//        this.id = id
//        this.name = name
//        this.ip = ip
//        this.port = port
//        this.servertype = servertype
//        this.status = status
//        this.updatedate = updatedate
//    }
}

def parseServerInfo(String data) {
    // 初始化存储 ServerInfo 对象的 List
    List<ServerInfo> serverInfoList = new ArrayList()

    // 将数据按行分割并去除第一行（标题行）
    def lines = data.readLines().drop(2)
    println lines
    // 遍历每一行，将其解析成 ServerInfo 对象
    lines.each { line ->
        def fields = line.split(/\|/, -1) // 使用 | 分隔符分割每行
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
                id: fields[0] as int,            // 将 id 转换为整数
                name: fields[1],                 // 名称
                ip: fields[2],                   // IP 地址
                port: fields[3] as int,          // 端口号
                servertype: fields[4] as int,    // 服务器类型
                status: fields[5] as int,        // 状态
                updatedate: fields[6]            // 更新日期（如果为空则为 null）
            )
            println serverInfo.toString()
            // 添加到列表
            serverInfoList.add(serverInfo)
            println serverInfoList.size()
        }
    }
    println serverInfoList.size()
    return serverInfoList
}



