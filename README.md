# chromeAutotest

Launch:

You can use JavaIDE (IntelliJ IDEA). Open the IDE and right-click on src / test / java and select
"RunAllTests".
Or use Apache Maven.
From the command line run "mvn test"

Task 1: FirstTest
Task 1 B: StarWarsTest <br>
Task 2: ComputerTest

Also, you need to specify the path to ChromeDriver and chrome in each class in ChromePatch and ChromeDriver.
ChromeDriver: https://chromedriver.chromium.org/downloads

Update: <br>
The path you must set in the variables: ChromePatch and ChromeDriver

Example:

    String ChromePatch = "C: \\ Program Files (x86) \\ Google \\ Chrome Beta \\ Application \\ chrome.exe";
    String ChromeDriver = "E: \\ chromedriver_win32 \\ chromedriver.exe";

Unfortunately, I could not build the executable file.



