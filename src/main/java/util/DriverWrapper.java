package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverWrapper {
    private static WebDriver driver;

    private static void initDriver() {
        String driverPath;
        switch (System.getProperty("os.name")) {
            case "Windows":
                driverPath = DriverWrapper.class
                        .getResource("/drivers/windows/chromedriver.exe")
                        .getPath();
                System.setProperty("webdriver.chrome.driver", driverPath);
                break;
            case "Linux":
                driverPath = DriverWrapper.class
                        .getResource("/drivers/linux/chromedriver")
                        .getPath();
                System.setProperty("webdriver.chrome.driver", driverPath);
                break;
        }
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void nullDriver() {
        driver.quit();
    }
}
