package com.automation.steps;

import com.automation.hooks.WebDriverHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class FormHandlingSteps {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public FormHandlingSteps() {
        this.driver = WebDriverHooks.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @And("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // try {
        //     Thread.sleep(3000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }

    @Then("I should see a success message")
    public void iShouldSeeASuccessMessage() {
        WebElement flashMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));
        Assert.assertTrue(flashMessage.getText().contains("You logged into a secure area"));
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"));
    }

    @Then("I should see {string}")
    public void iShouldSee(String message) {
        WebElement flashMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));
        Assert.assertTrue(flashMessage.getText().contains(message));
    }

    @And("I select a file to upload")
    public void iSelectAFileToUpload() throws Exception {
        // Create a temporary file for upload
        Path tempFile = Files.createTempFile("test-upload-", ".txt");
        Files.write(tempFile, "Test file content".getBytes());
        
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        fileInput.sendKeys(tempFile.toAbsolutePath().toString());
    }

    @And("I click the upload button")
    public void iClickTheUploadButton() {
        driver.findElement(By.id("file-submit")).click();
    }

    @Then("I should see the file uploaded successfully")
    public void iShouldSeeTheFileUploadedSuccessfully() {
        WebElement uploadedFiles = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploaded-files")));
        Assert.assertTrue(uploadedFiles.isDisplayed(), "Uploaded files section should be visible");
    }
} 