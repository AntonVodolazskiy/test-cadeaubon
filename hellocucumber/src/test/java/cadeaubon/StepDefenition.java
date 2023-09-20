package cadeaubon;

import PageObject.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefenition {
    private HomePage homePage;
    @Given("I am on the cadeaubon.nl website")
    public void isCadeaubonWebsiteOpen() {
        homePage = new HomePage(CadeaubonBaseTests.driver);
        CadeaubonBaseTests.driver.get(CadeaubonBaseTests.baseUrl);
        homePage.AcceptCookies();
    }
    @When("I start typing {string} in the search bar")
    public void iStartTypingInTheSearchBar(String string) {
        homePage.enterSearchText("diner ");
    }
    @Then("I should see product suggestions in the result field")
    public void iShouldSeeProductSuggestionsInTheResultField() {
        homePage.elementPresented();
    }
    @And("I select the first product from the search results")
    public void iSelectTheFirstProductFromTheSearchResults() {
        homePage.chooseProduct();
    }
    @Then("I add the selected product to the cart")
    public void iAddTheSelectedProductToTheCart() {
        homePage.putToCart();
        homePage.fillInfo("test@email.com");
    }

    @And("I proceed to the checkout")
    public void iProceedToTheCheckout() {
        homePage.checkUrlCart("https://www.cadeaubon.nl/winkelmandje/");
        homePage.fillCustomerInfo();
    }
    @Then("I enter my credit card data")
    public void iEnterMyCreditCardData() {
        homePage.proceedToCheckout();
        homePage.choosePaymentType();
    }
}
