package firstGroup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    //Declare ThreadLocal Driver (ThreadLocalMap) for ThreadSafe Tests
    protected static   ThreadLocal<WebDriver> driver=new ThreadLocal<>();

    @BeforeMethod
    @Parameters(value={"browser"})
    public void setup (String browser) throws MalformedURLException {
        //Set Browser to ThreadLocalMap



//        driver.set( capabilityFactory.getCapabilities(browser)));

        driver.set(new ChromeDriver(OptionsManager.getChromeOptions()));

    }

    public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }

//    @AfterMethod
//    public void tearDown() {
//        getDriver().quit();
//    }

//    @AfterClass
//    void terminate () {
//        //Remove the ThreadLocalMap element
//        driver.remove();
//    }
}
