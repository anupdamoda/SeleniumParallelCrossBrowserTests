package parallelTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class SeleniumTest {

    WebDriver driver;
    public static ChromeOptions options;
    public static EdgeOptions options1;

    /**
     * This function will execute before each Test tag in testng.xml
     * @param browser
     * @throws Exception
     */
    @BeforeTest
    @Parameters("browserType")
    public void setup(String browser) throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){

            capabilities.setBrowserName("firefox");
            //create firefox instance
     // not reuired in case of docker
            //       System.setProperty("webdriver.firefox.marionette",  System.getProperty("user.dir") + "/src/test/resources/geckodriver.exe");
     //       driver = new FirefoxDriver();
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("chrome")){

            capabilities.setBrowserName("chrome");

            options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            //set path to chromedriver.exe
      //      System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
            //create chrome instance
      //      driver = new ChromeDriver(options);
        }
        //Check if parameter passed as 'Edge'
        else if(browser.equalsIgnoreCase("Edge")){
            capabilities.setBrowserName("MicrosoftEdge");

            options1 = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
            //set path to Edge driver.exe
       //     System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/src/test/resources/msedgedriver.exe");
            //create Edge instance
       //     driver = new EdgeDriver(options1);
        }
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
         driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testParameterWithXML() throws InterruptedException{
        driver.get("http://demo.guru99.com/V4/");
        //Find user name
        WebElement userName = driver.findElement(By.name("uid"));
        //Fill user name
        userName.sendKeys("guru99");
        //Find password
        WebElement password = driver.findElement(By.name("password"));
        //Fill password
        password.sendKeys("guru99");
    }
}
