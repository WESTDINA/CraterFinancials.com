package com.tools2test.uiconfsteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class UI_confirmation {

    WebDriver driver = null;
    WebDriverWait wait = null;

    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\frankdexdevs\\Documents\\writingAssignments\\evans\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Given("^I open Chrome browser$")
    public void i_open_Chrome_browser() throws Throwable {
        initializeDriver();
    }

    @When("^I navigate to login page$")
    public void i_navigate_to_login_page() throws Throwable {
        driver.get("http://crater.primetech-apps.com/login");
    }

    @Then("^User should be directed to the Crater login page$")
    public void user_should_be_directed_to_the_Crater_login_page() {
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals("http://crater.primetech-apps.com/login");
    }

    @Then("^Page title should be Crater$")
    public void page_title_should_be_Crater() {
        String pageTitle = driver.getTitle();
        assert pageTitle.contains("Crater");
    }

    @Then("^User should see the Email Textbox input$")
    public void user_should_see_the_Email_Textbox_input() {
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")));
        assert emailInput.isDisplayed();
    }

    @Then("^User should see the Password input$")
    public void user_should_see_the_Password_input() {
        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']")));
        assert passwordInput.isDisplayed();
    }

    @Then("^User should see the \"forgot password\" link under password input field$")
    public void user_should_see_the_forgot_password_link_under_password_input_field() {
        WebElement forgotPasswordLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Forgot Password?")));
        assert forgotPasswordLink.isDisplayed();
    }

    @Then("^User should see Heading 2 located below Heading 1 with the text \"Crater helps you track expenses, record payments & generate beautiful invoices & estimates.\"$")
    public void user_should_see_Heading_2_with_the_text() {
        WebElement heading1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
        WebElement heading2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div/p")));

        String heading1Text = heading1.getText();
        String heading2Text = heading2.getText();

        assert heading1Text.equals("Simple Invoicing for Individuals Small Businesses");
        assert heading2Text.equals("Crater helps you track expenses, record payments & generate beautiful invoices & estimates.");
    }

}
