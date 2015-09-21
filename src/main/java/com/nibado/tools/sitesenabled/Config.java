package com.nibado.tools.sitesenabled;

import java.io.File;
import java.io.IOException;

public class Config {
    private String nginxDir;
    private String sitesFile;
    private int defaultPort;
    private String defaultProxyHost;
    private int defaultProxyPort;
    private boolean defaultAddWww;
    private String defaultFileName;

    public Config() {
        nginxDir = "/etc/nginx/";
        sitesFile = "{userHome}/.sitesenabled/sites.json";
        defaultPort = 80;
        defaultProxyHost = "localhost";
        defaultProxyPort = 8080;
        defaultAddWww = true;
        defaultFileName = "${serverName}";
    }

    public String getNginxDir() {
        return nginxDir;
    }

    public void setNginxDir(String nginxDir) {
        this.nginxDir = nginxDir;
    }

    public String getSitesFile() {
        return sitesFile;
    }

    public void setSitesFile(String sitesFile) {
        this.sitesFile = sitesFile;
    }

    public int getDefaultPort() {
        return defaultPort;
    }

    public void setDefaultPort(int defaultPort) {
        this.defaultPort = defaultPort;
    }

    public String getDefaultProxyHost() {
        return defaultProxyHost;
    }

    public void setDefaultProxyHost(String defaultProxyHost) {
        this.defaultProxyHost = defaultProxyHost;
    }

    public int getDefaultProxyPort() {
        return defaultProxyPort;
    }

    public void setDefaultProxyPort(int defaultProxyPort) {
        this.defaultProxyPort = defaultProxyPort;
    }

    public boolean isDefaultAddWww() {
        return defaultAddWww;
    }

    public void setDefaultAddWww(boolean defaultAddWww) {
        this.defaultAddWww = defaultAddWww;
    }

    public String getDefaultFileName() {
        return defaultFileName;
    }

    public void setDefaultFileName(String defaultFileName) {
        this.defaultFileName = defaultFileName;
    }

    public void save() throws IOException {
        Main.createMapper().writeValue(getConfigFile(), this);
    }

    File getSitesFileObj() {
        String f = sitesFile.replace("{userHome}", System.getProperty("user.home"));

        return new File(f);
    }

    private static File getConfigFile() {
        File file = new File(System.getProperty("user.home"), "/.sitesenabled/");
        file.mkdirs();
        file = new File(file, "config.json");

        return file;
    }
}
