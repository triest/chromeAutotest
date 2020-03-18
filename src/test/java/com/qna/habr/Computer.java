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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Key;
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
        String s = driver.findElement(By.xpath("//input[@value='" + name + "']")).getAttribute("value");
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
    public void addPC() {
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
        String name = getAlphaNumericString(10);
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

        WebElement submit = driver.findElementByCssSelector(".primary");
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

        String new_name = searchBox.getAttribute("value");
        System.out.println("new_name");
        System.out.println(new_name);

        if (new_name.equals(name)) {
            System.out.println("true");
        } else {
            Assert.fail("name not found");
        }

        searchBox = driver.findElement(By.id("introduced"));

        new_name = searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if (new_name.equals("2020-05-15")) {
            System.out.println("true");
        } else {
            Assert.fail("introduced ");
        }


        searchBox = driver.findElement(By.id("discontinued"));

        new_name = searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if (new_name.equals("2020-05-15")) {
            System.out.println("true");
        } else {
            Assert.fail("discontinued");
        }

    }


    public String addPCFunction() {
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
        String name = getAlphaNumericString(10);
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

        WebElement submit = driver.findElementByCssSelector(".primary");
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

        String new_name = searchBox.getAttribute("value");
        System.out.println("new_name");
        System.out.println(new_name);

        if (new_name.equals(name)) {
            System.out.println("true");
        } else {
            Assert.fail("name not found");
        }

        searchBox = driver.findElement(By.id("introduced"));

        new_name = searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if (new_name.equals("2020-05-15")) {
            System.out.println("true");
        } else {
            Assert.fail("introduced ");
        }


        searchBox = driver.findElement(By.id("discontinued"));

        new_name = searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if (new_name.equals("2020-05-15")) {
            System.out.println("true");
        } else {
            Assert.fail("discontinued");
        }
        return name;
    }

    @Test
    public void editPC() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");

        String name = addPCFunction();

        WebElement searchBox = driver.findElement(By.id("searchbox"));
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
        name = getAlphaNumericString(10);
        //add name
        searchBox = driver.findElement(By.id("name"));
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
        searchBox.sendKeys(Keys.BACK_SPACE);
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
        WebElement submit = driver.findElementByCssSelector(".primary");
        submit.click();


        if (checkPC(name)) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("discontinued");
        }

    }

    @Test
    public void testDelete() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");

        String name = addPCFunction();


        WebElement searchBox = driver.findElement(By.id("searchbox"));
        searchBox.sendKeys(name);

        WebElement SubmitButton = driver.findElement(By.id("searchsubmit"));
        SubmitButton.click();

        /*Check computer on page*/
        WebElement link = driver.findElementByXPath("//a[contains(text(),'" + name + "')]");


        //open naw bar
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        link.click();

        WebElement submit = driver.findElementByCssSelector(".danger");
        submit.click();


        if (checkPC(name) == false) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("discontinued");
        }
        //Delete this computer
    }

    public boolean checkPC(String name) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");

        WebElement searchBox = driver.findElement(By.id("searchbox"));
        searchBox.sendKeys(name);

        WebElement SubmitButton = driver.findElement(By.id("searchsubmit"));
        SubmitButton.click();


        /*Check computer on page*/
        WebElement link;
        try {
            link = driver.findElementByXPath("//a[contains(text(),'" + name + "')]");
        } catch (org.openqa.selenium.NoSuchElementException e)  {
            System.out.println("NoSuchElementException!!");
            return false;
        }
        if (link == null) {
            return false;
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

        String new_name = searchBox.getAttribute("value");
        System.out.println("new_name");
        System.out.println(new_name);
        System.out.println("name");
        System.out.println(name);
        if (new_name.equals(name)) {

        } else {
            return false;
        }

        searchBox = driver.findElement(By.id("introduced"));

        new_name = searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if (new_name.equals("2020-05-15")) {
            System.out.println("true");
        } else {
            return false;
        }


        searchBox = driver.findElement(By.id("discontinued"));

        new_name = searchBox.getAttribute("value");
        System.out.println("introduced");
        System.out.println(new_name);

        if (new_name.equals("2020-05-15")) {

        } else {
            return false;
        }
        return true;
    }


    @Test
    public void TestFormValidate() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");
        WebElement addButton = driver.findElement(By.id("add"));
        addButton.click();

        WebElement submit = driver.findElementByCssSelector(".primary");
        submit.click();

        WebElement span = driver.findElement(By.cssSelector(".error .help-inline"));
        System.out.println(span.getText());
        if (span.getText().equals("Required") == false) {
            Assert.fail("Name require not valid");
        }

        String name = getAlphaNumericString(10);
        WebElement searchBox = driver.findElement(By.id("name"));
        searchBox.sendKeys(name);

        WebElement introducedBox = driver.findElement(By.id("introduced"));
        introducedBox.sendKeys(name);

        //xpath=//section[@id='main']/form/fieldset/div[2]/div
        span = driver.findElement(By.xpath("//section[@id='main']/form/fieldset/div[2]/div"));


        if (!span.getText().equals("Date ('yyyy-MM-dd')")) {
            Assert.fail("Check introduced date format fail");
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 30; i++) {
            introducedBox.sendKeys(Keys.BACK_SPACE);
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        introducedBox.sendKeys("2020-05-20");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement discontinuedBox = driver.findElement(By.id("discontinued"));
        discontinuedBox.sendKeys("202");


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        submit = driver.findElementByCssSelector(".primary");
        submit.click();

        span = driver.findElement(By.xpath("//section[@id='main']/form/fieldset/div[2]/div"));


        if (!span.getText().equals("Date ('yyyy-MM-dd')")) {
            Assert.fail("Check discoumter date format fail");
        }
        discontinuedBox = driver.findElement(By.id("discontinued"));
        discontinuedBox.click();

        for (int i = 0; i < 10; i++) {
            discontinuedBox.sendKeys(Keys.BACK_SPACE);
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        discontinuedBox.sendKeys("20-02-2020");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        submit = driver.findElementByCssSelector(".primary");
        submit.click();

        span = driver.findElement(By.xpath("//section[@id='main']/form/fieldset/div[2]/div"));

        if (!span.getText().equals("Date ('yyyy-MM-dd')")) {
            Assert.fail("Check discoumter date format fail");
        }


        discontinuedBox = driver.findElement(By.id("discontinued"));
        discontinuedBox.click();
        for (int i = 0; i < 10; i++) {
            discontinuedBox.sendKeys(Keys.BACK_SPACE);
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        discontinuedBox.sendKeys("2020-02-02");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        submit = driver.findElementByCssSelector(".primary");
        submit.click();


        Assert.assertTrue(true);
    }

    /**/
    @Test
    public void check404() {

        String Address = "http://computer-database.gatling.io/comput";

        URL url = null;
        try {
            url = new URL(Address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;

        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        con.setRequestProperty("Accept", "application/json");
        int status = 0;
        try {
            status = con.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(status);
        if(status==404){
            Assert.assertTrue(true);
        }else {
            Assert.assertTrue(false);
        }
        

    }

    @Test
    public void TestBackButton(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");
        WebElement Buttom = driver.findElement(By.xpath("//a[contains(text(),'Next →')]"));
        Buttom.click();

        List<WebElement> link=driver.findElements(By.xpath("//a[contains(@href, '/computers/')]"));
        link.get(10).click();
       ////a[contains(text(),'Cancel')]
        Buttom=driver.findElementByXPath("//a[contains(text(),'Cancel')]");
        Buttom.click();

        String new_url=driver.getCurrentUrl().toString();
        if(new_url.equals("http://computer-database.gatling.io/computers")){
            Assert.assertTrue(true);
        }else {
            Assert.assertTrue(false);
        }

        driver.close();
    }


}
