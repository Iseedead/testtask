package util;

import org.openqa.selenium.support.PageFactory;

import static util.DriverWrapper.getDriver;

public abstract class Base {
    protected Base() {
        PageFactory.initElements(getDriver(), this);
    }

    public static void sleep(int mSec) {
        try {
            Thread.sleep(mSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
