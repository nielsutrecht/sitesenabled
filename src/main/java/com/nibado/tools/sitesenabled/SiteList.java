package com.nibado.tools.sitesenabled;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SiteList {
    private List<Site> sites;
    private Config config;

    public SiteList(Config config) {
        this.config = config;
        sites = new ArrayList<>();

        Site root = new Site();
        root.setDefaultServer(true);
        root.setIpv6(true);
        root.setProxy("http://localhost:8080");
        root.setProxyPass(true);
        root.setRoot("/var/www/niels.nu");
        root.setServerName("niels.nu");
        root.setPort(80);

        sites.add(root);
    }

    public void load() {

    }

    public void save() throws IOException {
        Main.createMapper().writeValue(config.getSitesFileObj(), sites);
    }
}
