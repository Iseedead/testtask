package util;

import org.openqa.selenium.support.PageFactory;

import static util.DriverWrapper.getDriver;

public abstract class Base {
    protected Base() {
        PageFactory.initElements(getDriver(), this);
    }
}
