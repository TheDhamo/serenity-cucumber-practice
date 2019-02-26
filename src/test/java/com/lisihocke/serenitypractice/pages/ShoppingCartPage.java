package com.lisihocke.serenitypractice.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

import static org.assertj.core.api.Assertions.assertThat;

@DefaultUrl("/index.php?controller=order")
public class ShoppingCartPage extends PageObject {
    public void checkCartContainsProduct(String productId) {
        String productReference = Serenity.getCurrentSession().get("productReference").toString();
        String productPrice = Serenity.getCurrentSession().get("productPrice").toString();

        assertThat(find(By.jquery("#product_" + productId + " .cart_ref")).getText()).isEqualToIgnoringCase(("SKU : " + productReference));
        assertThat(find(By.jquery("#product_" + productId + " .cart_quantity_input")).getAttribute("value")).isEqualToIgnoringCase("1");
        assertThat(find(By.jquery("#product_" + productId + " .price")).getText()).isEqualToIgnoringCase(productPrice);
    }

    public void checkCartDoesNotContainProduct(String productId) {
        waitForRenderedElementsToDisappear(By.id("product_" + productId));
    }

    public void removeProduct(String productId) {
        find(By.jquery(".cart_delete #" + productId)).click();
    }

    public void productHadBeenAddedToCart(String productId) {
        String id_product = productId.substring(0, 1);
        addItemThroughURL(id_product);
    }

    private void addItemThroughURL(String id_product) {
        this.getDriver().get("http://automationpractice.com/index.php?controller=cart&add=1&id_product=" + id_product);
    }
}