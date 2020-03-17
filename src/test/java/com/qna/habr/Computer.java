package com.qna.habr;


import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Computer {

    @Test
    public void CheckPlaceholderl() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");
        try {
            Thread.sleep(1000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //check placeholder
        String s = driver.findElement(By.xpath("//input[@placeholder='Filter by computer name...']")).getAttribute("placeholder");
        if (s.equals("Filter by computer name...")) {
            System.out.println("plaseholder found");
        } else {
            System.out.println("plaseholder not found");
            Assert.fail("placeholder not found");
        }

        /*By API request create new PC*/

        String Name = getAlphaNumericString(10);
        System.out.println("PC name " + Name);
        try {
            CreatePCByRequwest(Name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchName(Name);
        /*
         * now try found PC
         * */
    }


    public void SearchName(String name) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");
        try {
            Thread.sleep(1000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement searchBox = driver.findElement(By.id("searchbox"));
        searchBox.sendKeys(name);

        WebElement SubmitButton = driver.findElement(By.id("searchsubmit"));
        SubmitButton.click();

        /*Check computer on page*/
        WebElement link = driver.findElementByXPath("//a[contains(text(),'" + name + "')]");

        if (link == null) {
            Assert.fail("tags not found");
        }

        //open naw bar
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        link.click();
        /*Now Search name on page*/
        String s = driver.findElement(By.xpath("//input[@value='"+name+"']")).getAttribute("value");
        if (s.equals(name)) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("placeholder not found");
        }
    }


    public void CreatePCByRequwest(String string) throws IOException {
        String url = "http://computer-database.gatling.io/computers";

        HttpPost post = new HttpPost(url);
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("name", string));
        urlParameters.add(new BasicNameValuePair("introduced", "2020-03-17"));
        urlParameters.add(new BasicNameValuePair("discontinued", "2020-03-17"));
        urlParameters.add(new BasicNameValuePair("company", "7"));
        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}
