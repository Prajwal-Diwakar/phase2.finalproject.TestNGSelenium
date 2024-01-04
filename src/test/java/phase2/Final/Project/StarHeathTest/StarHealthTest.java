package phase2.Final.Project.StarHeathTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import phase2.StarHealthHome.StarHealthHomePage;
import phase2.finalProject.ExcelReader.ExcelReader;
import phase2.finalProject.MediaDetail.MediaDetail;

public class StarHealthTest {
	
	String driverPath= "drivers/windows/geckodriver.exe";
	String siteURL= "https://www.starhealth.in/";  
	private WebDriver driver;
	private StarHealthHomePage homePAGE; 

    @BeforeClass
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.geckodriver.driver", driverPath);

        // Initialize the WebDriver (open a new Chrome browser window)
        driver = new FirefoxDriver();

        // Navigate to the Star Health website
        driver.get(siteURL);
        
     // Initialize the StarHealthHomePage class
        homePAGE = new StarHealthHomePage(driver);
    }
    
    @AfterClass
    public void close() {
    	driver.quit(); // the quit() method closes all browser windows and ends the WebDriver session.
   }
    
    @Test(groups = "starhealth001")
    public void validateStarHealthLogoAlt() throws InterruptedException {
        // Get the alt value of the logo
        String altText = homePAGE.getStarHealthLogoAltText();

        // Validate the alt value using TestNG assertion
        Assert.assertEquals(altText, "Star Health Logo", "Alt value of the Star Health logo is incorrect");
        
        Thread.sleep(4000);
    }
    
    @Test(groups = "starhealth001")
   public void hoverOverPlansMenuAndClickForMyFamily() throws InterruptedException {
        // Create an instance of the StarHealthHomePage class
   	
    	homePAGE = new StarHealthHomePage(driver);

        // Hover over the Plans menu
    	homePAGE.hoverOverPlansMenu();
   	
  	for (String windowHandle : driver.getWindowHandles()) {
           // Switch to the pop-up window
           driver.switchTo().window(windowHandle);
        }
   	
   	Thread.sleep(4000);

        // Click on the "For My Family" option in the submenu
       WebElement forMyFamilyOption = driver.findElement(By.xpath("/html/body/div[1]/div/div/header/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div/div/div/div[2]/div/div/div[1]/div[1]/div[3]/a"));
        forMyFamilyOption.click();
       
      

       // Additional test steps for this scenario...
   }
    
    @Test(groups = "starhealth001")
    public void inputs_data() throws InterruptedException {
   	
   	Thread.sleep(2000);
    	
   	WebElement name= driver.findElement(By.id("name"));
   	name.click();
    	name.sendKeys("demo");
      driver.findElement(By.id("phoneNumber")).sendKeys("9111111111");
       driver.findElement(By.id("pinCode")).sendKeys("110005");
    	assertEquals("https://www.starhealth.in/health-insurance/comprehensive/", driver.getCurrentUrl());
    	Thread.sleep(4000);
   }
   
    @Test (groups = "starhealth001")
   public void validateSocialMediaLinksInFooter() throws IOException {
        // Create an instance of the StarHealthHomePage class
    	homePAGE = new StarHealthHomePage(driver);

       // Read social media details from Excel
        List<MediaDetail> socialMediaDetails = ExcelReader.readSocialMediaDetails();

       // Validate each social media link in the footer
        for (MediaDetail mediaDetail : socialMediaDetails) {
           validateSocialMediaLink(mediaDetail);
        }
    }
    
    private void validateSocialMediaLink(MediaDetail mediaDetail) {
        // Get the actual URL from the footer
        String actualURL = homePAGE.getSocialMediaURL(mediaDetail.getPlatform());

       // Assert that the actual URL matches the expected URL
       Assert.assertEquals(actualURL, mediaDetail.getUrl(), "Incorrect URL for " + mediaDetail.getPlatform());

        // Print a message indicating the link is valid
      System.out.println("Verified link for " + mediaDetail.getPlatform());
    }
    
    @Test(groups = "starhealth001")
    public void validateTwitterPageOpensInNewTab() {
        // Create an instance of the StarHealthHomePage class
        homePAGE = new StarHealthHomePage(driver);

        // Scroll down on the page
      homePAGE.scrollDown();

      // Click on the Twitter logo in the footer
       homePAGE.clickTwitterLogo();

        // Switch to the new tab
        homePAGE.switchToNewTab();

       // Validate the Twitter page URL
      // Validate that the URL contains "starhealthins"
        String expectedTextInURL = "https://twitter.com/starhealthins?lang=en";
        String actualURL = driver.getCurrentUrl();
        assertEquals(actualURL,expectedTextInURL);
   }
    
    @Test(groups = "starhealth002")
    public void printAllLinksOnHomePage() {
        // Launch the Star Health website
        driver.get(siteURL);

        // Initialize the StarHealthHomePage class
        homePAGE = new StarHealthHomePage(driver);

        // Get all links on the home page
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));

        // Print all links
        for (WebElement link : allLinks) {
            System.out.println("Link Text: " + link.getText() + ", URL: " + link.getAttribute("href"));
        }
    }

    

}



