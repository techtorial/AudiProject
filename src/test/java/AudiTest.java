import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.util.List;

public class AudiTest {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        softAssert=new SoftAssert();
        driver.manage().window().maximize();
        driver.manage().window().maximize();
    }


    @Test
    public void test1() throws InterruptedException {
        driver.get("https://www.audiusa.com/");

        BrowserUtils.scrollIntoView(driver,driver.findElement(By.xpath("//label//span[.='SUVs & Wagons']")));
        WebElement suv = driver.findElement(By.xpath("//label//span[.='SUVs & Wagons']"));
        suv.click();
        Thread.sleep(1000);
        WebElement q5 = driver.findElement(By.xpath("(//span[.='2021 Q5'])[2]"));
        q5.click();
        WebElement header = driver.findElement(By.xpath("//h1"));
        String actualHeader = header.getText().trim();
        String expectedHeader = "2021 Audi Q5";
        softAssert.assertEquals(actualHeader, expectedHeader);
        WebElement build = driver.findElement(By.xpath("//div[contains(@class , 'linkarea')]//span[contains(., 'Build')]"));
        build.click();
        Thread.sleep(1000);
        BrowserUtils.scrollIntoView(driver,driver.findElement(By.xpath("//div[.='Premium']")));
        // Premium Validation
        WebElement premium = driver.findElement(By.xpath("//div[.='Premium']"));
        softAssert.assertEquals(premium.getText(), "Premium");
        WebElement premiumPrice=driver.findElement(By.xpath("//div[.='Premium']/following-sibling::div"));
        softAssert.assertTrue(premiumPrice.getText().trim().endsWith("43,300"));

        // Premioum Plus
        WebElement premiumPlus = driver.findElement(By.xpath("//div[.='Premium Plus']"));
        softAssert.assertEquals(premiumPlus.getText(), "Premium Plus");
        WebElement premiumPlusPrice=driver.findElement(By.xpath("//div[.='Premium Plus']/following-sibling::div"));
        softAssert.assertTrue(premiumPlusPrice.getText().trim().endsWith("48,100"));

        // Prestige
        WebElement prestige = driver.findElement(By.xpath("//div[.='Prestige']"));
        softAssert.assertEquals(prestige.getText(), "Prestige");
        WebElement prestigePrice=driver.findElement(By.xpath("//div[.='Prestige']/following-sibling::div"));
        softAssert.assertTrue(prestigePrice.getText().trim().endsWith("54,000"));

        List<WebElement> tfsi45=driver.findElements(By.xpath("//div[.='45 TFSI®']"));
        for(WebElement tf : tfsi45){
            softAssert.assertEquals(tf.getText().trim(), "45 TFSI®");
        }

        List<WebElement> hybrids=driver.findElements(By.xpath("//div[.='55 TFSI® e Plug-in hybrid']"));
        for(WebElement hybrid : hybrids){
            softAssert.assertEquals(hybrid.getText().trim(), "55 TFSI® e Plug-in hybrid");
        }

        softAssert.assertAll();
    }


    @Test
    public void test2() throws InterruptedException {

        WebElement tfsi45=driver.findElement(By.xpath("(//span[contains(@class, 'audi-checkbox-symbol')])[3]"));
        tfsi45.click();
        BrowserUtils.scrollIntoView(driver, driver.findElement(By.xpath("//span[.='Continue']")));
        WebElement continueButton=driver.findElement(By.xpath("//span[.='Continue']"));
        continueButton.click();

        BrowserUtils.scrollIntoView(driver, driver.findElement(By.xpath("//div[contains(@class, 'optionsPrice')]")));
        WebElement optionalPrice=driver.findElement(By.xpath("//div[contains(@class, 'optionsPrice')]"));
        softAssert.assertTrue(optionalPrice.getText().trim().endsWith("0"));

        WebElement destinationCharge=driver.findElement(By.xpath("//div[contains(@class, 'destination-charge-amount')]"));
        softAssert.assertTrue(destinationCharge.getText().trim().endsWith("1,095"),"destination charge is validated");

        Thread.sleep(500);
        WebElement blueNavarra=driver.findElement(By.xpath("//div[@data-render-view='extcolors']//li[@data-mbvid='F14 2D2D']"));
        BrowserUtils.hoverOver(driver, blueNavarra);
        blueNavarra.click();

        Thread.sleep(500);
        BrowserUtils.scrollIntoView(driver,driver.findElement(By.xpath("//div[contains(text(),'Navarra Blue metallic')]")));
        WebElement colorText=driver.findElement(By.xpath("//div[contains(text(),'Navarra Blue metallic')]"));
        softAssert.assertEquals(colorText.getText().trim(), "Navarra Blue metallic","assert message ");

        BrowserUtils.scrollIntoView(driver,driver.findElement(By.xpath("//div[contains(text(),'Navarra Blue metallic')]/following-sibling::span")));
        WebElement colorPrice=driver.findElement(By.xpath("//div[contains(text(),'Navarra Blue metallic')]/following-sibling::span"));
        softAssert.assertTrue(colorPrice.getText().trim().endsWith("595"));

        softAssert.assertAll();
    }

}
