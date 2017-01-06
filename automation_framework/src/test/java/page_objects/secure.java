package page_objects;

import framework.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ben on 11/14/2016.
 */
public class secure extends PageObject {
    @FindBy(id = "flash")
    private WebElement alertMessage;

    public WebElement getAlertMessage(){
        return alertMessage;
    }
}
