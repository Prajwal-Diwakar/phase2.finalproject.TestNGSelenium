package phase2.StarHealthHome;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StarHealthHomePage {
    public WebDriver driver;

    @FindBy(xpath = "/html/body/div[1]/div/div/header/div[2]/div[1]/span/span/img")
    private WebElement starHealthLogo;
    
    @FindBy(xpath = "/html/body/div[1]/div/div/header/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/span/span/img")
    private WebElement plansMenu;

    // Constructor
    public StarHealthHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to get the alt value of the logo
 
    	
    	 public String getStarHealthLogoAltText() {
    	        try {
    	            return starHealthLogo.getAttribute("alt");
    	        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
    	            // Re-locate the element
    	            starHealthLogo = driver.findElement(By.xpath("/html/body/div[1]/div/div/header/div[2]/div[1]/span/span/img"));
    	            return starHealthLogo.getAttribute("alt");
    	        }
    	 }
    
 // Method to hover over the Plans menu
    public void hoverOverPlansMenu() {
        Actions actions = new Actions(driver);
        actions.moveToElement(plansMenu).build().perform();
    }
    
    private void clickForMyFamilyOption() throws InterruptedException {
        // Wait for the "For My Family" option to be clickable
        Thread.sleep(20);
        WebElement forMyFamilyOption = driver.findElement(By.xpath("//a[text()='For My Family']"));

        // Click on the "For My Family" option
        forMyFamilyOption.click();
    }
    
  
    public String getSocialMediaURL(String platform) {
        
        String xpath = "//footer//a[contains(@href,'" + platform.toLowerCase() + "')]";
        WebElement socialMediaLink = driver.findElement(By.xpath(xpath));
        return socialMediaLink.getAttribute("href");
    }
    
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);"); // Adjust the scroll distance as needed
    }
    
    public void clickTwitterLogo() {
        // Example XPath, adjust as needed based on your actual HTML structure
        String twitterLogoXPath = "//footer//a[contains(@href,'twitter.com')]";
        WebElement twitterLogo = driver.findElement(By.xpath(twitterLogoXPath));
        twitterLogo.click();
    }
    
    public void switchToNewTab() {
        // Get the current window handle
        String currentWindowHandle = driver.getWindowHandle();

        // Switch to the new tab (assuming the new tab is the last window handle)
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
    
    
}