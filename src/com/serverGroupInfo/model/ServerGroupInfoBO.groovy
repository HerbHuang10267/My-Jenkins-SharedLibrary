#!/usr/bin/env groovy
package com.serverGroupInfo.model

import com.common.database.DBQueryRunner
import com.serverGroupInfo.dao.ServerGroupInfoDAO
import com.serverGroupInfo.dto.ServerGroupInfo
import groovy.transform.Field

@Field ServerGroupInfoDAO serverGroupInfoDAO = new ServerGroupInfoDAO()

def queryServerGroupInfoList(Map config = [:]) {
    List<ServerGroupInfo> serverGroupInfoList = serverGroupInfoDAO.queryServerGroupInfoList(config)
    return serverGroupInfoList
}

return this