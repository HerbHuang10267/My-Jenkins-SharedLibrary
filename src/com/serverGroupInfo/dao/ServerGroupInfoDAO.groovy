#!/usr/bin/env groovy
package com.serverGroupInfo.dao

import com.common.database.DBQueryRunner

def static queryServerGroupInfoList(Map config = [:]) {
    def data = DBQueryRunner.executeSQL(project: config.project, sql: "SELECT * FROM servergroupinfo;")
    return data
}

return this