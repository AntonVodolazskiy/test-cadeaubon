package PageObject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//input[@placeholder='Waar ben je naar op zoek?']")
    private WebElement searchBar;
    @FindBy(xpath = "//div[contains(@class, 'SearchResults_productCardContainer__wzKDa')][1]")
    private WebElement searched_product;
    /*@FindBy(xpath = "//h1[contains(@class, 'Typography_root__V_oih') and contains(text(), 'Diner Cadeau')]")
    private WebElement choose_product;*/

    @FindBy(className = "SearchResults_resultsContainer__hVffj")
    private WebElement parent_container;
    @FindBy(className = "SearchResults_productCardContainer__wzKDa")
    private WebElement child_container;
    @FindBy(className = "Button_content__Uy3dq")
    private WebElement add_to_cart_btn_initial;
    @FindBy(xpath = "//div[text()='In m’n winkelmandje']")
    private WebElement add_to_cart_btn;
    @FindBy(xpath = "//label[@for='digital']/p[1]")
    private WebElement choose_digital_format;
    @FindBy(xpath = "//input[@name='name']")
    private WebElement name_field;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement email_field;
    @FindBy(xpath = "//input[@name='confirmEmail']")
    private WebElement confirm_email_field;
    @FindBy(xpath ="//div[text()='Veilig afrekenen']" )
    private WebElement proceed_to_checkout_btn;
    @FindBy(xpath ="//div[text()='Bestellen als gast']" )
    private WebElement proceed_as_guest_btn;
    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement first_name_field;
    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement last_name_field;
    @FindBy(xpath = "//input[@name='phoneNumber']")
    private WebElement phone_number_field;
    @FindBy(xpath ="//div[text()='Ga verder']")
    private WebElement proceed_to_payment_btn;
    @FindBy(xpath = "//div[contains(@class, '_263b114dce641053e4a91c7b9b02b805-css')][1]")
    private WebElement choose_payment_type_btn;
    @FindBy(xpath = "//div[contains(@class, '_5060cb64e4f4b00bbe042a7dcd236b6f-css')][4]")
    private WebElement choose_bank;
    @FindBy(xpath = "//div[text()='Betaling afrekenen']")
    private WebElement to_pay_btn;





    public void AcceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement accept_cookies = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("CookieConsentIOAccept")));
        accept_cookies.click();
    }
    public void enterSearchText(String searchText) {
        searchBar.sendKeys(searchText);
    }
    public void elementPresented() {
        List<WebElement> childContainers = parent_container
                .findElements(By.className("SearchResults_productCardContainer__wzKDa"));
        int numberOfChildContainers = childContainers.size();
        if (numberOfChildContainers > 0) {
            System.out.println("Autocomplete is working");
        } else {
            System.out.println("Products are not presented");
        }
    }
    public void chooseProduct() {
        searched_product.click();
    }
    public void putToCart() {
        choose_digital_format.click();
        add_to_cart_btn_initial.click();
    }
    public void fillInfo(String string) {
        name_field.sendKeys(string);
        email_field.sendKeys(string);
        confirm_email_field.sendKeys(string);
        add_to_cart_btn.click();
    }
    public void checkUrlCart(String expected) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlMatches(expected));
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expected, actualUrl);
    }

    public void proceedToCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(proceed_to_checkout_btn));
        proceed_to_checkout_btn.click();
        wait.until(ExpectedConditions.visibilityOf(proceed_as_guest_btn));
        proceed_as_guest_btn.click();
    }
    public void fillCustomerInfo(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(first_name_field));
        first_name_field.sendKeys("test");
        last_name_field.sendKeys("test");
        email_field.sendKeys("test@email.com");
        phone_number_field.sendKeys("1234567891");
        wait.until(ExpectedConditions.elementToBeClickable(proceed_to_payment_btn));
        proceed_to_payment_btn.click();
    }
    public void choosePaymentType(){
        choose_payment_type_btn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(choose_bank));
        choose_bank.click();
        to_pay_btn.click();
    }
}
