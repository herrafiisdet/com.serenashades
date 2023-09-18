package Utils;

import StepDefinitions.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer {
    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication() {
        ConfigReader.ReadProperties();
        String browserType = ConfigReader.getPropertyValue("browserType");
        switch (browserType) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Internet Explorer":
                driver = new InternetExplorerDriver();
                break;
            default:
                driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.WAIT_TIME));
        initializePageObjects();
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("This is the beginning of my Test Case");
        Log.info("My test case is executing right now");
        Log.warning("My test case might have some issues");
    }

    public static void closeBrowser() {
        Log.info("This test case is about to get completed");
        Log.endTestCase("This  Test Case is finished");
        driver.close();
    }
    //------------------- CLICK METHOD ---------------------------------------------------

    public static void doClick(WebElement element) {
        element.click();
    }

    //--------------------- CLEAR TEXT BOX AND SEND TEXT METHOD ----------------------
    public static void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
    public static void clearSentTextForRequiredTextBox(WebElement element,String text){
        element.sendKeys(text);
        element.clear();
    }

    //-------------------- DROP DOWN & SELECT TEXT BY VALUE METHOD -----------------------------------------
    public static Select clickDropDown(WebElement element) {
        Select select = new Select(element);
        return select;
    }
    //Select by Value
    public static void selectByValue(WebElement element,String value){

        clickDropDown(element).selectByValue(value);
    }

    // select by visible text method
    public static void selectByVisibleText(WebElement element, String visibleText) {
        clickDropDown(element).selectByVisibleText(visibleText);
    }
    //-------------------------- SCREENSHOT METHOD --------------------------------------

    public static byte[] takeScreenshot(String picName) {

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        byte[] picBytes = screenshot.getScreenshotAs(OutputType.BYTES);
        File sourcePath = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourcePath, new File(Constants.SCREENSHOT_FILE_PATH + picName + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return picBytes;
    }

    //-------------------------- TIME STAMP FOR LOG -----------------------------------------------
    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
    //---------------------------HowerOver Method--------------------
    public static void howerOver (WebElement mainMenu ){
        Actions actions=new Actions(driver);
        actions.moveToElement(mainMenu).perform();
        doClick(mainMenu);
    }
    //--------------------------Calender  method -------------------
    //This method takes 4 parameters
    //1st WebElement this is to activate your Calendar! Store a locator for the caldera you want activate by clicking.
    //2nd Enter the year!
    //3rd month  in this format>> "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    //4th Enter a day between 1-31.
    /*public void selectDateFromCalendar(WebElement activateCalender,String year, String month, String day) {
        doClick(activateCalender);
        selectByValue(employeeWorkExperiencePage.selectYear, year);//Select year
        selectByVisibleText(employeeWorkExperiencePage.selectMonth, month);//Select month
        String daysXpath = Constants.ENTER + day +Constants.BETWEEN_1_TO_30 ; //Enter the day you want to click.
        WebElement dayXpath = driver.findElement(By.xpath(daysXpath));
        dayXpath.click();
    }

     */
}