package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class DriverWrapper {
    private static WebDriver driver;

    private static void switchDriver() {
        String browserName = System.getProperty("browser");
        String driverPath;
        URI path = null;
        File file;
        switch (System.getProperty("os.name").split(" ")[0]) {
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
            case "edge":
                driver = new EdgeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            switchDriver();
        }
        return driver;
    }

    public static void nullDriver() {
        driver.quit();
    }
}
