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
    private static final boolean IS_DOCKER = Files.exists(Paths.get("/.dockerenv"));
    
    static {
        // Initialize WebDriver once when the class is loaded
        ChromeOptions options = new ChromeOptions();
        
        // Common options for both local and CI
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        
        // Disable password safety prompts
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-features=PasswordManager");
        options.addArguments("--disable-features=PasswordManagerReauthentication");
        options.addArguments("--disable-features=PasswordManagerReauthenticationUI");
        
        // CI/Docker-specific options
        if (IS_CI || IS_DOCKER) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            // Add a unique user data directory
            options.addArguments("--user-data-dir=/tmp/chrome-" + System.currentTimeMillis());
        }
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        // Create screenshots directory if it doesn't exist
        try {
            Files.createDirectories(Paths.get(SCREENSHOTS_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Before
    public void setUp() {
        // Clear cookies and cache before each scenario
        driver.manage().deleteAllCookies();
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
    }

    public static WebDriver getDriver() {
        return driver;
    }
    
    // Add a shutdown hook to quit the driver when JVM shuts down
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (driver != null) {
                driver.quit();
            }
        }));
    }
} 