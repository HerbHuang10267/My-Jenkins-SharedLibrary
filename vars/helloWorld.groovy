#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo
import com.serverInfo.dao.ServerInfoDAO

def test1() {
    println("Hello World! test1 ")
}

def test2() {
    println("Hello World! test2 ")
    ServerInfoDAO serverInfoDAO = new ServerInfoDAO(this)
    List<ServerInfo> serverInfoList = serverInfoDAO.findServerInfo('SELECT * FROM serverinfo')
}