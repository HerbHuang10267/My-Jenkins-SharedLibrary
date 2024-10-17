#!/usr/bin/env groovy
import com.serverInfo.model.ServerInfoBO
import groovy.transform.Field

@Field ServerInfoBO serverInfoBO = new ServerInfoBO()

def queryServerInfoList(Map config = [:]) {
    return serverInfoBO.queryServerInfoList(config)
}

def updateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    return serverInfoBO.updateServerInfoStatus(config, serverInfoMap)
}

//return this