package com.automation.steps;

import com.automation.hooks.WebDriverHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DynamicControlsSteps {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public DynamicControlsSteps() {
        this.driver = WebDriverHooks.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am on the dynamic controls page")
    public void iAmOnTheDynamicControlsPage() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        // Verify we're on the correct page
        WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h4")));
        Assert.assertEquals(heading.getText(), "Dynamic Controls");
    }

    @When("I click the checkbox remove button")
    public void iClickTheCheckboxRemoveButton() {
        WebElement removeButton = driver.findElement(By.cssSelector("#checkbox-example button"));
        removeButton.click();
    }

    @Then("the checkbox should disappear")
    public void theCheckboxShouldDisappear() {
        // Wait for the "It's gone!" message
        wait.until(ExpectedConditions.textToBe(By.cssSelector("#message"), "It's gone!"));
        // Verify checkbox is not present
        Assert.assertEquals(driver.findElements(By.id("checkbox")).size(), 0, 
            "Checkbox should not be present");
    }

    @When("I click the enable button")
    public void iClickTheEnableButton() {
        WebElement enableButton = driver.findElement(By.cssSelector("#input-example button"));
        enableButton.click();
    }

    @Then("the input field should be enabled")
    public void theInputFieldShouldBeEnabled() {
        // Wait for the "It's enabled!" message
        wait.until(ExpectedConditions.textToBe(By.cssSelector("#message"), "It's enabled!"));
        // Verify input is enabled
        WebElement input = driver.findElement(By.cssSelector("#input-example input"));
        Assert.assertTrue(input.isEnabled(), "Input field should be enabled");
    }

    @And("I should be able to type {string} in the input field")
    public void iShouldBeAbleToTypeInTheInputField(String text) {
        WebElement input = driver.findElement(By.cssSelector("#input-example input"));
        input.sendKeys(text);
        Assert.assertEquals(input.getAttribute("value"), text, 
            "Input field should contain the typed text");
    }
} 