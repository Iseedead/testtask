package util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;

import static util.DriverWrapper.getDriver;

public abstract class Base {
    protected Base() {
        PageFactory.initElements(getDriver(), this);
    }

    protected static void sleep(int mSec) {
        try {
            Thread.sleep(mSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected static void jsClick(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
//        js.executeScript("document.querySelector('')")

    }
}
