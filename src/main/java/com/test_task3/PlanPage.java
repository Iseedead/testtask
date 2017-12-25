package com.test_task3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Base;

public class PlanPage extends Base {
    @FindBy(xpath = "//div[contains(@class, 'setup-header')]/h1")
    private WebElement greetingMessage;

    public String getGreetingMessage() {
        return greetingMessage.getText();
    }
}
