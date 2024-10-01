#!/usr/bin/env groovy
package com.serverInfo.dao

import com.common.database.DBQueryRunner

def queryServerInfoList(Map config = [:]) {
    def con = [project: config.project, sql: "SELECT * FROM serverinfo ORDER BY hostname;"]
    def data = new DBQueryRunner().executeSQL(con)
    return data
}

def updateServerInfoStatus(Map config = [:], Map serverInfoMap = [:]) {
    StringBuilder sqlBuilder = new StringBuilder()
    serverInfoMap.each { key, value ->
        sqlBuilder.append("UPDATE serverinfo SET status = ${value}, updatedate = date('now') WHERE hostname = '${key}';")
    }
    new DBQueryRunner().executeUpdate(project: config.project, sql: sqlBuilder.toString())
}

def queryActiveHostName(Map config = [:]) {
    def con = [project: config.project, sql: "SELECT hostname FROM serverinfo WHERE status = 1 ORDER BY hostname;"]
    def data = new DBQueryRunner().executeSQL(con)
    return data
}

return this