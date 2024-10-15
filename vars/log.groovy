#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo
import com.serverGroupInfo.dto.ServerGroupInfo

def logServerInfo(List<ServerInfo> serverInfoList) {
    StringBuilder sb = new StringBuilder()
    for (ServerInfo serverInfo : serverInfoList) {
        sb.append("id:").append(serverInfo.id).append(", ")
        sb.append("hostName:").append(serverInfo.hostName).append(", ")
        sb.append("ip:").append(serverInfo.ip).append(", ")
        sb.append("serverType:").append(serverInfo.serverType).append(", ")
        sb.append("serverGroup:").append(serverInfo.serverGroup).append(", ")
        sb.append("tomcatPath:").append(serverInfo.tomcatPath).append(", ")
        sb.append("filebeatConfigPath:").append(serverInfo.filebeatConfigPath).append(", ")
        sb.append("startUpShellPath:").append(serverInfo.startUpShellPath).append(", ")
        sb.append("shutDownShellPath:").append(serverInfo.shutDownShellPath).append(", ")
        sb.append("deployShellPath:").append(serverInfo.deployShellPath).append(", ")
        sb.append("filebeatShellPath:").append(serverInfo.filebeatShellPath).append(", ")
        sb.append("ip2LocationPath:").append(serverInfo.ip2LocationPath).append(", ")
        sb.append("updateDate:").append(serverInfo.updateDate).append("\n")
    }
    println sb.toString()
}

def logServerGroupInfo(List<ServerGroupInfo> serverGroupInfoList) {
    StringBuilder sb = new StringBuilder()
    for (ServerGroupInfo serverGroupInfo : serverGroupInfoList) {
        sb.append("serverGroup:").append(serverGroupInfo.serverGroup).append(", ")
        sb.append("shutDownOrder:").append(serverGroupInfo.shutDownOrder).append(", ")
        sb.append("startUpOrder:").append(serverGroupInfo.startUpOrder).append("\n")
    }
    println sb.toString()
}