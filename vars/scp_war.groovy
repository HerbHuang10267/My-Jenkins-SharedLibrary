#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {

    sh '''
        echo "Hello scp war ${serverInfo.ip}"
        pwd
    '''
//    def remoteScript = "root@${serverInfo.ip}:${serverInfo.tomcatPath}/ROOT.zip"
//    def command = ["scp", "target/ROOT.war", remoteScript]
//
//    def proc = command.execute()
//    def sout = new StringBuilder(), serr = new StringBuilder()
//    proc.consumeProcessOutput(sout, serr)
//    proc.waitForOrKill(60 * 1000)
//    if (proc.exitValue() == 0) {
//        println("sout: ${sout}")
//    } else {
//        error("error: ${serr}")
//    }
}
