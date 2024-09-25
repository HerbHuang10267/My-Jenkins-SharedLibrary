package com.serverInfo.dto

class ServerInfo {

    private int id;
    private int serverType;
    private String name;
    private String port;
    private String ip;
    private int status;
    private String updateDate;

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

    int getServerType() {
        return serverType
    }

    void setServerType(int serverType) {
        this.serverType = serverType
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getPort() {
        return port
    }

    void setPort(String port) {
        this.port = port
    }

    String getIp() {
        return ip
    }

    void setIp(String ip) {
        this.ip = ip
    }

    int getStatus() {
        return status
    }

    void setStatus(int status) {
        this.status = status
    }

    String getUpdateDate() {
        return updateDate
    }

    void setUpdateDate(String updateDate) {
        this.updateDate = updateDate
    }

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
