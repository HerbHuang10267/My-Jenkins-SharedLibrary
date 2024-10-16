#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {
    log.logServerInfo(serverInfo)

    def remoteUser = "root@${serverInfo.ip}"
//    def remoteScript = serverInfo.shutDownShellPath
    def remoteScript = "pwd"

    if (remoteUser == null || remoteUser == "" ||
        remoteScript == null || remoteScript == "") {
        throw new Exception("remoteUser or remoteScript is null or empty")
    }

    // def command = ["ssh", remoteUser, "sh", remoteScript]
    def command = ["ssh", remoteUser, remoteScript]

    def proc = command.execute()
    def sout = new StringBuilder(), serr = new StringBuilder()
    proc.consumeProcessOutput(sout, serr)
    proc.waitForOrKill(60 * 1000)
    println("sout: ${sout}")
    println("sout: ${serr}")
}
