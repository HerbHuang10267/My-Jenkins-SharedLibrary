#!/usr/bin/groovy
import com.serverInfo.dao

def call() {
    echo "Hello World! My first Shared Library"
    new com.serverInfo.dao.ServerInfoDAO().findServerInfo('SELECT * FROM serverinfo')
}