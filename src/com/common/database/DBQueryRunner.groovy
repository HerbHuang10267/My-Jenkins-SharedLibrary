#!/usr/bin/env groovy
package com.common.database

/*
 * Execute SQL query
 * @param config.project: project name
 * @param config.sql: SQL
 */
def executeSQL(Map config = [:]) {
    try {
        def dbPath = getDataBasePath(config.project)
        def sql = config.sql
//        println("dbPath: ${dbPath}")
//        println("sql: ${sql}")
        def command = ["sqlite3", dbPath, ".headers on", sql]
        def proc = command.execute()
        def sout = new StringBuilder(), serr = new StringBuilder()
        proc.consumeProcessOutput(sout, serr)
        proc.waitForOrKill(10 * 1000)
//        println("sout: ${sout}")
        return sout.toString()
    } catch (Exception e) {
        println("SQLite excute SQL failed: ${e.getMessage()} ")
    }
    return null
}

/*
 * Execute SQL query
 * @param config.project: project name
 * @param config.sql: SQL
 */
def executeUpdate(Map config = [:]) {
    StringBuilder sqlBuilder = new StringBuilder()
    sqlBuilder.append("BEGIN TRANSACTION;")
    sqlBuilder.append(config.sql)
    sqlBuilder.append("COMMIT;")
    executeSQL(project: config.project, sql: sqlBuilder.toString())
}

def getDataBasePath(String project) {
    switch (project) {
        case "local":
            return 'C:/Users/SAHerbHuangT14/9w-db.sqlite'
        case "9w":
            return '/var/jenkins_home/sqlite_data/9w-db.sqlite'
        case "vk":
            return '/var/jenkins_home/sqlite_data/vk-db.sqlite'
        case "mini":
            return '/var/jenkins_home/sqlite_data/mini-db.sqlite'
        default:
            return null
    }
}

return this