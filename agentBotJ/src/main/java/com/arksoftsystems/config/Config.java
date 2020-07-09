package com.arksoftsystems.config;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Config {

    private static String DRIVER_DIR = System.getProperty("user.dir")
            + File.separator + "drivers";
    
    private static String CHROME_DRIVER_PATH = DRIVER_DIR + File.separator
            + "chromedriver";
    
    private static String FIREFOX_DRIVER_PATH = DRIVER_DIR + File.separator
            + "geckodriver";
    
    private static String modifyIfWindows(String inPath) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            return inPath + ".exe";
        } else {
            return inPath;
        }
    }

    public static WebDriver createChromeDriver(ChromeOptions options) {
        System.setProperty("webdriver.chrome.driver", modifyIfWindows(CHROME_DRIVER_PATH));
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    public static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", modifyIfWindows(CHROME_DRIVER_PATH));
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver createFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", modifyIfWindows(FIREFOX_DRIVER_PATH));
        WebDriver driver = new FirefoxDriver();
        return driver;
    }
}
