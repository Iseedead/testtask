package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class DriverWrapper {
    private static WebDriver driver;

    private static void initDriver() {
        String browserName = System.getProperty("browser");
        String driverPath;
        URI path = null;
        File file;
        switch (System.getProperty("os.name")) {
            case "Linux":
                try {
                    path = DriverWrapper.class.getResource("/drivers/linux/" + browserName + "driver").toURI();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                file = new File(path);
                driverPath = file.getPath();
                file.setExecutable(true, false);
                System.setProperty("webdriver." + browserName + ".driver", driverPath);
                break;
            case "Windows":
                try {
                    path = DriverWrapper.class.getResource("/drivers/windows/" + browserName + "driver.exe").toURI();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                file = new File(path);
                driverPath = file.getPath();
                file.setExecutable(true, false);
                System.setProperty("webdriver." + browserName + ".driver", driverPath);
                break;
        }

        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "gecko":
                driver = new FirefoxDriver();
                break;
        }
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
