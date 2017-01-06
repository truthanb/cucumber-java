package framework;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Hooks {
    private static boolean ranOnce = false;

    @After
    public void afterEach(){
        try{
            WebDriver browser = Env.getBrowser();
            browser.manage().deleteAllCookies();
            browser.findElement(By.id("logout")).click();
            browser.manage().deleteAllCookies();
        }catch (Exception e){}
    }

    @Before
    public void beforeAll(){
        if(!ranOnce){
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() { afterAllHook(); }
            });
            beforeAllHook();
            ranOnce = true;
        }
    }

    private void beforeAllHook() {
        Utils.loadLog4J2Config();
        Env.loadConfig();
        Env.setBrowser();
    }

    private void afterAllHook() {
        //ReportGenerator.buildMasterthoughtReport();
        BrowserFactory.closeAllDrivers();
    }
}
