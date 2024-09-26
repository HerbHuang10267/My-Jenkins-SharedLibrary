#!/usr/bin/env groovy
def call(String optionStr) {
    Map checkBoxMap = [:]
    String[] optionArray = optionStr.split(",")
    for (int i = 0; i < optionArray.size(); i += 2) {
        def key = optionArray[i]
        def value = optionArray[i + 1].toBoolean() ? 1 : 0
        checkBoxMap[key] = value
    }
    return checkBoxMap
}