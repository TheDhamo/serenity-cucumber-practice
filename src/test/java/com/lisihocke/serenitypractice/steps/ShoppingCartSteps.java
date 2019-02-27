package com.lisihocke.serenitypractice.steps;

import com.lisihocke.serenitypractice.pages.ShoppingCartPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;

public class ShoppingCartSteps {

    private ShoppingCartPage shoppingCartPage;

    @Given("^I have a product in the cart$")
    public void i_have_a_product_in_the_cart() {
        String productId = "1_1_0_0";
        shoppingCartPage.open();
        shoppingCartPage.productHadBeenAddedToCart(productId);
        Serenity.setSessionVariable("productId").to(productId);
    }

    @When("^I remove the product from the cart$")
    public void i_remove_the_product_from_the_cart() {
        String productId = Serenity.getCurrentSession().get("productId").toString();
        shoppingCartPage.removeProduct(productId);
    }

    @Then("^The cart contains the product$")
    public void the_cart_contains_the_product() {
        shoppingCartPage.open();
        shoppingCartPage.checkCartContainsProduct();
    }

    @Then("^The cart does not contain the product$")
    public void the_cart_does_not_contain_the_product() {
        String productId = Serenity.getCurrentSession().get("productId").toString();
        shoppingCartPage.checkCartDoesNotContainProduct(productId);
    }
}
