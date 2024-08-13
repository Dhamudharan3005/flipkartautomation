package demo;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

   @Test 
public void testCase01() throws InterruptedException{
    Wrappers home=new Wrappers(driver);
    boolean status=false;
    //navigating to page
    driver.get("https://www.flipkart.com/");
    Thread.sleep(3000);
    //typing text on searcch box and clicking search
    home.Searchtext("Washing Machine","//input[@class='Pke_EE']");
    status=home.click_on_tab("Popularity");
    //getting result in int and storing
    int a=home.ratingscount("4");
    //printing the result
    System.out.println("Total no of products having rating "+4+" and above in current page :"+a);
}

@Test
public void testCase02() throws InterruptedException{
    Wrappers home=new Wrappers(driver);
    WebElement box=driver.findElement(By.xpath("//input[@class='zDPmFV']"));
    String temp="Washing Machine";
    for(int i=0;i<temp.length();i++){
        box.sendKeys(Keys.BACK_SPACE);
    }
    home.Searchtext("iPhone","//input[@class='zDPmFV']");
    Thread.sleep(3000);
    home.Check_Discount(17);
}
@Test
public void testCase03() throws InterruptedException{
    Wrappers home=new Wrappers(driver);
    String temp="iphone";
    WebElement box=driver.findElement(By.xpath("//input[@class='zDPmFV']"));
    for(int i=0;i<temp.length();i++){
        box.sendKeys(Keys.BACK_SPACE);
    }
    home.Searchtext("Coffee Mug","//input[@class='zDPmFV']");
    Thread.sleep(5000);
    //home.printproducts("4");
    home.reviewSort(4);
}
    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
  

}