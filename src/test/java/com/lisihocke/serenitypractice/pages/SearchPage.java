package com.lisihocke.serenitypractice.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class SearchPage extends PageObject {

    public void searchForProduct(String searchTerm) {
        WebElementFacade searchField = find(By.id("search_query_top"));
        searchField.typeAndEnter(searchTerm);
    }

    public void checkSearchResults(String searchTerm) {
        List<WebElementFacade> productNames = findAll(".product_list .product-name");

        productNames.forEach((product) -> {
            assert product.getText().toLowerCase().contains(searchTerm.toLowerCase());
        });
    }
}
