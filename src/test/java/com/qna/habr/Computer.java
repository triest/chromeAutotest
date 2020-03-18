package com.qna.habr;


import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    @Test
    public void addPC(){
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

        WebElement addButton = driver.findElement(By.id("add"));
       addButton.click();
        String name=getAlphaNumericString(10);
       //add name
        WebElement searchBox = driver.findElement(By.id("name"));
        searchBox.sendKeys(name);

        //add introduced
         searchBox = driver.findElement(By.id("introduced"));
        searchBox.sendKeys("2020-05-15");

        //add name
        searchBox = driver.findElement(By.id("discontinued"));
        searchBox.sendKeys("2020-05-15");

        searchBox = driver.findElement(By.id("company"));
        searchBox.click();
        searchBox.sendKeys(Keys.DOWN);
        searchBox.sendKeys(Keys.DOWN);
        searchBox.sendKeys(Keys.DOWN);
        searchBox.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(10000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

     WebElement  submit = driver.findElementByCssSelector(".primary");
        submit.click();



        //now Search element
         searchBox = driver.findElement(By.id("searchbox"));
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

        /*Check data*/
        searchBox = driver.findElement(By.id("name"));

        String new_name=searchBox.getAttribute("value");
        System.out.println("new_name");
        System.out.println(new_name);

        if(new_name.equals(name)){
            System.out.println("true");
        }else {
            Assert.fail("name not found");
        }

        searchBox = driver.findElement(By.id("introduced"));

        new_name=searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if(new_name.equals("2020-05-15")){
            System.out.println("true");
        }else {
            Assert.fail("introduced ");
        }


        searchBox = driver.findElement(By.id("discontinued"));

        new_name=searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if(new_name.equals("2020-05-15")){
            System.out.println("true");
        }else {
            Assert.fail("discontinued");
        }

    }


    public String addPCFunction(){
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

        WebElement addButton = driver.findElement(By.id("add"));
        addButton.click();
        String name=getAlphaNumericString(10);
        //add name
        WebElement searchBox = driver.findElement(By.id("name"));
        searchBox.sendKeys(name);

        //add introduced
        searchBox = driver.findElement(By.id("introduced"));
        searchBox.sendKeys("2020-05-15");

        //add name
        searchBox = driver.findElement(By.id("discontinued"));
        searchBox.sendKeys("2020-05-15");

        searchBox = driver.findElement(By.id("company"));
        searchBox.click();
        searchBox.sendKeys(Keys.DOWN);
        searchBox.sendKeys(Keys.DOWN);
        searchBox.sendKeys(Keys.DOWN);
        searchBox.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(10000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement  submit = driver.findElementByCssSelector(".primary");
        submit.click();



        //now Search element
        searchBox = driver.findElement(By.id("searchbox"));
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

        /*Check data*/
        searchBox = driver.findElement(By.id("name"));

        String new_name=searchBox.getAttribute("value");
        System.out.println("new_name");
        System.out.println(new_name);

        if(new_name.equals(name)){
            System.out.println("true");
        }else {
            Assert.fail("name not found");
        }

        searchBox = driver.findElement(By.id("introduced"));

        new_name=searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if(new_name.equals("2020-05-15")){
            System.out.println("true");
        }else {
            Assert.fail("introduced ");
        }


        searchBox = driver.findElement(By.id("discontinued"));

        new_name=searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if(new_name.equals("2020-05-15")){
            System.out.println("true");
        }else {
            Assert.fail("discontinued");
        }
        return name;
    }

    @Test
    public void editPC(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");

          //String name=addPCFunction();
        String     name="1sa4FfYyQu";
        WebElement    searchBox = driver.findElement(By.id("searchbox"));
        searchBox.sendKeys(name);

        WebElement SubmitButton = driver.findElement(By.id("searchsubmit"));
        SubmitButton.click();

        /*Check computer on page*/
        WebElement link = driver.findElementByXPath("//a[contains(text(),'" + name + "')]");

        if (link == null) {
            Assert.fail("tags not found");
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        link.click();


//        WebElement addButton = driver.findElement(By.id("add"));
 //       addButton.click();
         name=getAlphaNumericString(10);
        //add name
         searchBox = driver.findElement(By.id("name"));
        searchBox.sendKeys(name);

        //add introduced
        searchBox = driver.findElement(By.id("introduced"));
      //  searchBox.sendKeys("2020-05-20");

        //add name
        searchBox = driver.findElement(By.id("discontinued"));
//        searchBox.sendKeys("2020-05-20");

        searchBox = driver.findElement(By.id("company"));
        searchBox.click();
        searchBox.sendKeys(Keys.DOWN);
        searchBox.sendKeys(Keys.DOWN);
        searchBox.sendKeys(Keys.DOWN);
        searchBox.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(10000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("edit key");
        WebElement  submit = driver.findElementByCssSelector(".primary");
        submit.click();


        if(checkPC(name)){
            Assert.assertTrue(true);
        }else {
            Assert.fail("discontinued");
        }

    }

    public boolean checkPC(String name){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");

     WebElement   searchBox = driver.findElement(By.id("searchbox"));
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

        /*Check data*/
        searchBox = driver.findElement(By.id("name"));

        String new_name=searchBox.getAttribute("value");
        System.out.println("new_name");
        System.out.println(new_name);

        if(new_name.equals(name)){
            System.out.println("true");
        }else {
            Assert.fail("name not found");
        }

        searchBox = driver.findElement(By.id("introduced"));

        new_name=searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if(new_name.equals("2020-05-15")){
            System.out.println("true");
        }else {
            return  false;
        }


        searchBox = driver.findElement(By.id("discontinued"));

        new_name=searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if(new_name.equals("2020-05-15")){

        }else {
           return  false;
        }
        return  true;
    }

}
