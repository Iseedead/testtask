package util;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverWrapper {
    private static WebDriver driver;
    private static String androidDevice = "m3 note L681H";
    private static String androidVersion = "5.1";
    private static String osName = System.getProperty("os.name").toLowerCase().split(" ")[0];
    private static String osArch = System.getProperty("os.arch").toLowerCase();
    private static String browserName = System.getProperty("browser");
    private static URI path = null;
    private static File file;

    public static synchronized WebDriver getDriver() {
        if (driver == null) {
            driver = switchDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver switchDriver() {
        driver = null;
        if (System.getProperty("mobile").equals("false")) {
            switch (osName) {
                case "linux":
                    try {
                        path = DriverWrapper.class.getResource("/drivers/linux/" + browserName + "driver").toURI();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    break;
                case "windows":
                    switch (osArch.substring(osArch.length() - 2)) {
                        case "64":
                            try {
                                path = DriverWrapper.class
                                        .getResource("/drivers/windows/64/" + browserName + "driver.exe")
                                        .toURI();
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "32":
                            try {
                                path = DriverWrapper.class
                                        .getResource("/drivers/windows/32/" + browserName + "driver.exe")
                                        .toURI();
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
            }
            file = new File(path);
            file.setExecutable(true, false);
            System.setProperty("webdriver." + browserName + ".driver", file.getPath());

            driver = getBrowser(browserName);
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("deviceName", androidDevice);
            capabilities.setCapability("platformVersion", androidVersion);
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
            case "opera":
                OperaOptions options = new OperaOptions();
                switch (osName) {
                    case "windows":
                        options.setBinary("C:\\Program Files\\Opera\\launcher.exe");
                        break;
                    case "linux":
                        options.setBinary("/usr/bin/opera");
                        break;
                }
                driver = new OperaDriver(options);
                break;
//            case "edge":
//                driver = new EdgeDriver();
//                break;
        }
        return driver;
    }

    public static void nullDriver() {
        if (browserName.equals("opera")) {
            try {
                Runtime.getRuntime().exec("taskkill /f /im opera.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}
