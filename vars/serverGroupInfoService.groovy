#!/usr/bin/env groovy
import com.serverGroupInfo.dto.ServerGroupInfo
import com.serverGroupInfo.model.ServerGroupInfoBO
import groovy.transform.Field

@Field ServerGroupInfoBO serverGroupInfoBO = new ServerGroupInfoBO()

def queryServerGroupInfoList(Map config = [:]) {
    return serverGroupInfoBO.queryServerGroupInfoList(config)
}

//return this