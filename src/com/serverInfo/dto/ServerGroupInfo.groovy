package com.serverInfo.dto

class ServerGroupInfo {

    String serverGroup;
    int shutDownOrder;
    int startUpOrder;


    @Override
    String toString() {
        return "ServerGroupInfo{" +
                "serverGroup='" + serverGroup + '\'' +
                ", shutDownOrder=" + shutDownOrder +
                ", startUpOrder=" + startUpOrder +
                '}';
    }
}
