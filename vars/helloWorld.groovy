#!/usr/bin/env groovy

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
    def result = serverInfoService.queryServerInfoList(config)
    return result
}