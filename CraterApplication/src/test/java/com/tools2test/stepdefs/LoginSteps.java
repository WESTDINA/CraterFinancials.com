package com.tools2test.stepdefs;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
public class LoginSteps {

 

    WebDriver driver;
    WebDriverWait wait;

   
	public void initializeDriver ( ) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\frankdexdevs\\Documents\\writingAssignments\\evans\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        wait =  new WebDriverWait(driver, 10);
    }

 

    @Given("^I open Chrome browser$")
    public void i_open_Chrome_browser() throws Throwable {

            initializeDriver();

    }

 

    @When("^I navigate to login page$")
    public void i_navigate_to_login_page() throws Throwable {

            driver.get("http://crater.primetech-apps.com/login");

    }


 

    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void i_provide_username_as_and_password_as(String user, String password) throws Throwable {
    	    	
        WebElement emailInp = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#loginForm > div:nth-child(1) > div > div > input")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", emailInp);
        emailInp.click();
        emailInp.sendKeys("yusuf@haji.com");


        //WebElement passInp =wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#loginForm > div:nth-child(2) > div > div > input")));
        //passInp.sendKeys(password);
        
    }

 

    @When("^I click on login button$")
    public void i_click_on_login_button() throws Throwable {

            // Click on the Login Button

    	WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
        loginButton.click();       

    }

   

    @Then("^The user should be taken to the Dashboard page after successfully logging in according to the system$")
    public void the_user_should_be_taken_to_the_Dashboard_page_after_successfully_logging_in_according_to_the_system() {
        // Verify that the redirection happened
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div")));
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("http://crater.primetech-apps.com/admin/settings/account-settings")) {
            System.out.println("Redirection successful");
        } else {
            System.out.println("Redirection failed. Current URL: " + currentUrl);
        }

        // Close the browser
        driver.quit();
    }

}

