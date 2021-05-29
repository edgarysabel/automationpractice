// Page URL:  http://automationpractice.com/index.php
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class WebForm extends PageObject{

    private final String itemName = "Faded Short Sleeve T-shirts";
    private final String storeNumberInfo = "(347) 466-7432";
    private final String storeAddressInfo = "Selenium Framework, Research Triangle Park, North Carolina, USA";
    private final String storeEmailInfo = "support@seleniumframework.com";
    private final SoftAssert softAssert = new SoftAssert();
    private final WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"block_contact_infos\"]//ul/li[2]/span")
    private WebElement storeNumber;

    @FindBy(xpath = "//*[@id=\"block_contact_infos\"]//ul[@class=\"toggle-footer\"]/li[1]")
    private WebElement storeAddress;

    @FindBy(xpath = "//*[@id=\"block_contact_infos\"]//ul/li[3]/span/a")
    private WebElement storeEmail;

    @FindBy(xpath = "//a[contains(text(),'Submit')]")
    private WebElement submit_button;

    @FindBy(id = "add_to_cart")
    private WebElement addToCartBTN;

    @FindBy(xpath = "//*[@title=\"View my shopping cart\"]")
    private WebElement shoppingCartBTN;

    @FindBy(xpath = "//*[@title=\"Proceed to checkout\"]")
    private WebElement proceedBTN;

    @FindBy(xpath = "//*[@class=\"icon-trash\"]")
    private WebElement trashBTN;

    @FindBy(xpath = "//h5/a[@title=\"Faded Short Sleeve T-shirts\"]")
    private WebElement articleLink;

    @FindBy(xpath = "//*[@class=\"cart_description\"]/p[@class=\"product-name\"]/a")
    private WebElement cartItemName;

    @FindBy(id = "search_query_top")
    private WebElement searchBar;

    @FindBy(xpath = "//*[@name=\"submit_search\"]")
    private WebElement searchBTN;
    //*[@title="Faded Short Sleeve T-shirts"]

    @FindBy(xpath = "//*[@title=\"Faded Short Sleeve T-shirts\"]")
    private WebElement itemSearched;

    //*[@id="center_column"]/p[@class="alert alert-warning"]
    @FindBy(xpath = "//*[@id=\"center_column\"]/p[@class=\"alert alert-warning\"]")
    private WebElement notFoundMessage;

    public WebForm(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 60);
    }

    public void searchItem(String item){
        this.searchBar.sendKeys(item);
        this.searchBTN.click();

        Assert.assertEquals(true, itemSearched.isDisplayed());

    }
    public void searchWrongItem(String item){
        this.searchBar.sendKeys(item);
        this.searchBTN.click();

        Assert.assertEquals(true, notFoundMessage.isDisplayed());

    }

    public void pressSubmitButton(){
        wait.until(ExpectedConditions.elementToBeClickable(proceedBTN));
        this.submit_button.click();
    }

    public void addToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBTN));
        Assert.assertEquals(true, addToCartBTN.isDisplayed());
        this.addToCartBTN.click();
    }

    //articleLink
    public void articleLink(){
        wait.until(ExpectedConditions.elementToBeClickable(articleLink));
        Assert.assertEquals(true, articleLink.isDisplayed());
        this.articleLink.click();
    }

    public void proceedBTN(){
        wait.until(ExpectedConditions.elementToBeClickable(proceedBTN));
        Assert.assertEquals(true, proceedBTN.isDisplayed());
        this.proceedBTN.click();
    }


    public void trashBTN(){
        wait.until(ExpectedConditions.elementToBeClickable(trashBTN));
        Assert.assertEquals(true, trashBTN.isDisplayed());
        this.trashBTN.click();
    }

    public void verifyItemAdded(){
        String elementText = this.cartItemName.getText();

        //verify correct item was added
        Assert.assertEquals(itemName, elementText);
    }

    public void verifyItemDeleted(Integer _seconds, WebDriver driver) {
        for (int second = 0; ; second++) {
            if (second >= _seconds)
                System.out.println("Waiting for changes to be saved...");
            try {
                if (!("block".equals(cartItemName.getCssValue("display"))))
                    System.out.println("Deleted");
                break;
            } catch (Exception e) {

            }
        }
    }

    public void verifyFooter(){
        String emailText = this.storeEmail.getText();
        String addressText = this.storeAddress.getText();
        String numberText = this.storeNumber.getText();

        System.out.println(emailText);
        System.out.println(addressText);
        System.out.println(numberText);

        //verify info
        softAssert.assertEquals(numberText, storeNumberInfo);
        softAssert.assertEquals(addressText, storeAddressInfo);
        softAssert.assertEquals(emailText, storeEmailInfo);
    }
}