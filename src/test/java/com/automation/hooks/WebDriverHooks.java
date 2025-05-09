package com.automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WebDriverHooks {
    private static WebDriver driver;
    private static final String SCREENSHOTS_DIR = "target/screenshots";
    private static final boolean IS_CI = System.getenv("CI") != null;
    
    @Before
    public void setupDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            
            // Add CI-specific options
            if (IS_CI) {
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            // Create screenshots directory if it doesn't exist
            try {
                Files.createDirectories(Paths.get(SCREENSHOTS_DIR));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot if scenario fails
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot of failure");
            
            // Save screenshot to file
            try {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                String fileName = String.format("%s/%s_%s.png", SCREENSHOTS_DIR, scenarioName, timestamp);
                
                Path screenshotPath = Paths.get(fileName);
                Files.write(screenshotPath, screenshot);
                System.out.println("Screenshot saved to: " + screenshotPath.toAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
} 