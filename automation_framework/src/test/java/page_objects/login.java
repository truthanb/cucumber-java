package page_objects;

import framework.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ben on 11/14/2016.
 */
public class login extends PageObject {
    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@class = 'radius']/i")
    private WebElement submit;

    @FindBy(id = "login")
    private WebElement loginForm;

    public WebElement getUserName(){
        return userName;
    }

    public WebElement getPassWord(){
        return password;
    }
    public WebElement getSubmit(){
        return submit;
    }
}
