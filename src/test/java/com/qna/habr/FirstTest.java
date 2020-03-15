package com.qna.habr;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static java.lang.Thread.*;

public class FirstTest {

    @Test
    public void FirstTest() {
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
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium");
        searchBox.sendKeys(Keys.UP);
        searchBox.sendKeys(Keys.UP);
        searchBox.sendKeys(Keys.ENTER);

        //олучаем едеьенты
        List<WebElement> posts= driver.findElementsByClassName("content-list__item");

        System.out.println("posts");
           posts.get(2).click();

    }


}
