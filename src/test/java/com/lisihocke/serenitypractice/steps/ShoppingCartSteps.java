package com.lisihocke.serenitypractice.steps;

import com.lisihocke.serenitypractice.pages.ShoppingCartPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShoppingCartSteps {

    private ShoppingCartPage shoppingCartPage;

    @Given("^I have product (.*) in the cart$")
    public void i_have_the_product_in_the_cart(String productId) {
        shoppingCartPage.open();
        shoppingCartPage.productHadBeenAddedToCart(productId);
    }

    @When("^I remove product (.*) from the cart$")
    public void i_remove_the_product_from_the_cart(String productId) {
        shoppingCartPage.removeProduct(productId);
    }

    @Then("^The cart contains the product (.*)$")
    public void the_cart_contains_the_product(String productId) {
        shoppingCartPage.open();
        shoppingCartPage.checkCartContainsProduct(productId);
    }

    @Then("^The cart does not contain the product (.*)$")
    public void the_cart_does_not_contain_the_product(String productId) {
        shoppingCartPage.checkCartDoesNotContainProduct(productId);
    }
}
