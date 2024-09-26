#!/usr/bin/env groovy
def test1() {
    println("Hello World! test1 ")
    return "Hello World! test1"
}

def callOtherMethod(Map config = [:]) {
    println("Hello World! test2 ")
    def result = serverInfoService.executeSQL(config)
    return result
}
