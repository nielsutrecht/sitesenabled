package com.nibado.tools.sitesenabled;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Main {
    public static void main(String... argv) throws Exception {
        Config config = new Config();
        new SiteList(config).save();
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates/"));
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Site root = new Site();
        root.setDefaultServer(true);
        root.setIpv6(true);
        root.setProxy("http://localhost:8080");
        root.setProxyPass(true);
        root.setRoot("/var/www/niels.nu");
        root.setServerName("niels.nu");
        root.setPort(80);

        Template temp = cfg.getTemplate("template.ftl");
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
    }

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }
}
