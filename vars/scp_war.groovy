#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {

    def remoteScript = "root@${serverInfo.ip}:${serverInfo.tomcatPath}/ROOT.zip"
    def command = ["scp", "target/ROOT.war", remoteScript]

    def proc = command.execute()
    def sout = new StringBuilder(), serr = new StringBuilder()
    proc.consumeProcessOutput(sout, serr)
    proc.waitForOrKill(60 * 1000)

    println("sout: ${sout}")
    println("serr: ${serr}")

    if (proc.exitValue() !=0 ) {
        System.exit(-1)
    } else {
        System.exit(0)
    }
}
