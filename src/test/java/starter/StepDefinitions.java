package starter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ProductPage;

import static net.serenitybdd.core.pages.PageObject.withParameters;

public class StepDefinitions {

    ProductPage productPage;

    @Given("^I am on a product page$")
    public void i_am_on_a_product_page() throws Throwable {
        productPage.open("productPage", withParameters("1"));
    }

    @When("^I add the item to the cart$")
    public void i_add_the_item_to_the_cart() throws Throwable {
    }

    @Then("^The cart contains the added item$")
    public void the_cart_contains_the_added_item() throws Throwable {
    }

}
