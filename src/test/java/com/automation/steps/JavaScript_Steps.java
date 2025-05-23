package com.automation.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.hooks.WebDriverHooks;
import io.cucumber.java.en.*;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;
import org.openqa.selenium.support.Color;


public class JavaScript_Steps {
    private WebDriver driver;
    private WebDriverWait wait;

    public JavaScript_Steps() {
        driver = WebDriverHooks.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I click on the long page")
    public void iClickOnTheLongPage() {
        driver.findElement(By.linkText("Long page")).click();
    }

    @Then("I scroll to last paragraph")
    public void iScrollToASpecificElement() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p:last-child")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Then("I click on the infinite scroll page")
    public void iClickOnTheInfiniteScrollPage() {
        driver.findElement(By.linkText("Infinite scroll")).click();
    }

    @Then("check if scroll is infinite")
    public void checkIfScrollIsInfinite() {
        // Get initial count of paragraphs
        List<WebElement> initialElements = driver.findElements(By.tagName("p"));
        int initialCount = initialElements.size();
        
        // Scroll to the last element
        WebElement lastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p:last-child")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lastElement);
        
        // Wait for new content to load (up to 10 seconds)
        wait.until(driver -> {
            List<WebElement> currentElements = driver.findElements(By.tagName("p"));
            return currentElements.size() > initialCount;
        });
        
        // Get final count and verify
        List<WebElement> finalElements = driver.findElements(By.tagName("p"));
        int finalCount = finalElements.size();
        System.out.println("\n\n\n"+"Initial count: " + initialCount + " Final count: " + finalCount+"\n\n\n");
        Assert.assertTrue(finalCount > initialCount, "Scroll is infinite - expected more elements after scrolling");
    }

    @Then("I test the Asynchronous JavaScript")
    public void iTestTheAsynchronousJavaScript() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Duration pause=Duration.ofSeconds(3);

        String script= "const callback=arguments[arguments.length-1];"+"window.setTimeout(()=>callback(),"+pause.toMillis()+");";
        
        long initialTime=System.currentTimeMillis();
        js.executeAsyncScript(script);
        Duration duration=Duration.ofMillis(System.currentTimeMillis()-initialTime);
        Assert.assertTrue(duration.getSeconds()>=pause.getSeconds(),"Execution of async script took less than "+pause.getSeconds()+" seconds");
    }

    @Then("I check the inital color is {string}")
    public void iCheckTheInitalColorIs(String expectedColor) {
        WebElement colorPicker = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-colors")));
        String actualColor = colorPicker.getAttribute("value");
        Assert.assertTrue(actualColor.contains(expectedColor), "Initial color is not " + expectedColor);
    }

    @Then("I should set a new color to red and check")
    public void iShouldSetANewColorToAndCheck() {
        WebElement colorPicker = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-colors")));
        String initColor = colorPicker.getAttribute("value");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Color red = new Color(255, 0, 0,1);
        String script = String.format(
                "arguments[0].setAttribute('value', '%s');", red.asHex());
        js.executeScript(script, colorPicker);
        String finalColor = colorPicker.getAttribute("value");
        Assert.assertTrue(finalColor.contains(red.asHex()), "Color is not red");
    }
}

