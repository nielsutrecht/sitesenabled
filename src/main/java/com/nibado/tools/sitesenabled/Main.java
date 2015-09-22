package com.nibado.tools.sitesenabled;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;

import static java.util.Arrays.asList;

public class Main {
    private static final String VERSION = "1.0";

    private Config config;
    private boolean runGui;
    private SiteList sites;

    public Main(OptionSet options) {
        config = new Config((File)options.valueOf("c"));
        runGui = options.has("g");
    }

    public void runGui() {
        System.out.println("GUI");
    }

    public void runConsole() {
        System.out.println("Console");
    }

    public static void main(String... argv) throws Exception {
        OptionParser parser = getOptionParser();

        OptionSet options = parser.parse(argv);

        if(options.has("v")) {
            System.out.println("sitesenabled v" + VERSION);
        }
        else if(options.has("h")) {
            parser.printHelpOn(System.out);
        }
        else {
            Main main = new Main(options);
            if(options.has("g")) {
                main.runGui();
            }
            else {
                main.runConsole();
            }
        }
        System.exit(0);
        System.out.println(options.has("g"));
        System.out.println(options.has("v"));
        System.out.println(options.has("h"));
        System.out.println(options.has("c"));
        System.out.println(options.valueOf("c"));

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

    private static OptionParser getOptionParser() {
        OptionParser parser = new OptionParser() {
            {
                acceptsAll(asList("c", "config"), "override config file").withRequiredArg().ofType(File.class)
                        .describedAs("configfile").defaultsTo(new File(System.getProperty("user.home") + "/.sitesenabled/config.json"));
                acceptsAll(asList("g", "gui"), "run GUI");
                acceptsAll(asList("v", "version"), "print version");
                acceptsAll(asList("h", "help", "?"), "show help").forHelp();
            }
        };

        return parser;
    }

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }
}
