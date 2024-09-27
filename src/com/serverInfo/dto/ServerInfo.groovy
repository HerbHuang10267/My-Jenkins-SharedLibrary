package com.serverInfo.dto

class ServerInfo {

    int id;
    int serverType;
    String serverTypeName;
    String hostName;
    int port;
    String ip;
    int status;
    String updateDate;


    @Override
    String toString() {
        return "ServerInfo{" +
                "id=" + id +
                ", serverType=" + serverType +
                ", serverTypeName='" + serverTypeName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", port=" + port +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
