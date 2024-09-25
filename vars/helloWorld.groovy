#!/usr/bin/env groovy
def test1() {
    println("Hello World! test1 ")
}

def callOtherMethod() {
    println("Hello World! test2 ")
    def result = serverInfoService.executeSQL("SELECT * FROM serverinfo")
    println(result)
}
