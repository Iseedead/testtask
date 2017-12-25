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
    @When("^User navigates to Sign Up Page$")
    public void userNavigatesToSignUpPage() throws Throwable {
        getDriver().get("https://github.com/join");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://github.com/join");
    }

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
        Assert.assertEquals(planPage.getGreetingMessage(), welcomeMessage);
    }

    @When("^User enters Invalid Password to Sign Up Fields$")
    public void userEntersInvalidDataToSignUpFields(List<InvalidPassword> invalidPass) throws Throwable {
        SignUpPage sign = new SignUpPage();
        for (InvalidPassword pass : invalidPass) {
            Thread.sleep(1000);
            sign.fillUserName(pass.getValue());
            Thread.sleep(1000);
            Assert.assertEquals(pass.getError(), sign.getSingleError());
            sign.clearFields();
            Thread.sleep(1000);
        }
    }
}
