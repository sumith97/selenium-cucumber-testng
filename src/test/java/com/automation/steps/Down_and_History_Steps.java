package com.automation.steps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import com.automation.hooks.WebDriverHooks;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.cucumber.java.en.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class Down_and_History_Steps {

    private WebDriver driver;
    private WebDriverWait wait;

    public Down_and_History_Steps() {
        this.driver = WebDriverHooks.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @Then("I click on data list and check {string} is present")
    public void i_click_on_data_list_and_check_is_present(String string) {
        List<String> expectedOptions = Arrays.asList("New York", "San Francisco", "Seattle", "Los Angeles", "Chicago");
        WebElement dataList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-datalist")));
        dataList.click();
        WebElement option = driver.findElement(By.xpath("//datalist[@id='my-options']/option[2]"));
        List<WebElement> options = driver.findElements(By.xpath("//datalist[@id='my-options']/option"));
        List<String> optionValues = new ArrayList<>();
        for (WebElement opt : options) {
            System.out.println("\n\n\n\n"+opt.getAttribute("value")+"\n\n\n\n");
            optionValues.add(opt.getAttribute("value"));
        }
        Assert.assertTrue(optionValues.containsAll(expectedOptions) && expectedOptions.containsAll(optionValues),
            "Expected options not found in the list. Expected: " + expectedOptions + ", Found: " + optionValues);
        Assert.assertEquals(option.getAttribute("value"), string);
    }

    @Then("I select {string} from the data list")
    public void i_select_from_the_data_list(String string) {
        WebElement dataList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-datalist")));
        dataList.click();
        // Use keyboard actions to simulate user typing and selecting
        dataList.sendKeys(string);
        // Press Enter to select the option
       // dataList.sendKeys(Keys.ENTER);
        // Verify the value was set correctly
        Assert.assertEquals(dataList.getAttribute("value"), string);
    }

    @Then("I select {string} from a selection dropdown")
    public void i_select_from_a_selection_dropdown(String string) {
        Select select = new Select(driver.findElement(By.name("my-select")));
        select.selectByVisibleText(string);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), string);
    }

}
