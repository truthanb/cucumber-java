package framework;


import org.openqa.selenium.WebDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class Env {
    private static WebDriver browser;
    public static Map<String, String> config = new HashMap<>();
    private static int time_out_duration = 10;
    private static boolean config_loaded = false;

    private Env(){}

    public static String getBaseUrl() { return config.get("base_url"); }

    public static WebDriver getBrowser() { return browser; }

    public static WebDriver setBrowser(){
        loadConfig();
        String browser_type = config.get("browser");
        if(browser_type.toLowerCase().equals("no browser")) {
            BrowserFactory.closeAllDrivers();
            return null;
        }
        browser = BrowserFactory.getBrowser(browser_type);
        setTimeOutDuration(time_out_duration);
        return browser;
    }

    public static WebDriver setBrowser(String browser_type){
        loadConfig();
        config.put("browser", browser_type);
        if(browser_type.toLowerCase().equals("no browser")) {
            BrowserFactory.closeAllDrivers();
            return null;
        }
        browser = BrowserFactory.getBrowser(browser_type);
        setTimeOutDuration(time_out_duration);
        return browser;
    }

    private static void setTimeOutDuration(int tod) {
        time_out_duration = tod;
        browser.manage().timeouts().implicitlyWait(time_out_duration, TimeUnit.SECONDS);
    }

    static void loadConfig() {
        if (config_loaded) {
             return;
        }
        try{
            InputStream config_file = new FileInputStream(new File("C:\\Users\\Ben\\IdeaProjects\\cucumber-selenium\\automation_framework\\src\\test\\resources\\data\\configuration.yml"));
            Yaml yaml = new Yaml();
            config = (Map<String, String>) yaml.load(config_file);
            time_out_duration = Integer.parseInt(config.get("time_out_duration"));
            config_loaded = true;
            config_file.close();
        }catch(IOException e){
            System.out.println("Could not load config file. Using default settings.");
        }
    }
}
