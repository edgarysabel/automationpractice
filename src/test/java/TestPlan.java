import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Add Item to shopping cart")
    public static void addItem(){
        driver.get(Utils.BASE_URL);
        WebForm webForm = new WebForm(driver);

        webForm.articleLink();
        webForm.addToCart();
        webForm.proceedBTN();
        webForm.verifyItemAdded();
    }

    @Test(testName = "Delete Item")
    public static void deleteItem(){
        driver.get(Utils.BASE_URL);
        WebForm webForm = new WebForm(driver);

        //Pre-request
        addItem();

        webForm.trashBTN();
        webForm.verifyItemDeleted(30, driver);
    }

    @Test(testName = "Search Item Successfully")
    public static void searchItem(){
        driver.get(Utils.BASE_URL);
        WebForm webForm = new WebForm(driver);

        webForm.searchItem("Faded Short Sleeve T-shirts");
    }

    @Test(testName = "Search Item unsuccessfully")
    public static void searchWrongItem(){
        driver.get(Utils.BASE_URL);
        WebForm webForm = new WebForm(driver);

        webForm.searchWrongItem("last name");
    }

    @Test(testName = "Verify Store Info")
    public static void verifyFooter(){
        driver.get(Utils.BASE_URL);
        WebForm webForm = new WebForm(driver);

        //Scrolling Down
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 2500);");


        webForm.verifyFooter();
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}