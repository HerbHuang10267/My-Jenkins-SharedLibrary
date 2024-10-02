#!/usr/bin/env groovy
import com.serverGroupInfo.dto.ServerGroupInfo
import com.serverGroupInfo.model.ServerGroupInfoBO
import groovy.transform.Field

@Field ServerGroupInfoBO serverGroupInfoBO = new ServerGroupInfoBO()

static void main(String[] args) {
    List<ServerGroupInfo> serverGroupInfoList = queryServerGroupInfoList(project: "local")
    println "==============="
    for (ServerGroupInfo serverGroupInfo : serverGroupInfoList) {
        println serverGroupInfo.toString()
    }

    List startUpOrderList = serverGroupInfoList.sort { it.startUpOrder }.collect()
    println startUpOrderList

    List shutDownOrderList = serverGroupInfoList.sort { it.shutDownOrder }.collect()
    println shutDownOrderList
    println startUpOrderList
}

List serverGroupInfoList = queryServerGroupInfoList(project: "local")
println "==============="
for (ServerGroupInfo serverGroupInfo : serverGroupInfoList) {
    println serverGroupInfo.toString()
}

List<ServerGroupInfo> shutDownServerOrderList = new ArrayList<>(serverGroupInfoList)
shutDownServerOrderList.sort(Comparator.comparingInt { it.shutDownOrder })
println shutDownServerOrderList

List<ServerGroupInfo> startUpServerOrderList = new ArrayList<>(serverGroupInfoList)
startUpServerOrderList.sort(Comparator.comparingInt { it.startUpOrder })
println startUpServerOrderList


//List startUpOrderList = serverGroupInfoList.sort { it.startUpOrder }.collect()
//println startUpOrderList
//
//List shutDownOrderList = serverGroupInfoList.sort { it.shutDownOrder }.collect()
//println shutDownOrderList

def queryServerGroupInfoList(Map config = [:]) {
    return serverGroupInfoBO.queryServerGroupInfoList(config)
}

//return this