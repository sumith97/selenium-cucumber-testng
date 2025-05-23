package com.automation.steps;

import com.automation.hooks.WebDriverHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class CommonSteps {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final String BASE_URL = "https://the-internet.herokuapp.com";

    public CommonSteps() {
        this.driver = WebDriverHooks.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am on the internet homepage")
    public void iAmOnTheInternetHomepage() {
        driver.get(BASE_URL);
        Assert.assertEquals(driver.getTitle(), "The Internet");
    }

    @When("I navigate to the {string} page")
    public void iNavigateToThePage(String pageName) {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(pageName)));
        link.click();
    }

    @Then("Navigate back to forward page")
    public void thenNavigateBackToForwardPage() {
        driver.navigate().forward();
    }
    @Then("Navigate back to previous page")
    public void then_navigate_back_to_previous_page() {
        driver.navigate().back();
    }
    @Then("check if I am in Home page")
    public void checkIfIAmInHomePage() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://bonigarcia.dev/selenium-webdriver-java/");
    }
    
    @Then("I should see the text {string}")
    public void i_should_see_the_text(String string) {
        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+string+"')]")));
        Assert.assertEquals(message.getText(), string);
    }
    
    @Then("I scroll down the page by {string} pixels")
    public void i_scroll_down_the_page_by_pixels(String string) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + string + ");");
    }

    @Then("I scroll up the page by {string} pixels")
    public void i_scroll_up_the_page_by_pixels(String string) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -" + string + ");");
    }

    @Then("I refresh the page")
    public void i_refresh_the_page() {
        driver.navigate().refresh();
    }

    
} 