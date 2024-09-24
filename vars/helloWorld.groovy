#!/usr/bin/groovy
import com.serverInfo.dao.ServerInfoDAO

def call() {
    ServerInfoDAO.findServerInfo('SELECT * FROM serverinfo')
    echo "Hello World! My first Shared Library"
}