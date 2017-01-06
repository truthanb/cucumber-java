package framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;


public class Utils {
    public static void highlightElement(WebElement element){
        WebDriver driver = Env.getBrowser();
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "background: yellow; border: 2px solid red;");
    }

    public static void waitForJQuery(){
        WebDriver driver = Env.getBrowser();
        boolean isJqueryUsed = (boolean)(((JavascriptExecutor)driver).executeScript("return (typeof(jQuery) != 'undefined'"));
        if(isJqueryUsed){
            while(true){
                boolean ajaxIsComplete = (boolean)(((JavascriptExecutor)driver).executeScript("return jQuery.active == 0"));
                if (ajaxIsComplete) break;
                try{
                    Thread.sleep(100);
                }catch (InterruptedException ignored){}
            }
        }
    }

    static void loadLog4J2Config() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        File file = new File("C:\\Users\\Ben\\IdeaProjects\\cucumber-selenium\\automation_framework\\src\\test\\resources\\log4j2.xml");
        context.setConfigLocation(file.toURI());
    }

}
