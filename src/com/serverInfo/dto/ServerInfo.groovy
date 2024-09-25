package com.serverInfo.dto

class ServerInfo {

    int id;
    int serverType;
    String name;
    String port;
    String ip;
    int status;
    String updateDate;

    @Override
    String toString() {
        return "ServerInfo{" +
                "id=" + id +
                ", serverType=" + serverType +
                ", name='" + name + '\'' +
                ", port='" + port + '\'' +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                ", updateDate=" + updateDate +
                '}';
    }
}
