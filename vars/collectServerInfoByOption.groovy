#!/usr/bin/env groovy
def call(String optionStr) {
    Map serverInfoMap = [:]
    String[] optionArray = optionStr.split(",")
    for (int i = 0; i < optionArray.size(); i += 2) {
        def key = optionArray[i]
        def value = optionArray[i + 1] ? 1 : 0
        serverInfoMap[key] = value
    }
    return serverInfoMap
}