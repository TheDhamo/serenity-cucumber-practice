package com.lisihocke.serenitypractice.steps;

import com.lisihocke.serenitypractice.pages.ShoppingCartPage;
import cucumber.api.java.en.Then;

public class ShoppingCartSteps {

    private ShoppingCartPage shoppingCartPage;

    @Then("^The cart contains the product (.*)$")
    public void the_cart_contains_the_product(String productId) {
        shoppingCartPage.open();
        shoppingCartPage.checkCartContainsProduct(productId);
    }
}
