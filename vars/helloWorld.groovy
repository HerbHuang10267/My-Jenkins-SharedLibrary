#!/usr/bin/groovy
import com.serverInfo.dao.ServerInfoDAO

def call() {
    echo "Hello World! My first Shared Library"
    new ServerInfoDAO().findServerInfo('SELECT * FROM serverinfo')
}