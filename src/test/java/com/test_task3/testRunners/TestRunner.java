package com.test_task3.testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\cucumberFeatures\\test.feature",
        glue = {"src\\test\\java\\com\\test_task3\\testRunners"},
        monochrome = true,
        format = {"pretty"}
)
public class TestRunner {
}
