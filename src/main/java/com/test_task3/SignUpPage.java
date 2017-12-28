package com.test_task3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Base;

public class SignUpPage extends Base {
    @FindBy(css = "input#user_login")
    private WebElement userNameField;
    @FindBy(css = "input#user_email")
    private WebElement emailField;
    @FindBy(css = "input#user_password")
    private WebElement passField;
    @FindBy(css = "button#signup_button")
    private WebElement singUpButton;
    @FindBy(css = "div[class*=my-3]")
    private WebElement generalRegError;

    private By fieldError = By.cssSelector("dd.error");

    public void fillUserName(String username) {
        userNameField.clear();
        userNameField.sendKeys(username);
    }

    public void fillEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void fillPassword(String pass) {
        passField.clear();
        passField.sendKeys(pass);
    }

    public void signUp() {
        jsClick(singUpButton);
    }

    public String getFieldError(String field) {
        switch (field) {
            case "username":
                return getElementGrandParent(userNameField).findElement(fieldError).getText();
            case "email":
                return getElementGrandParent(emailField).findElement(fieldError).getText();
            case "password":
                return getElementGrandParent(passField).findElement(fieldError).getText();
        }
        return null;
    }

    public String getGeneralRegError() {
        return generalRegError.getText();
    }
}
