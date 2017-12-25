package com.test_task3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Base;

public class SignUpPage extends Base {
    @FindBy(xpath = "//input[@name='user[login]']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@name='user[email]']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='user[password]']")
    private WebElement passField;

    @FindBy(xpath = "//button[@id='signup_button']")
    private WebElement singUpButton;

    @FindBy(xpath = "//dd[@class='error']")
    private WebElement singleError;

    public void fillUserName(String username) {
        userNameField.sendKeys(username);
    }

    public void fillEmail(String email) {
        emailField.sendKeys(email);
    }

    public void fillPassword(String pass) {
        passField.sendKeys(pass);
    }

    public void signUp() {
        System.out.println("Sign Up Button Clicked");
        singUpButton.click();
    }

    public String getSingleError() {
        return singleError.getText();
    }

    public void clearFields() {
        userNameField.clear();
        emailField.clear();
        passField.clear();
    }
}
