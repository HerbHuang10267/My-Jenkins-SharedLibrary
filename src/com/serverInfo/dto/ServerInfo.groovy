package com.serverInfo.dto

class ServerInfo {
    // id|hostname|ip|port|servertype|servergroup|status|tomcatpath|filebeatconfigpath|startupshellpath|shutdownshellpath|deployshellpath|filebeatshellpath|ip2locationpath|updatedate
    int id;
    String hostName;
    String ip;
    int port;
    int serverType;
    String serverGroup;
    int status;
    String tomcatPath
    String filebeatConfigPath
    String startUpShellPath
    String shutDownShellPath
    String deployShellPath
    String filebeatShellPath
    String ip2LocationPath
    String updateDate;

    @Override
    String toString() {
        return "ServerInfo{" +
                "id=" + id +
                ", hostName='" + hostName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", serverType=" + serverType +
                ", serverGroup='" + serverGroup + '\'' +
                ", status=" + status +
                ", tomcatPath='" + tomcatPath + '\'' +
                ", filebeatConfigPath='" + filebeatConfigPath + '\'' +
                ", startUpShellPath='" + startUpShellPath + '\'' +
                ", shutDownShellPath='" + shutDownShellPath + '\'' +
                ", deployShellPath='" + deployShellPath + '\'' +
                ", filebeatShellPath='" + filebeatShellPath + '\'' +
                ", ip2LocationPath='" + ip2LocationPath + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
