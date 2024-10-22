#!/usr/bin/env groovy
import com.serverInfo.dto.ServerInfo

def call(ServerInfo serverInfo) {
    sh """
        scp -p56123 ./IP-COUNTRY-REGION-CITY-ISP.BIN root@$serverInfo.ip:$serverInfo.ip2LocationPath/IP-COUNTRY-REGION-CITY-ISP.BIN
    """
}
