package com.lisihocke.serenitypractice.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.lisihocke.serenitypractice.pages.ProductPage;
import com.lisihocke.serenitypractice.pages.ShoppingCartPage;

import static net.serenitybdd.core.pages.PageObject.withParameters;

public class StepDefinitions {

    private ProductPage productPage;
    private ShoppingCartPage shoppingCartPage;

    @Given("^I am on a product page$")
    public void i_am_on_a_product_page() {
        productPage.open("productPage", withParameters("1"));
    }

    @When("^I add the item to the cart$")
    public void i_add_the_item_to_the_cart() {
        productPage.addItemToCart();
    }

    @Then("^The cart contains the added item$")
    public void the_cart_contains_the_added_item() {
        shoppingCartPage.open();
        shoppingCartPage.checkCartForItem("product_1_1_0_0");
    }
}
