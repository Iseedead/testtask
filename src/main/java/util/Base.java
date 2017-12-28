package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

    protected static void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click()", element);
    }

    protected WebElement getElementGrandParent(WebElement childElement) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (WebElement) js.executeScript("return arguments[0].parentNode.parentNode;", childElement);
    }
}
