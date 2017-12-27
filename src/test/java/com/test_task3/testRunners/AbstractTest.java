package com.test_task3.testRunners;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static util.DriverWrapper.getDriver;
import static util.DriverWrapper.nullDriver;

public abstract class AbstractTest {
    @BeforeClass
    public static void preCondition() {
        // cause of bug in webdriver
        if (!System.getProperty("browser").equals("opera")) {
            getDriver().manage().window().maximize();
        }
    }
    @AfterClass
    public static void postCondition() {
        nullDriver();
    }
}
