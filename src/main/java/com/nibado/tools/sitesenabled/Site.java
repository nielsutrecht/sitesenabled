package com.nibado.tools.sitesenabled;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class Site {
    private boolean defaultServer;
    private boolean proxyPass;
    private boolean ipv6;
    private String root;
    private String serverName;
    private String proxy;
    private int port;

    @JsonIgnore
    private boolean dirty;

    public boolean isDefaultServer() {
        return defaultServer;
    }

    public void setDefaultServer(boolean defaultServer) {
        this.defaultServer = defaultServer;
    }

    public boolean isDirty() {
        return dirty;
    }

    public boolean isProxyPass() {
        return proxyPass;
    }

    public void setProxyPass(boolean proxyPass) {
        this.proxyPass = proxyPass;
    }

    public boolean isIpv6() {
        return ipv6;
    }

    public void setIpv6(boolean ipv6) {
        this.ipv6 = ipv6;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("defaultServer", defaultServer);
        map.put("proxyPass", proxyPass);
        map.put("ipv6", ipv6);
        map.put("root", root);
        map.put("serverName", serverName);
        map.put("proxy", proxy);

        return map;
    }
}
