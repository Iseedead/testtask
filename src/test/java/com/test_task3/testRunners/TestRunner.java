package com.test_task3.testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/cucumberFeatures",
        glue = {"classpath:com/test_task3/stepDefinitions"},
        monochrome = true,
        format = {"json:cucumberReports/cucumber.json"}
)
public class TestRunner extends AbstractTest{
}
