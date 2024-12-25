import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridTest {

    private WebDriver driver;

    @DataProvider(name = "browsers")
    public Object[][] getBrowsers() {
        return new Object[][]{
                {"chrome"},
                {"firefox"},
                {"MicrosoftEdge"}
        };
    }

    @Test(dataProvider = "browsers")
    public void testGoogleTitle(String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);

        // Replace localhost with your Docker host IP if necessary
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);

        // Navigate to Google
        driver.get("https://www.google.com");

        // Verify the title of the page
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        System.out.println("Title on " + browser + ": " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Title did not match on browser: " + browser);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
