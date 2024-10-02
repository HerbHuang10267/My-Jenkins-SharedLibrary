#!/usr/bin/env groovy
def serverInfoToGroupMap(List serverInfoList) {

    if (serverInfoList == null || serverInfoList.isEmpty()) {
        return null
    }

    Map serverInfoGroupMap = [:]
    serverInfoList.each { server ->
        serverInfoGroupMap[server.serverGroup] ?: serverInfoGroupMap.put(server.serverGroup, [])
        List serverInfoStatusList = serverInfoGroupMap.get(server.serverGroup)
        serverInfoStatusList.add(server)
    }

    return serverInfoGroupMap
}

def collectCheckBoxToMap(String optionStr) {
    Map checkBoxMap = [:]
    String[] optionArray = optionStr.split(",")
    for (int i = 0; i < optionArray.size(); i += 2) {
        def key = optionArray[i]
        def value = optionArray[i + 1].toBoolean() ? 1 : 0
        checkBoxMap[key] = value
    }
    return checkBoxMap
}

return this