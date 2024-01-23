package Assessment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseClass {

	// Static WebDriver variable
	protected static WebDriver driver;
	
    public void openCalculator() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.online-calculator.com/full-screen-calculator/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    
    public void closeCalculator() {
        if (driver != null) {
            driver.quit();
        }
    }
 // Add the following method in your BaseClass
    public String getCanvasText() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Replace "yourCanvasElementId" with the actual ID of the canvas element
        String script = "return document.getElementById('canvas').textContent;";
        return (String) js.executeScript(script);
    }
    public void clickButton1() {
        pressKey(KeyEvent.VK_1);
    }
    public void clickButton2() {
        pressKey(KeyEvent.VK_2);
    }
    public void clickButton3() {
        pressKey(KeyEvent.VK_3);
    }
    public void clickButton4() {
        pressKey(KeyEvent.VK_4);
    }
    public void clickButton5() {
        pressKey(KeyEvent.VK_5);
    }
    public void clickButton6() {
        pressKey(KeyEvent.VK_6);
    }
    public void clickButton7() {
        pressKey(KeyEvent.VK_7);
    }
    public void clickButton8() {
        pressKey(KeyEvent.VK_8);
    }
    public void clickButton9() {
        pressKey(KeyEvent.VK_9);
    }
    
    public void clickButton0() {
        pressKey(KeyEvent.VK_0);
    }
    public void clickAdditionButton() {
        pressKey(KeyEvent.VK_ADD);
    }
    public void clickSubtractButton() {
        pressKey(KeyEvent.VK_SUBTRACT);
    }
    public void clickMultiplyButton() {
        pressKey(KeyEvent.VK_MULTIPLY);
    }
    public void clickDivisionButton() {
        pressKey(KeyEvent.VK_DIVIDE);
    }
    public void clickEqualsButton() {
        pressKey(KeyEvent.VK_EQUALS);
    }

    public void clickDecimalButton() {
        pressKey(KeyEvent.VK_PERIOD);
    }

    
    private void pressKey(int keyCode) {
        try {
            Robot r = new Robot();
            r.keyPress(keyCode);
            r.keyRelease(keyCode);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

	// Maximize window method
	public static void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	// Chrome Webdriver
	public static WebDriver getDriver() {
		if (driver == null) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		return driver;
	}


	// Excel Path
	public static String getExcelPath() {
		return "U:/Eblocks/Data.xlsx";
	}

	// Get the Sheet Name
	public static String getPositiveSheetName() {
		return "Positive";
	}

	// Get the Sheet
	public static Sheet getPositiveSheet() {
		// Initialize the workbook within the method
		try (FileInputStream fis = new FileInputStream(getExcelPath())) {
			Workbook workbook = WorkbookFactory.create(fis);
			return workbook.getSheet(getPositiveSheetName());
		} catch (IOException e) {
			e.printStackTrace(); // Handle the exception appropriately
		}
		return null;
	}

	// Read Data From Excel
	public Object[][] readTestPositiveDataFromExcel(String excelFilePath) {
		// Open the Excel file
		try (FileInputStream inputStream = new FileInputStream(excelFilePath)) {
			// Assuming data is present in the first sheet (Sheet at index 0)
			Sheet sheet = getPositiveSheet();

			// Get the number of rows in the sheet
			int rowCount = sheet.getPhysicalNumberOfRows();

			// Get the number of columns (assuming all rows have the same number of columns)
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

			// Create a 2D array to store data
			Object[][] data = new Object[rowCount - 1][colCount];

			// Skip the header row and populate the data array
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					Cell cell = row.getCell(j);
					// Add a null check before calling toString()
					data[i - 1][j] = (cell != null) ? cell.toString() : null;
				}
			}

			return data;
		} catch (IOException e) {
			throw new RuntimeException("Error reading Excel file: " + excelFilePath, e);
		}
	}

	// Get the Sheet Name
	public static String getNegativeSheetName() {
		return "Negetive";
	}

	// Get the Sheet
	public static Sheet getNegativeSheet() {
		// Initialize the workbook within the method
		try (FileInputStream fis = new FileInputStream(getExcelPath())) {
			Workbook workbook = WorkbookFactory.create(fis);
			return workbook.getSheet(getNegativeSheetName());
		} catch (IOException e) {
			e.printStackTrace(); // Handle the exception appropriately
		}
		return null;
	}

	// Read Data From Excel
	public Object[][] readTestNegetiveDataFromExcel(String excelFilePath) {
		// Open the Excel file
		try (FileInputStream inputStream = new FileInputStream(excelFilePath)) {
			// Assuming data is present in the first sheet (Sheet at index 0)
			Sheet sheet = getNegativeSheet();

			// Get the number of rows in the sheet
			int rowCount = sheet.getPhysicalNumberOfRows();

			// Get the number of columns (assuming all rows have the same number of columns)
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

			// Create a 2D array to store data
			Object[][] data = new Object[rowCount - 1][colCount];

			// Skip the header row and populate the data array
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					Cell cell = row.getCell(j);
					// Add a null check before calling toString()
					data[i - 1][j] = (cell != null) ? cell.toString() : null;
				}
			}

			return data;
		} catch (IOException e) {
			throw new RuntimeException("Error reading Excel file: " + excelFilePath, e);
		}
	}

	// Get the Sheet Name for BoundaryValue
	public static String getBoundryValueSheetName() {
		return "Sheet1"; // Corrected sheet name
	}

	// Get the Sheet for BoundaryValue
	public static Sheet getBoundryValueSheet() {
		// Initialize the workbook within the method
		try (FileInputStream fis = new FileInputStream(getExcelPath())) {
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(getBoundryValueSheetName());
			if (sheet == null) {
				throw new RuntimeException("Sheet not found: " + getBoundryValueSheetName());
			}
			return sheet;
		} catch (IOException e) {
			e.printStackTrace(); // Handle the exception appropriately
		}
		return null;
	}

	// Read Data From Excel
	public Object[][] readTestBoundaryValueDataFromExcel(String excelFilePath) {
		// Open the Excel file
		try (FileInputStream inputStream = new FileInputStream(excelFilePath)) {
			// Assuming data is present in the first sheet (Sheet at index 0)
			Sheet sheet = getBoundryValueSheet();

			// Get the number of rows in the sheet
			int rowCount = sheet.getPhysicalNumberOfRows();

			// Get the number of columns (assuming all rows have the same number of columns)
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

			// Create a 2D array to store data
			Object[][] data = new Object[rowCount - 1][colCount];

			// Skip the header row and populate the data array
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					Cell cell = row.getCell(j);
					// Add a null check before calling toString()
					data[i - 1][j] = (cell != null) ? cell.toString() : null;
				}
			}

			return data;
		} catch (IOException e) {
			throw new RuntimeException("Error reading Excel file: " + excelFilePath, e);
		}
	}

	public void enterValue(WebDriver driver, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Define invalid input characters
		String invalidCharacters = "abcdefghijklmnopqrstuvwxyz@#$";

		// Iterate through each character in the input value
		for (char digit : value.toCharArray()) {
			String digitAsString = String.valueOf(digit);

			// Check if the character is an invalid input
			if (invalidCharacters.contains(digitAsString)) {
				// Handle invalid input (e.g., logging, reporting)
				System.out.println("Invalid character: " + digitAsString);
			} else {
				// Wait for the element with the current digit to be visible
				WebElement element = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[text()='" + digitAsString + "']")));

				// Click on the element
				element.click();
				System.out.println("Attempting to click element with value: " + digitAsString);
			}
		}
	}

	// Check if operand contains invalid characters
	public boolean isValidOperand(String operand) {
		String invalidCharacters = "abcdefghijklmnopqrstuvwxyz@#$";
		for (char digit : operand.toCharArray()) {
			String digitAsString = String.valueOf(digit);
			if (invalidCharacters.contains(digitAsString)) {
				return false;
			}
		}
		return true;
	}

	// This method retrieves the entered value from the calculator input field
	protected static StringBuilder enteredValue = new StringBuilder();

	// Method to get the entered value
	public static String getEnteredValue() {
		// Return the entered value as a string
		return enteredValue.toString();
	}

	public void tearDown() {
		// Close the browser after each test method
		closeDriver();
	}

	// Close the WebDriver
	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null; // Set it to null to indicate that the WebDriver is closed
		}
	}
}
