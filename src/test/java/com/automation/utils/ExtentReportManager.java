package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportManager {
    private static ExtentReports extentReports;
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    public static synchronized ExtentReports getExtentReports() {
        if (extentReports == null) {
            extentReports = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-reports/extent-report.html");
            
            // Configure the reporter
            sparkReporter.config()
                .setTheme(Theme.STANDARD);
            sparkReporter.config()
                .setDocumentTitle("Automation Test Report");
            sparkReporter.config()
                .setReportName("Test Execution Report");
            sparkReporter.config()
                .setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
            
            extentReports.attachReporter(sparkReporter);
            
            // Add system information
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
            extentReports.setSystemInfo("Browser", "Chrome");
        }
        return extentReports;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String description) {
        ExtentTest test = getExtentReports().createTest(testName, description);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized void endTest() {
        getExtentReports().flush();
    }

    public static synchronized void addSystemInfo(String key, String value) {
        getExtentReports().setSystemInfo(key, value);
    }
} 