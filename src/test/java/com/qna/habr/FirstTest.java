package com.qna.habr;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import javax.lang.model.element.Element;
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
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium");
        searchBox.sendKeys(Keys.UP);
        searchBox.sendKeys(Keys.UP);
        searchBox.sendKeys(Keys.ENTER);

        //олучаем едеьенты
        List<WebElement> posts = driver.findElementsByClassName("content-list__item");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("posts");
        posts.get(2).click();

        //chech selenium tag exist
        //tags-list

        WebElement tagsList = driver.findElementByCssSelector(".question__tags:nth-child(2) > .tags-list a");
        if (tagsList == null) {
            System.out.println("no found");
        } else {
            System.out.println("found");
        }


        //open naw bar

/*
        WebElement nawBar = driver.findElementByClassName("btn_navbar_toggle");
        if (nawBar == null) {
            System.out.println("nawBar no found");
            System.exit(1);
        } else {
            nawBar.click();
            TimeUnit.SECONDS.sleep(2);
            nawBar.click();
        }
*/
        driver.executeScript("window.scrollTo(0, document.body.scrollHeight*0.37);");

        WebElement share = driver.findElementByCssSelector(".dropdown:nth-child(4) > .btn");
        if (share != null) {

         share.click();
            System.out.println("share not null");
        } else {
            System.out.println("share null");
        }


        share = driver.findElementByCssSelector(".dropdown_active .link_tw");
        if (share != null) {
            share.click();
            System.out.println("share not null");
        } else {

        }

    }


}
