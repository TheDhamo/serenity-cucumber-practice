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

        assertThat(find(By.jquery("#product_" + productId + " .cart_ref")).getText()).isEqualToIgnoringCase(("SKU : "+productReference));
        assertThat(find(By.jquery("#product_" + productId + " .cart_quantity_input")).getAttribute("value")).isEqualToIgnoringCase("1");
        assertThat(find(By.jquery("#product_" + productId + " .price")).getText()).isEqualToIgnoringCase(productPrice);
    }
}
