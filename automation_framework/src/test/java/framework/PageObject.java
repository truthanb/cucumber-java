package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

 public abstract class PageObject {
    protected WebDriver browser;

    protected PageObject(){
        browser = Env.getBrowser();
        PageFactory.initElements(browser, this);
    }
}
