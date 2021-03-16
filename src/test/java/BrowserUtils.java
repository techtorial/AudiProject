import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BrowserUtils {
        // 10 lines of code and I am calling same code in 10 different place--> 100
    // Create one method and store this 10 lines of the code , call ten times this method --> 20
    public static void scrollIntoView(WebDriver driver, WebElement element){
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true)",element);
    }

    public static void hoverOver(WebDriver driver, WebElement element){
        Actions actions=new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void click(WebDriver driver, WebElement element){
        Actions actions=new Actions(driver);
        actions.click(element).perform();
    }

    public static void jsClick(WebDriver driver, WebElement element){
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()",element);
    }
}
