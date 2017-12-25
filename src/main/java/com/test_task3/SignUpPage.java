package com.test_task3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import util.Base;

import static util.DriverWrapper.getDriver;

public class SignUpPage extends Base {
    @FindBy(xpath = "//input[@id='user_login']")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@id='user_email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@id='user_password']")
    private WebElement passField;
    @FindBy(xpath = "//button[@id='signup_button']")
    private WebElement singUpButton;
    @FindBy(xpath = "//div[contains(@class, 'my-3')]")
    private WebElement generalRegError;

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
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("scroll(0, 250);");
        singUpButton.click();
    }

    public String getFieldError(String field) {
        switch (field) {
            case "username":
                return userNameField.findElement(By.xpath("../../dd[@class='error']")).getText();
            case "email":
                return emailField.findElement(By.xpath("../../dd[@class='error']")).getText();
            case "password":
                return passField.findElement(By.xpath("../../dd[@class='error']")).getText();
        }
        return null;
    }

    public String getGeneralRegError() {
        return generalRegError.getText();
    }
}
