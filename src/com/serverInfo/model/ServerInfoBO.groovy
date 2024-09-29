package com.serverInfo.model

import groovy.transform.Field
@Field def db = new GroovyClassLoader(getClass().getClassLoader()).parseClass(new File("../../../com/common/database/DBQueryRunner.groovy")).newInstance()

def queryActiveHostName(Map config = [:]) {
    def con = [project: config.project, sql: "SELECT hostname FROM serverinfo WHERE status = 1 ORDER BY servertype;"]
    def data = db.executeSQL(con)
    return parseHostNameList(data)
}

def parseHostNameList(String data) {

    if (data == null || data.isEmpty()) {
        return null
    }

    List hostNameList = []
    // 去除 header 及分隔線
    def lines = data.readLines().drop(1)
    lines.each { line ->
        def fields = line.split(/\|/, -1) // 使用 | 分隔資料
        if (fields.size() >= 1) {
            hostNameList.add(fields[0])
        }
    }
    return hostNameList
}

return this