package com.automation.steps;
import com.automation.hooks.WebDriverHooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import org.openqa.selenium.By;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import io.cucumber.java.en.Given;
import java.io.File;

public class Mouse_Keyboard_Steps {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Mouse_Keyboard_Steps() {
        this.driver = WebDriverHooks.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am in the test page")
    public void i_am_in_the_test_page() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
    }

    @When("I click on the Web Form")
    public void i_click_on_the_web_form() {
        WebElement webForm = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='web-form.html']")));
        webForm.click();
    }

    @Then("I should click on the checkbox and check")
    public void i_should_click_on_the_checkbox_and_check() {
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-check-2")));
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());
    }

    @Then("I should click on the radio button and check")
    public void i_should_click_on_the_radio_button_and_check() {
        WebElement radioButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-radio-2")));
        radioButton.click();
        Assert.assertTrue(radioButton.isSelected());
    }

    @Then("I should type {string} in the text field and check the text")
    public void i_should_type_in_the_text_field_and_check_the_text(String string) {
        WebElement textField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-text-id")));
        textField.sendKeys(string);
        Assert.assertEquals(textField.getAttribute("value"), string);
    }

    @Then("I should upload a file")
    public void i_should_upload_a_file() {
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("my-file")));
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        String absolutePath = System.getProperty("user.dir") + File.separator + "fun.txt";
        fileInput.sendKeys(absolutePath);
    }

    @When("I submit the form")
    public void i_should_submit_the_form() {
        WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
        submitButton.click();
    }


    @When("I click on the canvas page")
    public void i_click_on_the_canvas_page() {
        WebElement canvasPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='draw-in-canvas.html']")));
        canvasPage.click();
    }
    
    @Then("I should see the text {string} and must draw a circle")
    public void i_should_see_the_text_and_must_draw_a_circle(String string) {
        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+string+"')]")));
        Assert.assertTrue(message.getText().toLowerCase().contains(string.toLowerCase()));
        Actions actions = new Actions(driver);
        WebElement canvas = driver.findElement(By.tagName("canvas"));
        actions.moveToElement(canvas).clickAndHold();

        int numPoints = 10;
        int radius = 30;
        for (int i = 0; i <= numPoints; i++) {
            double angle = Math.toRadians(360 * i / numPoints);
            double x = Math.sin(angle) * radius;
            double y = Math.cos(angle) * radius;
            actions.moveByOffset((int) x, (int) y);
        }
        actions.release(canvas).build().perform();
    }


    @When("I click on the Dropdown Menu page")
    public void i_click_on_the_dropdown_menu_page() {
        WebElement dropdownMenuPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='dropdown-menu.html']")));
        dropdownMenuPage.click();
    }

    @Then("I perform right click and check")
    public void i_perform_right_click_and_check() {
        WebElement dropdownMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-dropdown-2")));
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownMenu).contextClick().perform();
        WebElement contextMenu2 = driver.findElement(By.id("context-menu-2"));
        Assert.assertTrue(contextMenu2.isDisplayed());
    }

    @Then("I perform double click and check")
    public void i_perform_double_click_and_check() {
        WebElement dropdownMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-dropdown-3")));
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownMenu).doubleClick().perform();
        WebElement contextMenu3 = driver.findElement(By.id("context-menu-3"));
        Assert.assertTrue(contextMenu3.isDisplayed());
    }   
}
