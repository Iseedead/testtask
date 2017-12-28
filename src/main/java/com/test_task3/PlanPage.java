package com.test_task3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Base;

public class PlanPage extends Base {
    @FindBy(css = "div[class*=setup-header] > h1")
    private WebElement greetingMessage;

    public String getGreetingMessage() {
        sleep(1000);
        return greetingMessage.getText();
    }
}
