package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TestSteps {

    static WebDriver foxBrowser;


    @Given("^User is on Home Page$")
    public void user_is_on_Home_Page() throws Exception {
        //Sukuria Firefox naršyklės objektą
        FirefoxOptions opts = new FirefoxOptions();
        //į sukurtą objektą įdeda reikšmę, kurios dėka naršyklė bus atidaryta inPrivate lange
        opts.addArguments("-private");
        /*naršyklės draiveriai randami automatiškai - nereikia jų pačiam parsisiųsti ir nurodyti kelio.
         * Tai galima dėl to, nes pom.xml faile įdėtas webdrivermanager dependency*/
        WebDriverManager.firefoxdriver().setup();
        foxBrowser = new FirefoxDriver(opts); //kaip argumentą įdedam objektą, kuris nustatytas naršyklę atidaryti inPrivate lange
        foxBrowser.get("http://www.store.demoqa.com");
    }

    @When("^User Navigate to LogIn Page$")
    public void user_Navigate_to_LogIn_Page() throws Exception {
        foxBrowser.findElement(By.xpath("/html/body/p/a")).click();
        foxBrowser.findElement(By.xpath("//*[@id=\"noo-site\"]/header/div[1]/div/ul[2]/li[2]/a")).click();
    }

    @When("^User enters \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enters_UserName_and_Password(String username, String password) throws Exception {
        foxBrowser.findElement(By.id("username")).sendKeys(username);
        foxBrowser.findElement(By.id("password")).sendKeys(password);
        foxBrowser.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/form/p[3]/button")).click();
    }

    @Then("^Error \"([^\"]*)\" is shown$")
    public void error_is_shown(String message) throws Exception {
        String actualError = foxBrowser.findElement(By.xpath("//*[@id=\"post-8\"]/div/div/div[1]/ul/li/strong")).getText();
        Assert.assertEquals(message, actualError);

    }




}
