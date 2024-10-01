#!/usr/bin/env groovy
package com.serverGroupInfo.service

import com.serverGroupInfo.dto.ServerGroupInfo
import com.serverGroupInfo.model.ServerGroupInfoBO

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

def static queryServerGroupInfoList(Map config = [:]) {
    return ServerGroupInfoBO.queryServerGroupInfoList(config)
}

//return this