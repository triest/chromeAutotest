import java.net.ProtocolException;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String rez="";

        FirstTest firstTest = new FirstTest();
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


        ComputerTest computerTest = new ComputerTest();
        rez = computerTest.CheckPlaceholderl();
        if (!rez.equals("")) {
            System.out.println("CheckPlaceholderl: " + rez);
        } else {
            System.out.println("CheckPlaceholderl: pass");
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

        rez="";
        StarWarsTest starWarsTest = new StarWarsTest();
        try {
            rez=starWarsTest.FirstTest();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        if (!rez.equals("")) {
            System.out.println("StarWarsTest: " + rez);
        } else {
            System.out.println("StarWarsTest: pass");
        }

    }
}
