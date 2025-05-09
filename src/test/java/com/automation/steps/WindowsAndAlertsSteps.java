package com.automation.steps;

import com.automation.hooks.WebDriverHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WindowsAndAlertsSteps {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private String mainWindowHandle;

    public WindowsAndAlertsSteps() {
        this.driver = WebDriverHooks.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @And("I click {string} link")
    public void iClickLink(String linkText) {
        mainWindowHandle = driver.getWindowHandle();
        driver.findElement(By.linkText(linkText)).click();
    }

    @Then("I should be able to switch to the new window")
    public void iShouldBeAbleToSwitchToTheNewWindow() {
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
    }

    @And("I should see {string} text in the new window")
    public void iShouldSeeTextInTheNewWindow(String expectedText) {
        WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
        Assert.assertEquals(heading.getText(), expectedText);
    }

    @When("I switch back to the main window")
    public void iSwitchBackToTheMainWindow() {
        driver.switchTo().window(mainWindowHandle);
    }

    @Then("I should see the original content")
    public void iShouldSeeTheOriginalContent() {
        WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
        Assert.assertEquals(heading.getText(), "Opening a new window");
    }

    @And("I click the {string} button")
    public void iClickTheButton(String buttonText) {
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        buttons.stream()
                .filter(button -> button.getText().equals(buttonText))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Button not found: " + buttonText))
                .click();
    }

    @Then("I should be able to accept the alert")
    public void iShouldBeAbleToAcceptTheAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Then("I should be able to dismiss the confirm dialog")
    public void iShouldBeAbleToDismissTheConfirmDialog() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }

    @Then("I should be able to enter {string} in the prompt")
    public void iShouldBeAbleToEnterInThePrompt(String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
    }

    @Then("I should see {string} message")
    public void iShouldSeeMessage(String message) {
        WebElement result = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result")));
        Assert.assertTrue(result.getText().contains(message));
    }

    @When("I click on {string} link")
    public void iClickOnLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    @Then("I should be able to switch to the frame")
    public void iShouldBeAbleToSwitchToTheFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mce_0_ifr"));
    }

    @And("I should be able to type {string} in the editor")
    public void iShouldBeAbleToTypeInTheEditor(String text) {
        try {
            WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tinymce")));
            // Use JavaScript to set the content instead of clear() and sendKeys()
            ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = arguments[1]", editor, text);
            Assert.assertEquals(editor.getText(), text);
        } catch (Exception e) {
            // If JavaScript approach fails, try alternative method
            WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tinymce")));
            editor.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Select all text
            editor.sendKeys(Keys.DELETE); // Delete selected text
            editor.sendKeys(text);
            Assert.assertEquals(editor.getText(), text);
        }
    }

    @When("I switch back to default content")
    public void iSwitchBackToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    @Then("I should see the frame page header")
    public void iShouldSeeTheFramePageHeader() {
        WebElement header = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
        Assert.assertEquals(header.getText(), "An iFrame containing the TinyMCE WYSIWYG Editor");
    }
} 