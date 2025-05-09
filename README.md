# Selenium Cucumber TestNG Framework

[![Selenium Tests](https://github.com/{your-username}/selenium-cucumber-testng/actions/workflows/selenium-tests.yml/badge.svg)](https://github.com/{your-username}/selenium-cucumber-testng/actions/workflows/selenium-tests.yml)

This is a test automation framework built using:
- Selenium WebDriver
- Cucumber for BDD
- TestNG for test execution
- Maven for dependency management

## Prerequisites
- Java 11 or higher
- Maven
- Chrome browser

## Project Structure
```
src/
├── test/
    ├── java/
    │   └── com/
    │       └── automation/
    │           ├── hooks/
    │           ├── runners/
    │           └── steps/
    └── resources/
        └── features/
```

## Features Tested
- Dynamic Controls
- Form Handling and File Upload
- Window and Alert Handling
- Frame Handling

## Running Tests

### Local Execution
```bash
mvn clean test
```

### Test Reports
After test execution, reports can be found in:
- Cucumber HTML Report: `target/cucumber-reports/cucumber-pretty.html`
- TestNG Report: `target/surefire-reports/index.html`
- Screenshots (for failed tests): `target/screenshots/`

## CI/CD
This project uses GitHub Actions for continuous integration. The workflow:
- Runs on Ubuntu latest
- Uses JDK 11
- Executes tests in headless mode
- Generates and uploads test reports
- Provides test results as GitHub annotations

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request 