#!/usr/bin/env groovy
import com.serverInfo.model.ServerInfoBO

def test1() {
    println("Hello World! test1 ")
    return "Hello World! test1"
}

def test2() {
    println("Hello World! test2 ")
    return "Hello World! test2"
}

def callOtherMethod(Map config = [:]) {
    println("Hello World! test2 ")
    def result = serverInfoService.executeSQL(config)
    return result
}

def callSrcServerInfoBO() {
    println("Hello World! test3 ")
    def result = ServerInfoBO.helloServerInfoBO()
    return result
}
