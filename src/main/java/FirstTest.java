import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    String ChromePatch = "C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe";
    String ChromeDriver = "E:\\chromedriver_win32\\chromedriver.exe";


    public String PleseholderTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("https://qna.habr.com/");
        try {
            Thread.sleep(1000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
         * check plaseholder
         * */

        String s;
        try {
            s = driver.findElement(By.xpath("//input[@placeholder='Найти вопрос, ответ, тег или пользователя']")).getAttribute("placeholder");
        } catch (Exception e) {
            return "placeholder not found";
        }
        driver.quit();
        if (s.equals("Найти вопрос, ответ, тег или пользователя")) {
            return "";
        } else {
            Assert.fail("placeholder not found");
            return "placeholder not found";
        }
    }

    @Test
    public String CheckSeleniumTag() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("https://qna.habr.com/");
        try {
            Thread.sleep(1000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium");
        searchBox.sendKeys(Keys.UP);
        searchBox.sendKeys(Keys.UP);
        searchBox.sendKeys(Keys.ENTER);

        //олучаем едеьенты
        List<WebElement> posts = driver.findElementsByClassName("content-list__item");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement tagsList = null;
        posts.get(2).click();
        try {
            tagsList = driver.findElementByXPath("//a[contains(text(),'Selenium')]");
        } catch (Exception e) {
            return "tags not found";
        }

        driver.quit();
        if (tagsList == null) {
            return "tags not found";
        } else {
            return "";
        }
    }


    @Test
    public String FirstTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("https://qna.habr.com/");
        try {
            Thread.sleep(1000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
         * check plaseholder
         * */
        String s = "";
        try {
            s = driver.findElement(By.xpath("//input[@placeholder='Найти вопрос, ответ, тег или пользователя']")).getAttribute("placeholder");

        } catch (Exception e) {
            return "placeholder not found";
        }
        if (s.equals("Найти вопрос, ответ, тег или пользователя")) {
        } else {
            return "placeholder not found";
        }


        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium");
        searchBox.sendKeys(Keys.UP);
        searchBox.sendKeys(Keys.UP);
        searchBox.sendKeys(Keys.ENTER);
        WebElement tagsList = null;
        try {
            List<WebElement> posts = driver.findElementsByClassName("content-list__item");
            TimeUnit.SECONDS.sleep(2);
            posts.get(2).click();
            tagsList = driver.findElementByXPath("//a[contains(text(),'Selenium')]");
        } catch (Exception e) {
            return "tags not found";
        }


        //open naw bar
        TimeUnit.SECONDS.sleep(2);
        WebElement nawBar;
        try {
             nawBar = driver.findElementByClassName("btn_navbar_toggle");
        }catch (Exception e){
            return "nawbar not found";
        }

        if (nawBar == null) {
            driver.quit();
            return "nawbar not found";

        } else {
            nawBar.click();
            TimeUnit.SECONDS.sleep(2);
            nawBar.click();
        }
        TimeUnit.SECONDS.sleep(2);
        driver.executeScript("window.scrollTo(0, document.body.scrollHeight*0.37);");
        WebElement share=null;
        try {
            share = driver.findElementByCssSelector(".dropdown:nth-child(4) > .btn");
            if (share != null) {
                share.click();
            } else {
                driver.quit();
                return "share not found";
            }
        }catch (Exception e){
            return "share not found";
        }

        TimeUnit.SECONDS.sleep(2);
        try {


            share = driver.findElementByCssSelector(".dropdown_active .link_tw");
            if (share != null) {
                share.click();
            } else {
                driver.quit();
                Assert.fail("Twittrer not found");
                return "Twittrer not found";
            }
        }catch (Exception e){
            return "Twittrer not found";
        }
        TimeUnit.SECONDS.sleep(2);
        // check Tritter Page on new window
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String new_url = driver.getCurrentUrl().toString();
        driver.quit();
        if (new_url.contains("/twitter.com/intent/tweet?text=")) {
            return "";
        } else {
            return "Twitter page not open";
        }
    }


}
