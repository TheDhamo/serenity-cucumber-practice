package com.lisihocke.serenitypractice.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.lisihocke.serenitypractice.pages.ProductPage;
import com.lisihocke.serenitypractice.pages.ShoppingCartPage;
import net.serenitybdd.core.Serenity;

import static net.serenitybdd.core.pages.PageObject.withParameters;

public class ProductSteps {

    private ProductPage productPage;

    @Given("^I am on the page for product (.*)$")
    public void i_am_on_the_page_for_product(String productId) {
        productPage.open("productPage", withParameters(productId));
        productPage.storeProductReference();
        Serenity.setSessionVariable("productId").to("product_"+productId);
    }

    @When("^I add the product to the cart$")
    public void i_add_the_product_to_the_cart() {
        productPage.addItemToCart();
    }
}
