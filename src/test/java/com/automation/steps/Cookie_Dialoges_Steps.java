package com.automation.steps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import com.automation.hooks.WebDriverHooks;
import java.time.Duration;
import io.cucumber.java.en.*;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.openqa.selenium.Alert;

public class Cookie_Dialoges_Steps {
    private WebDriver driver;
    private WebDriverWait wait;

    public Cookie_Dialoges_Steps() {
        this.driver = WebDriverHooks.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @When("I click on Cookies page")
    public void i_click_on_cookies_page() {
        WebElement cookiesPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Cookies")));
        cookiesPage.click();
    }
    
    @Then("I should add a cookie with the name {string} and value {string}")
    public void i_should_add_a_cookie_with_the_name_and_value(String name, String value) {
        Cookie cookie= new Cookie(name,value);
        driver.manage().addCookie(cookie);
    }
    
    @Then("I should read the cookie with the name {string}")
    public void i_should_read_the_cookie_with_the_name(String name) {
        Cookie cookie = driver.manage().getCookieNamed(name);
        Assert.assertNotNull(cookie, "Cookie with name " + name + " should exist");
        Assert.assertEquals(cookie.getValue(), "test", "Cookie value should match the expected value");
    }

    @Then("I should click on cookies display button and check cookie with name {string} is displayed")
    public void i_should_click_on_cookies_display_button_and_check_cookie_with_name_is_displayed(String name) {
        WebElement cookieDisplayButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("refresh-cookies")));
        cookieDisplayButton.click();
        WebElement cookieDisplay = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookies-list")));
        Assert.assertTrue(cookieDisplay.getText().contains(name));
    }
    @Then("I should edit the cookie with the name {string} and value {string}")
    public void i_should_edit_the_cookie_with_the_name_and_value(String name, String value) {
        Cookie oldCookie = driver.manage().getCookieNamed(name);
        driver.manage().addCookie(new Cookie(oldCookie.getName(), value));
        Cookie cookie = driver.manage().getCookieNamed(name);
        Assert.assertEquals(cookie.getValue(), value, "Cookie value should match the expected value");
    }
    
    @Then("I should delete the cookie with the name {string}")
    public void i_should_delete_the_cookie_with_the_name(String name) {
        driver.manage().deleteCookieNamed(name);
        Cookie cookie = driver.manage().getCookieNamed(name);
        Assert.assertNull(cookie, "Cookie with name " + name + " should not exist");
    }   

    @When("I click on Dialoges page")
    public void i_click_on_dialoges_page() {
        WebElement dialogesPage = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dialog boxes")));
        dialogesPage.click();
    }

    @When("I click on the Launch Alert button")
    public void i_click_on_the_launch_alert_button() {
        WebElement launchAlertButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-alert")));
        launchAlertButton.click();
    }

    @Then("I should see the alert and verify it has the text {string}")
    public void i_should_see_the_alert_and_verify_it_has_the_text(String text) {
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), text);
    }

    @Then("I should close the alert")
    public void i_should_close_the_alert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    @When("I click on the Launch Prompt button")
    public void i_click_on_the_launch_prompt_button() {
        WebElement launchPromptButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-prompt")));
        launchPromptButton.click();
    }

    @Then("I should see the prompt and verify it has the text {string} and send the text {string}")
    public void i_should_see_the_prompt_and_verify_it_has_the_text(String text, String value) {
        Alert prompt = driver.switchTo().alert();
        Assert.assertEquals(prompt.getText(), text);
        System.out.println("\n\n\n\n" + prompt.getText() + "\n\n\n\n");
        prompt.sendKeys(value);
    }

    @Then("I should accept the alert")
    public void i_should_accept_the_alert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @When("I click on the Launch Modal button")
    public void i_click_on_the_launch_modal_button() {
        WebElement launchModalButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-modal")));
        launchModalButton.click();
    }

    @Then("I should see the modal and verify it has the text {string}")
    public void i_should_see_the_modal_and_verify_it_has_the_text(String text) {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-body")));
        System.out.println("\n\n\n\n" + modal.getText() + "\n\n\n\n");
        Assert.assertTrue(modal.getText().contains(text));
    }
    
    @Then("I should close the modal")
    public void i_should_close_the_modal() {
       WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Close']")));
       //WebElement close = driver.findElement(By.xpath("//button[contains(text(),'Close')]"));
       //WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Close')]")));
       close.click();
    }
    
}

  
    
    
