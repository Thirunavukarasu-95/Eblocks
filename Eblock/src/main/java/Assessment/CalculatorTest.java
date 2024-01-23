package Assessment;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;

public class CalculatorTest extends BaseClass {

    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        // Initialize ExtentReports and attach a reporter
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test(enabled = true)
    public void testOnlineCalculator() {
        // Set up ChromeDriver using WebDriverManager
        WebDriver driver = getDriver();

        // Navigate to the online calculator application (Google search page)
        driver.get("https://www.google.com/search?q=online+calculator");

        // Maximize the window
        maximizeWindow(driver);

        // Read input data from Excel
        Object[][] testData = readTestPositiveDataFromExcel(getExcelPath());

        // Iterate through each set of test data
        for (Object[] data : testData) {
            String operand1 = data[0].toString();
            String operator = data[1].toString();
            String operand2 = data[2].toString();
            String expectedResult = data[3].toString();

            // Locate the calculator input field and enter values
            enterValue(driver, operand1);
            enterValue(driver, operator);
            enterValue(driver, operand2);
            enterValue(driver, "=");

            // Retrieve and print the result
            WebElement resultElement = driver.findElement(By.id("cwos"));
            String result = resultElement.getText();
            System.out.println("Result: " + result);

            // Assertion: Check that the result is equal to the expected result
            Assert.assertEquals(result, expectedResult, "Result is not as expected");

            // Create a new test entry in the ExtentReport
            test = extent.createTest("testOnlineCalculator_Row_" + getRowIndex(testData, data));

            // Log test status to ExtentReports
            test.log(Status.PASS, "Test passed");

            // Capture screenshot for the current iteration
            captureScreenshot(driver, "testOnlineCalculator_Row_" + getRowIndex(testData, data));

            // Clear the calculator for the next iteration
            driver.findElement(By.xpath("//div[text()='AC']")).click();
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Log failure status to ExtentReports
            if (test != null) {
                test.log(Status.FAIL, "Test failed");
            }
        }

        // Flush the report
        extent.flush();
    }

    @Test(enabled = true)
    public void negativeTestOnlineCalculator() {
        WebDriver driver = getDriver();
        driver.get("https://www.google.com/search?q=online+calculator");
        maximizeWindow(driver);

        Object[][] testData = readTestNegetiveDataFromExcel(getExcelPath());

        for (Object[] data : testData) {
            // Clear the StringBuilder for a fresh start
            enteredValue.setLength(0);

            String operand1 = data[0].toString();
            String operator = data[1].toString();
            String operand2 = data[2].toString();
            String expectedResult = data[3].toString();

            // Check if operand1 contains invalid characters
            if (!isValidOperand(operand1)) {
                System.out.println("This is Non Numeric Value");
                continue; // Move to the next row
            }

            // Check if operand2 contains invalid characters
            if (!isValidOperand(operand2)) {
                System.out.println("This is Non Numeric Value");
                continue; // Move to the next row
            }

            enterValue(driver, operand1);
            enterValue(driver, operator);
            enterValue(driver, operand2);
            enterValue(driver, "=");

            // Retrieve and print the result
            WebElement resultElement = driver.findElement(By.id("cwos"));
            String result = resultElement.getText();
            System.out.println("Result: " + result);

            // Assertion: Check that the result is equal to the expected result
            Assert.assertEquals(result, expectedResult, "Result is not as expected");

            // Create a new test entry in the ExtentReport
            test = extent.createTest("negativeTestOnlineCalculator_Row_" + getRowIndex(testData, data));

            // Log test status to ExtentReports
            test.log(Status.PASS, "Test passed");

            // Capture screenshot for the current iteration
            captureScreenshot(driver, "negativeTestOnlineCalculator_Row_" + getRowIndex(testData, data));

            // Clear the calculator for the next iteration
            driver.findElement(By.xpath("//div[text()='AC']")).click();
        }
    }

    @Test(enabled = true)
    public void boundaryValueTestOnlineCalculator() {
        WebDriver driver = getDriver();
        driver.get("https://www.google.com/search?q=online+calculator");
        maximizeWindow(driver);

        Object[][] testData = readTestBoundaryValueDataFromExcel(getExcelPath());

        for (Object[] data : testData) {
            // Clear the StringBuilder for a fresh start
            enteredValue.setLength(0);

            String operand1 = data[0].toString();
            String operator = data[1].toString();
            String operand2 = data[2].toString();
            String expectedResult = data[3].toString();

            // Check if operand1 contains invalid characters
            if (!isValidOperand(operand1)) {
                System.out.println("This is Non Numeric Value");
                continue; // Move to the next row
            }

            // Check if operand2 contains invalid characters
            if (!isValidOperand(operand2)) {
                System.out.println("This is Non Numeric Value");
                continue; // Move to the next row
            }

            enterValue(driver, operand1);
            enterValue(driver, operator);
            enterValue(driver, operand2);
            enterValue(driver, "=");

            // Retrieve and print the result
            WebElement resultElement = driver.findElement(By.id("cwos"));
            String result = resultElement.getText();
            System.out.println("Result: " + result);

            // Assertion: Check that the result is equal to the expected result
            Assert.assertEquals(result, expectedResult, "Result is not as expected");

            // Create a new test entry in the ExtentReport
            test = extent.createTest("boundaryValueTestOnlineCalculator_Row_" + getRowIndex(testData, data));

            // Log test status to ExtentReports
            test.log(Status.PASS, "Test passed");

            // Capture screenshot for the current iteration
            captureScreenshot(driver, "boundaryValueTestOnlineCalculator_Row_" + getRowIndex(testData, data));

            // Clear the calculator for the next iteration
            driver.findElement(By.xpath("//div[text()='AC']")).click();
        }
    }

    private void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = "screenshots/" + screenshotName + ".png";
            FileUtils.copyFile(source, new File(destination));
            System.out.println("Screenshot captured: " + destination);

            // Log screenshot in the ExtentReport
            if (test != null) {
                test.addScreenCaptureFromPath(destination);
            }
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

    private int getRowIndex(Object[][] array, Object[] row) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == row) {
                return i + 1; // Add 1 because Excel rows are 1-indexed
            }
        }
        return -1; // Row not found
    }
}
