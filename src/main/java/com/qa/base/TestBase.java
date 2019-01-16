package com.qa.base;

import com.qa.util.TestUtil;
import com.qa.util.WebDriverListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static EventFiringWebDriver eventFiringWebDriver;
    public static WebDriverEventListener eventListener;

    /**
     * Constructor is used to Initialize property
     */
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
                    + "/src/main/java/com/qa/config/config.properties");
            prop.load(fileInputStream);
        } catch (FileNotFoundException fNException) {
            fNException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Start Browser, No need to mention ChromeDriver or FireFox Driver Path
     *
     * @param url
     */
    public static void initialization(String url) {

        String browser = prop.getProperty("browser");
        WebDriverManager.chromedriver().setup();
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventListener = new WebDriverListener();
        eventFiringWebDriver.register(eventListener);
        driver = eventFiringWebDriver;
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(url);
    }
}

