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
        try {
            CreatePCByRequwest();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void CreatePCByRequwest() throws IOException {
        String url = "http://computer-database.gatling.io/computers";

        HttpPost post = new HttpPost(url);
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("name", "name212dsds21"));
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

            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
