package com.serverInfo.dto

class ServerInfo {

    int id;
    int serverType;
    String serverGroup;
    String hostName;
    int port;
    String ip;
    int status;
    String tomcatPath
    String startUpShellPath
    String shutDownShellPath
    String deployShellPath
    String updateDate;

    @Override
    String toString() {
        return "ServerInfo{" +
                "id=" + id +
                ", serverType=" + serverType +
                ", serverGroup='" + serverGroup + '\'' +
                ", hostName='" + hostName + '\'' +
                ", port=" + port +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                ", tomcatPath='" + tomcatPath + '\'' +
                ", startUpShellPath='" + startUpShellPath + '\'' +
                ", shutDownShellPath='" + shutDownShellPath + '\'' +
                ", deployShellPath='" + deployShellPath + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
