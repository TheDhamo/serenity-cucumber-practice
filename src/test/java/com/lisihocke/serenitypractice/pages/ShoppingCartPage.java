package com.lisihocke.serenitypractice.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

@DefaultUrl("/index.php?controller=order")
public class ShoppingCartPage extends PageObject {
    public void checkCartContainsProduct() {
        String productId = Serenity.getCurrentSession().get("productId").toString();
        String productReference = Serenity.getCurrentSession().get("productReference").toString();
        String productPrice = Serenity.getCurrentSession().get("productPrice").toString();

        assertThat(find(By.jquery("#" + productId + " .cart_ref")).getText()).isEqualToIgnoringCase(("SKU : " + productReference));
        assertThat(find(By.jquery("#" + productId + " .cart_quantity_input")).getAttribute("value")).isEqualToIgnoringCase("1");
        assertThat(find(By.jquery("#" + productId + " .price")).getText()).isEqualToIgnoringCase(productPrice);
    }

    public void checkCartDoesNotContainProduct(String productId) {
        waitForRenderedElementsToDisappear(By.id("product_" + productId));
    }

    public void removeProduct(String productId) {
        find(By.jquery(".cart_delete #" + productId)).click();
    }

    public void productHadBeenAddedToCart(String productId) {
        String id_product = productId.substring(0, 1);
        this.getDriver().get("http://automationpractice.com/index.php?controller=cart&add=1&id_product=" + id_product);
    }

    public void updateQuantity(int quantity) {
        String productQuantity = String.valueOf(quantity);
        find(By.className("cart_quantity_input")).typeAndEnter(productQuantity);

        WebDriverWait wait = new WebDriverWait(this.getDriver(), 3);
        wait.until(ExpectedConditions.attributeToBe(By.jquery(".cart_quantity input[type='hidden']"), "value", productQuantity));
    }

    public void checkProductTotalPriceIsUpdatedToQuantity(int quantity) {
        double productUnitPrice = Double.parseDouble(find(By.jquery(".cart_unit span span")).getText().substring(1));
        double productTotalPrice = Double.parseDouble(find(By.jquery(".cart_total .price")).getText().substring(1));
        assertThat(productUnitPrice * quantity).isEqualTo(productTotalPrice);
    }
}