package util;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverWrapper {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = switchDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver switchDriver() {
        driver = null;
        if (System.getProperty("mobile").equals("false")) {
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
                    break;
                case "Windows":
                    try {
                        path = DriverWrapper.class.getResource("/drivers/windows/" + browserName + "driver.exe").toURI();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            file = new File(path);
            driverPath = file.getPath();
            file.setExecutable(true, false);
            System.setProperty("webdriver." + browserName + ".driver", driverPath);

            driver = getBrowser(browserName);
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("deviceName", "m3 note L681H");
            capabilities.setCapability("platformVersion", "5.1");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("clearSystemFiles", "true");
            capabilities.setCapability("fullReset", "false");
            capabilities.setCapability("noReset", "true");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");

            try {
                driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return driver;
    }

    private static WebDriver getBrowser(String browserName) {
        driver = null;
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
        return driver;
    }

    public static void nullDriver() {
        driver.quit();
    }
}
