package com.lisihocke.serenitypractice.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;
import net.serenitybdd.core.annotations.findby.By;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@NamedUrls({@NamedUrl(name = "searchResultPage", url = "/index.php?controller=search&search_query={1}")})
public class SearchPage extends PageObject {

    public void searchForProduct(String searchTerm) {
        WebElementFacade searchField = find(By.id("search_query_top"));
        searchField.typeAndEnter(searchTerm);
    }

    public void checkSearchResults(String searchTerm) {
        List<WebElementFacade> productNames = findAll(".product_list .product-name");

        productNames.forEach((product) -> {
            assertThat(product.getText().toLowerCase()).contains(searchTerm.toLowerCase());
        });
    }

    public void viewProductDetailsPage() {
        WebElementFacade product = find(By.jquery(".product-container a.product-name"));
        Serenity.setSessionVariable("productName").to(product.getText());
        product.click();
    }

    public void viewProductPreview() {
        WebElementFacade product = find(By.jquery(".product-container a.product-name"));
        Serenity.setSessionVariable("productName").to(product.getText());

        WebElementFacade productImage = find(By.className("product_img_link"));
        Actions action = new Actions(this.getDriver());
        action.moveToElement(productImage).build().perform();

        WebElementFacade quickView = find(By.jquery("a.quick-view"));
        quickView.click();

        waitFor((WebElementFacade) find(By.jquery("div.fancybox-opened")));
    }
}
