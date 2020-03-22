import java.io.File;
import java.io.FileNotFoundException;
import java.net.ProtocolException;
import java.util.Scanner;

public class Main {

    private static String ChromePatch = "C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe";
    //  private static String ChromePatch = "";
    private static String ChromeDriver = "E:\\chromedriver_win32\\chromedriver.exe";
    //private static String ChromeDriver = "";


    public static void main(String[] args) throws InterruptedException {

        //  readSettings();
        String rez = "";

        FirstTest firstTest = new FirstTest(ChromePatch, ChromeDriver);
        System.out.println(firstTest.PlaceholderTest());


        rez = firstTest.PlaceholderTest();
        if (!rez.equals("")) {
            System.out.println("PlaceholderTest: " + rez);
        } else {
            System.out.println("PlaceholderTest: pass");
        }
        rez = firstTest.CheckSeleniumTag();
        if (!rez.equals("")) {
            System.out.println("CheckSeleniumTag: " + rez);
        } else {
            System.out.println("CheckSeleniumTag: pass");
        }

        rez = firstTest.FirstTest();
        if (!rez.equals("")) {
            System.out.println("FirstTest: " + rez);
        } else {
            System.out.println("FirstTest: pass");
        }


        ComputerTest computerTest = new ComputerTest(ChromePatch, ChromeDriver);
        rez = computerTest.CheckPlaceholderl();
        if (!rez.equals("")) {
            System.out.println("CheckPlaceholder: " + rez);
        } else {
            System.out.println("CheckPlaceholder: pass");
        }


        rez = computerTest.addPC();


        if (!rez.equals("")) {
            System.out.println("addPC: " + rez);
        } else {
            System.out.println("addPC: pass");
        }

        rez = computerTest.editPC();


        if (!rez.equals("")) {
            System.out.println("editPC: " + rez);
        } else {
            System.out.println("editPC: pass");
        }


        rez = computerTest.testDelete();


        if (!rez.equals("")) {
            System.out.println("testDelete: " + rez);
        } else {
            System.out.println("testDelete: pass");
        }

        rez = computerTest.testFormValidate();


        if (!rez.equals("")) {
            System.out.println("testFormValidate: " + rez);
        } else {
            System.out.println("testFormValidate: pass");
        }

        rez = computerTest.addPCFunction();
        if (!rez.equals("")) {
            System.out.println("testFormValidate: " + rez);
        } else {
            System.out.println("testFormValidate: pass");
        }

        rez = computerTest.check404();
        if (!rez.equals("")) {
            System.out.println("check404: " + rez);
        } else {
            System.out.println("check404: pass");
        }

        rez = computerTest.TestBackButton();
        if (!rez.equals("")) {
            System.out.println("TestBackButton: " + rez);
        } else {
            System.out.println("TestBackButton: pass");
        }

        rez = "";
        StarWarsTest starWarsTest = new StarWarsTest();
        try {
            rez = starWarsTest.FirstTest();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        if (!rez.equals("")) {
            System.out.println("StarWarsTest: " + rez);
        } else {
            System.out.println("StarWarsTest: pass");
        }

    }

    public static void readSettings() {
        try {
            System.out.println("const");
            File myObj = new File("E:\\filename.txt");
            Scanner myReader = new Scanner(myObj);
            int count = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println("data" + data);
                count++;
                if (count == 0) {
                    ChromePatch = data;
                } else if (count == 1) {
                    ChromeDriver = data;
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
    }
}
