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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ComputerTest {

    String ChromePatch = "C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe";
    String ChromeDriver = "E:\\chromedriver_win32\\chromedriver.exe";

    @Test
    public String CheckPlaceholderl() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
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
        } else {
            return "placeholder not found";
        }

        /*By API request create new PC*/

        String Name = getAlphaNumericString(10);
        try {
            CreatePCByRequest(Name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         * now try found PC
         * */
        driver.quit();
        return "";
    }


    public String SearchNameTest(String name) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
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
            return "tags not found";
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
            return "";
        } else {
            return "placeholder not found";
        }
    }


    public void CreatePCByRequest(String string) throws IOException {
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


    public String addPC() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");
        try {
            Thread.sleep(1000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement searchBox = null;
        String name = "";
        try {
            WebElement addButton = driver.findElement(By.id("add"));
            addButton.click();
            name = getAlphaNumericString(10);
            //add name
            searchBox = driver.findElement(By.id("name"));
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
        } catch (Exception e) {
            return "add PC FAIL";
        }


        /*Check computer on page*/
        WebElement link;
        try {
            link = driver.findElementByXPath("//a[contains(text(),'" + name + "')]");
        } catch (Exception e) {
            return "tags not found";
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


        if (!new_name.equals(name)) {
            return "name not found";
        }

        try {
            searchBox = driver.findElement(By.id("introduced"));
            new_name = searchBox.getAttribute("value");
            if (!new_name.equals("2020-05-15")) {
                return "introduced ";
            }
        } catch (Exception e) {
            return "introduced ";
        }

        try {
            searchBox = driver.findElement(By.id("discontinued"));

            new_name = searchBox.getAttribute("value");
            if (!new_name.equals("2020-05-15")) {
                return "discontinued";
            }
        } catch (Exception e) {
            return "discontinued";
        }

        driver.quit();
        return "";
    }


    public String addPCFunction() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");
        try {
            Thread.sleep(1000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = getAlphaNumericString(10);
        WebElement searchBox;
        try {
            WebElement addButton = driver.findElement(By.id("add"));
            addButton.click();

            //add name
            searchBox = driver.findElement(By.id("name"));
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
        } catch (Exception e) {
            return "edit not passed";
        }


        try {
            Thread.sleep(10000);  // Let the user actually see something!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement submit = driver.findElementByCssSelector(".primary");
        submit.click();
        WebElement link = null;

        //now Search element
        try {
            searchBox = driver.findElement(By.id("searchbox"));
            searchBox.sendKeys(name);

            WebElement SubmitButton = driver.findElement(By.id("searchsubmit"));
            SubmitButton.click();

            /*Check computer on page*/
            link = driver.findElementByXPath("//a[contains(text(),'" + name + "')]");
        } catch (Exception e) {
            return "link not found";
        }

        if (link == null) {
            return "tags not found";
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

        if (!new_name.equals(name)) {
            return "name not found";
        }
        try {
            searchBox = driver.findElement(By.id("introduced"));

            new_name = searchBox.getAttribute("value");
        } catch (Exception e) {
            return "introduced ";
        }


        if (!new_name.equals("2020-05-15")) {
            return "introduced ";
        }

        try {
            searchBox = driver.findElement(By.id("discontinued"));

            new_name = searchBox.getAttribute("value");
        } catch (Exception e) {
            return "discontinued";
        }

        if (!new_name.equals("2020-05-15")) {
            Assert.fail("discontinued");
        }
        driver.quit();
        return name;
    }


    public String editPC() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");

        String name = addPCFunction();
        WebElement searchBox = null;
        WebElement link = null;
        try {
            searchBox = driver.findElement(By.id("searchbox"));
            searchBox.sendKeys(name);

            WebElement SubmitButton = driver.findElement(By.id("searchsubmit"));
            SubmitButton.click();

            /*Check computer on page*/
            link = driver.findElementByXPath("//a[contains(text(),'" + name + "')]");
            if (link == null) {
                return "link not found";
            }
        } catch (Exception e) {
            return "link not found";
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
        try {


            WebElement submit = driver.findElementByCssSelector(".primary");
            submit.click();
        } catch (Exception e) {
            return "discontinued";
        }

        driver.quit();
        if (checkPC(name)) {
            return "";
        } else {
            return "discontinued";
        }

    }


    public String testDelete() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");

        String name = addPCFunction();
        try {
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

            driver.quit();
        } catch (Exception e) {
            return "link not found";
        }


        if (checkPC(name) == false) {
            return "";
        } else {
            return "discontinued";
        }
        //Delete this computer
    }

    public boolean checkPC(String name) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
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
        } catch (org.openqa.selenium.NoSuchElementException e) {
            driver.quit();
            return false;
        }
        if (link == null) {
            driver.quit();
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

        if (new_name.equals(name)) {

        } else {
            driver.quit();
            return false;
        }

        searchBox = driver.findElement(By.id("introduced"));

        new_name = searchBox.getAttribute("value");

        if (!new_name.equals("2020-05-15")) {
            driver.quit();
            return false;
        }


        searchBox = driver.findElement(By.id("discontinued"));

        new_name = searchBox.getAttribute("value");

        driver.quit();
        if (new_name.equals("2020-05-15")) {

        } else {
            return false;
        }
        return true;
    }


    public String testFormValidate() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");
        WebElement addButton = driver.findElement(By.id("add"));
        addButton.click();
        WebElement submit = null;
        try {
            submit = driver.findElementByCssSelector(".primary");
            submit.click();

        } catch (Exception e) {
            return "submit button not found";
        }

        WebElement span = driver.findElement(By.cssSelector(".error .help-inline"));
        System.out.println(span.getText());
        if (span.getText().equals("Required") == false) {
            return "Name require not valid";
        }

        String name = getAlphaNumericString(10);
        WebElement searchBox = driver.findElement(By.id("name"));
        searchBox.sendKeys(name);

        WebElement introducedBox = driver.findElement(By.id("introduced"));
        introducedBox.sendKeys(name);


        span = driver.findElement(By.xpath("//section[@id='main']/form/fieldset/div[2]/div"));


        if (!span.getText().equals("Date ('yyyy-MM-dd')")) {
            driver.quit();
            return "Check introduced date format fail";
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
        WebElement discontinuedBox = null;
        try {
            discontinuedBox = driver.findElement(By.id("discontinued"));
            discontinuedBox.sendKeys("202");
        } catch (Exception e) {
            return "discontinued not fiund";
        }


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        submit = driver.findElementByCssSelector(".primary");
        submit.click();
        try {


            span = driver.findElement(By.xpath("//section[@id='main']/form/fieldset/div[2]/div"));
        } catch (Exception e) {
            return "Check discounted date format fail";
        }

        if (!span.getText().equals("Date ('yyyy-MM-dd')")) {
            return "Check discounted date format fail";
        }
        try {
            discontinuedBox = driver.findElement(By.id("discontinued"));
            discontinuedBox.click();

            for (int i = 0; i < 10; i++) {
                discontinuedBox.sendKeys(Keys.BACK_SPACE);
            }
        } catch (Exception e) {
            return "discontinued not fund";
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
        try {
            submit = driver.findElementByCssSelector(".primary");
        } catch (Exception e) {
            return "Exception not found";
        }

        submit.click();
        try {

        } catch (Exception e) {
            span = driver.findElement(By.xpath("//section[@id='main']/form/fieldset/div[2]/div"));

            if (!span.getText().equals("Date ('yyyy-MM-dd')")) {
                return "Check discoumter date format fail";
            }

        }

        try {
            discontinuedBox = driver.findElement(By.id("discontinued"));
            discontinuedBox.click();
            for (int i = 0; i < 10; i++) {
                discontinuedBox.sendKeys(Keys.BACK_SPACE);
            }
        } catch (Exception e) {
            return "Check discoumter date format fail";
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

        driver.quit();
        return "";

    }

    public String check404() {

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

        if (status == 404) {
            return "";
        } else {
            return "not 404";
        }


    }

    public String TestBackButton() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(ChromePatch);
        System.setProperty("webdriver.chrome.driver", ChromeDriver);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1080, 960));
        driver.get("http://computer-database.gatling.io/computers");
        WebElement Button = null;
        try {
            Button = driver.findElement(By.xpath("//a[contains(text(),'Next →')]"));
            Button.click();
        } catch (Exception e) {
            return "next button not found";
        }

        try {
            List<WebElement> link = driver.findElements(By.xpath("//a[contains(@href, '/computers/')]"));
            link.get(10).click();
            ////a[contains(text(),'Cancel')]
            Button = driver.findElementByXPath("//a[contains(text(),'Cancel')]");
            Button.click();
            String new_url = driver.getCurrentUrl().toString();
        } catch (Exception e) {
            return "links to computers not found";
        }
        List<WebElement> link = driver.findElements(By.xpath("//a[contains(@href, '/computers/')]"));
        link.get(10).click();
        ////a[contains(text(),'Cancel')]
        Button = driver.findElementByXPath("//a[contains(text(),'Cancel')]");
        Button.click();

        String new_url = driver.getCurrentUrl().toString();
        driver.close();
        if (new_url.equals("http://computer-database.gatling.io/computers")) {
            return "";
        } else {
            return "test Back Button Fail";
        }


    }


}
