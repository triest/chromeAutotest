package com.qna.habr;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.*;

public class FirstTest {

    @Test
    public void FirstTest()  {
        ChromeOptions chromeOptions= new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver","E:\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver  driver=new ChromeDriver(chromeOptions);
        driver.get("https://qna.habr.com/");
        try {
            Thread.sleep(5000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        try {
            Thread.sleep(5000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // driver.quit();
    }
}
