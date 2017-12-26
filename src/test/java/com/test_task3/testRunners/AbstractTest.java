package com.test_task3.testRunners;

import org.junit.AfterClass;

import static util.DriverWrapper.nullDriver;

public abstract class AbstractTest {
    @AfterClass
    public static void postCondition() {
        nullDriver();
    }
}
