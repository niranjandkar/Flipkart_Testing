package com.test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlipkartTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println("Flipkart website open successful");
    }
    

    @DataProvider(name = "productData")
    public Object[][] productData() {
        return new Object[][] {
        	
            {"iPhone"},
            {"lenovo"},
            {"TV"}
            
        };
        
    }
    @Test(dataProvider = "productData")
    public void searchProductTest1(String productName) {
        driver.get("https://www.flipkart.com/");
        
        
        try {
            WebElement closeButton = driver.findElement(By.cssSelector("button._2KpZ6l._2doB4z"));
            closeButton.click();
        } catch (Exception e) {
           
        }
        System.out.println("Clicked on search bar");
        
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(productName);
        searchBox.submit();
        
        
        WebDriverWait wait = new WebDriverWait(driver, 20);
        List<WebElement> productNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.KzDlHZ")));
        List<WebElement> productRating = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.XQDdHH")));
        System.out.println("Execution has started");
        System.out.println("Number of products found: " + productNames.size());
        
        for (int i = 0; i < productNames.size() ; i++) {
            System.out.println("Product: " + productNames.get(i).getText()+"Product rating:"+productRating.get(i).getText() );
        }
        
        int numberOfProducts = productNames.size();
    }
             
     @Test
    public void conclusionTest() {
        System.out.println("The test is completed on product data.");
    }

     @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
