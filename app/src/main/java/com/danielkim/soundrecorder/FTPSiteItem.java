package com.danielkim.soundrecorder;



public class FTPSiteItem {
    public String host, remoteDir, username, password;
    public FTPSiteItem(String host, String remoteDir, String username, String password) {
        this.host = host;
        this.remoteDir = remoteDir;
        this.username = username;
        this.password = password;
    }
}
