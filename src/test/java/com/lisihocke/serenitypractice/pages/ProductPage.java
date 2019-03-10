package com.lisihocke.serenitypractice.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;

import static org.assertj.core.api.Assertions.assertThat;

@NamedUrls({@NamedUrl(name = "productPage", url = "/index.php?id_product={1}&controller=product")})
public class ProductPage extends PageObject {

    public void storeProductReference() {
        String productReference = find(By.jquery("#product_reference span")).getText();
        Serenity.setSessionVariable("productReference").to(productReference);
    }

    public void addItemToCart() {
        String productPrice = find(By.id("our_price_display")).getText();
        Serenity.setSessionVariable("productPrice").to(productPrice);
        find(By.id("add_to_cart")).click();
    }

    public void checkProductPage() {
        String productName = Serenity.getCurrentSession().get("productName").toString();
        assertThat(find(By.jquery("#product h1[itemprop='name']")).getText()).isEqualToIgnoringCase(productName);
    }

    public void checkProductPreview() {
        String productName = Serenity.getCurrentSession().get("productName").toString();
        this.getDriver().switchTo().frame(0);
        assertThat(find(By.jquery("#product h1[itemprop='name']")).getText()).isEqualToIgnoringCase(productName);
    }

    public void sendToAFriend() {
        find(By.id("send_friend_button")).click();
        find(By.id("friend_name")).type("test");
        find(By.id("friend_email")).type("test@test.com");
        find(By.id("sendEmail")).click();
    }

    public void checkForSuccessMessage() {
        assertThat(find(By.jquery(".fancybox-inner > p")).getText()).isEqualToIgnoringCase("Your e-mail has been sent successfully");
    }
}
