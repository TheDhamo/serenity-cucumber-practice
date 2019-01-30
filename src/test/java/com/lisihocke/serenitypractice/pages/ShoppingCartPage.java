package com.lisihocke.serenitypractice.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/index.php?controller=order")
public class ShoppingCartPage extends PageObject {
    public void checkCartContainsProduct(String productId) {
        String productReference = Serenity.getCurrentSession().get("productReference").toString();
        String productPrice = Serenity.getCurrentSession().get("productPrice").toString();

        assert find(By.jquery("#product_" + productId + " .cart_ref")).getText().equalsIgnoreCase("SKU : "+productReference);
        assert find(By.jquery("#product_" + productId + " .cart_quantity_input")).getAttribute("value").equalsIgnoreCase("1");
        assert find(By.jquery("#product_" + productId + " .price")).getText().equalsIgnoreCase(productPrice);
    }
}
