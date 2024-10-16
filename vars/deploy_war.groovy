#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {
    
    def remoteUser = "root@${serverInfo.ip}"
    def remoteScript = serverInfo.deployShellPath
    def command = ["ssh", remoteUser, "sh", remoteScript]

    def proc = command.execute()
    def sout = new StringBuilder(), serr = new StringBuilder()
    proc.consumeProcessOutput(sout, serr)
    proc.waitForOrKill(60 * 1000)
    println("sout: ${sout}")
    println("serr: ${serr}")
}
