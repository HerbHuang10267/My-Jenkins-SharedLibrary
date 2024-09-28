#!/usr/bin/env groovy
import com.common.database.DBQueryRunner

def test1() {
    println("Hello World! test1 ")
    return "Hello World! test1"
}

def test2() {
    println helloWorld2.test()
    return helloWorld2.test()
}

def callOtherMethod(Map config = [:]) {
    println("Hello World! test2 ")
    List result = serverInfoService.queryServerInfoList(config)
    return result
}

def callOtherMethod2(Map config = [:]) {
    println("Hello World! callOtherMethod2 ")
    DBQueryRunner dBQueryRunner = new DBQueryRunner()
    def result = dBQueryRunner.executeSQL(config)
    println("result: ${result}")
    return result
}