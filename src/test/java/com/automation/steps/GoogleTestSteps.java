package com.automation.steps;
import com.automation.hooks.WebDriverHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class GoogleTestSteps {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public GoogleTestSteps() {
        this.driver = WebDriverHooks.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Add options to avoid detection
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--disable-blink-features=AutomationControlled");
        // options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        // options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
    }

    @Given("I am on the Google search page")
    public void iAmOnTheGoogleSearchPage() {
        driver.get("https://www.google.com");
        // Wait for the page to load completely
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
    }

    @When("I search for {string}")
    public void iSearchFor(String searchQuery) throws InterruptedException {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(searchQuery);
        searchBox.submit();
        
        // Wait for search results
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));
    }

    @Then("I should see {string} in the search results")
    public void iShouldSeeInTheSearchResults(String searchQuery) {
        // Wait for search results container
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));
        
        // Wait for and find elements containing the search query
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//div[@id='search']//*[contains(text(),'" + searchQuery + "')]")));
        
        Assert.assertFalse(elements.isEmpty(), "No elements found containing '" + searchQuery + "'");
        String foundText = elements.get(0).getText();
        System.out.println("Found text: " + foundText);
        Assert.assertTrue(foundText.contains(searchQuery), 
            "Expected text to contain '" + searchQuery + "' but found: " + foundText);
    }
}
