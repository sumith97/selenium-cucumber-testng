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
} 