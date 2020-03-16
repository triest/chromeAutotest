package com.qna.habr;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

public class FirstTest {

    @Test
    public void FirstTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
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
        String s=driver.findElement(By.xpath("//input[@placeholder='Найти вопрос, ответ, тег или пользователя']")).getAttribute("placeholder");
        if(s.equals("Найти вопрос, ответ, тег или пользователя")){
        }else {
            Assert.fail("placeholder not found");
        }


        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium");
        searchBox.sendKeys(Keys.UP);
        searchBox.sendKeys(Keys.UP);
        searchBox.sendKeys(Keys.ENTER);

        //олучаем едеьенты
        List<WebElement> posts = driver.findElementsByClassName("content-list__item");
        TimeUnit.SECONDS.sleep(2);
        posts.get(2).click();

        WebElement tagsList = driver.findElementByXPath("//a[contains(text(),'Selenium')]");

        if (tagsList == null) {
            Assert.fail("tags not found");
        }


        //open naw bar
        TimeUnit.SECONDS.sleep(2);

        WebElement nawBar = driver.findElementByClassName("btn_navbar_toggle");
        if (nawBar == null) {
            Assert.fail("nawbar not found");
            System.exit(1);
        } else {
            nawBar.click();
            TimeUnit.SECONDS.sleep(2);
            nawBar.click();
        }
        TimeUnit.SECONDS.sleep(2);
        driver.executeScript("window.scrollTo(0, document.body.scrollHeight*0.37);");

        WebElement share = driver.findElementByCssSelector(".dropdown:nth-child(4) > .btn");
        if (share != null) {
         share.click();
        } else {
            Assert.fail("share not found");
        }
        TimeUnit.SECONDS.sleep(2);

        share = driver.findElementByCssSelector(".dropdown_active .link_tw");
        if (share != null) {
            share.click();
        } else {
            Assert.fail("Twittrer not found");
        }
        TimeUnit.SECONDS.sleep(2);
        // check Tritter Page on new window
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        System.out.println(driver.getCurrentUrl().toString());
        String new_url=driver.getCurrentUrl().toString();
        if(new_url.contains("/twitter.com/intent/tweet?text=")){
            System.out.println("pass");
        }else {
            Assert.fail("Twittrer page not open");
        }
        driver.quit();
    }


}
