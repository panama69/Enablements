package com.hpe.srf;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * Created by Administrator on 3/23/2017.
 */
public class SeleniumRemote {

    private static RemoteWebDriver driver;
//    private static WebDriver local;
    @BeforeClass
    public static void configToSRF()throws MalformedURLException{
        DesiredCapabilities capabilities = DesiredCapabilities.chrome(); // This is the type of the browser that you will use in your test

        capabilities.setCapability("SRF_CLIENT_ID", "<Client ID>"); // Your Client ID here
        capabilities.setCapability("SRF_CLIENT_SECRET", "<Client Seceret>"); // Your Client Secret here
        capabilities.setCapability("testName","<Test Name>"); // This will be the name of the test that will appear in SRF

        capabilities.setVersion("<Browser Version>"); // Change this to your browser version. ("54")
        capabilities.setCapability("platform","<OS Version>"); // The OS that your script will run on. "Windows 7"

        URL FTAAS_URL = new URL("https","ftaas.saas.hpe.com", 443,"/wd/hub/");

//        local = new ChromeDriver();
        driver = new RemoteWebDriver(FTAAS_URL, capabilities);
    }

    @Test
    public void test() {

        driver.get("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}
