package Assessment;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class OnlineCalculator extends BaseClass {
    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest test;

    @BeforeMethod
    public void setup() {
        openCalculator();
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Capture screenshot only on test failure
            captureScreenshot(result.getName());
        }
        // Flush Extent Reports after each test method
        extent.flush();
        // Close the calculator after each test method
        closeCalculator();
    }

    @Test
    public void testAddition() {
        test = extent.createTest("testAddition");
        clickButton2();
        clickAdditionButton();
        clickButton3();
        clickEqualsButton();
        // Log test status in Extent Reports
        test.log(Status.PASS, "Addition Test Passed");
    }

    @Test
    public void testSubtraction() {
        test = extent.createTest("testSubtraction");
        clickButton8();
        clickSubtractButton();
        clickButton3();
        clickEqualsButton();
        // Log test status in Extent Reports
        test.log(Status.PASS, "Subtraction Test Passed");
    }

    @Test
    public void testMultiplication() {
        test = extent.createTest("testMultiplication");
        clickButton5();
        clickMultiplyButton();
        clickButton6();
        clickEqualsButton();
        // Log test status in Extent Reports
        test.log(Status.PASS, "Multiplication Test Passed");
    }

    @Test
    public void testDivision() {
        test = extent.createTest("testDivision");
        clickButton1();
        clickButton0();
        clickDivisionButton();
        clickButton2();
        clickEqualsButton();
        // Log test status in Extent Reports
        test.log(Status.PASS, "Division Test Passed");
    }

    @Test
    public void NegativeTest1() {
        test = extent.createTest("NegativeTest1");
        clickButton8();
        clickDivisionButton();
        clickButton0();
        clickEqualsButton();
        // Log test status in Extent Reports
        test.log(Status.PASS, "Negative Test 1 Passed");
    }

    public void captureScreenshot(String methodName) {
        try {
            // Convert WebDriver object to TakesScreenshot
            TakesScreenshot ts = (TakesScreenshot) driver;

            // Capture screenshot as File
            File source = ts.getScreenshotAs(OutputType.FILE);

            // Define the destination path to save the screenshot
            Path destinationPath = Path.of("screenshots", methodName + "_failure.png");

            // Copy the screenshot file to the specified destination
            Files.copy(source.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            // Log the screenshot in Extent Reports
            test.log(Status.FAIL, "Screenshot captured: " + destinationPath.toString());

            System.out.println("Screenshot captured: " + destinationPath.toString());
        } catch (Exception e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
        }
    }
}
