#!/usr/bin/env groovy
package com.serverGroupInfo.dao

import com.common.database.DBQueryRunner

def queryServerGroupInfoList(Map config = [:]) {
    def data = new DBQueryRunner().executeSQL(project: config.project, sql: "SELECT * FROM servergroupinfo;")
    return data
}

return this