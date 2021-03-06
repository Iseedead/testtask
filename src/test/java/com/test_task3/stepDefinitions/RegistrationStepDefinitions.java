package com.test_task3.stepDefinitions;

import com.test_task3.PlanPage;
import com.test_task3.SignUpPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

import static util.DriverWrapper.getDriver;
import static util.RandomGenerator.getRandomString;

public class RegistrationStepDefinitions {
    @Given("^User is on Sign Up page$")
    public void userIsOnSignUpPage() throws Throwable {
        getDriver().get("https://github.com/join");
        Assert.assertEquals("https://github.com/join", getDriver().getCurrentUrl());
    }

    @When("^User enters Valid Data to Sign Up Fields$")
    public void userEntersValidDataToSignUpFields() throws Throwable {
        SignUpPage sign = new SignUpPage();
        sign.fillUserName(getRandomString());
        sign.fillEmail(getRandomString() + "@gmail.com");
        sign.fillPassword(getRandomString());
    }

    @And("^Press the Create an account button$")
    public void pressTheCreateAnAccountButton() throws Throwable {
        SignUpPage sign = new SignUpPage();
        sign.signUp();
    }

    @Then("^Welcome Message \"([^\"]*)\" is Displayed$")
    public void welcomeMessageIsDisplayed(String welcomeMessage) throws Throwable {
        PlanPage planPage = new PlanPage();
        Assert.assertEquals(welcomeMessage, planPage.getGreetingMessage());
    }

    @And("^User enters Invalid Password to Sign Up Field and sees Error$")
    public void userEntersInvalidPassToSignUpField(List<InvalidData> invalidPass) throws Throwable {
        SignUpPage sign = new SignUpPage();
        for (InvalidData pass : invalidPass) {
            sign.fillPassword(pass.getValue());
            Assert.assertEquals(pass.getError(), sign.getFieldError("password"));
        }
    }

    @And("^User enters Invalid Email to Sign Up Field and sees Error$")
    public void userEntersInvalidEmailToSignUpField(List<InvalidData> invalidMail) throws Throwable {
        SignUpPage sign = new SignUpPage();
        for (InvalidData mail : invalidMail) {
            sign.fillEmail(mail.getValue());
            Assert.assertEquals(mail.getError(), sign.getFieldError("email"));
        }
    }

    @When("^User enters Invalid Username to Sign Up Field and sees Error$")
    public void userEntersInvalidUsernameToSignUpField(List<InvalidData> invalidName) throws Throwable {
        SignUpPage sign = new SignUpPage();
        for (InvalidData name : invalidName) {
            sign.fillUserName(name.getValue());
            Assert.assertEquals(name.getError(), sign.getFieldError("username"));
        }
    }

    @Then("^Error Message \"([^\"]*)\" is displayed$")
    public void errorMessageIsDisplayed(String errorMessage) throws Throwable {
        SignUpPage sign = new SignUpPage();
        Assert.assertEquals(errorMessage, sign.getGeneralRegError());
    }
}
