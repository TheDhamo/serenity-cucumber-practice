package com.lisihocke.serenitypractice.steps;

import com.lisihocke.serenitypractice.pages.SearchPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;

import static net.serenitybdd.core.pages.PageObject.withParameters;

public class SearchSteps {

    private SearchPage searchPage;

    @Given("^I have searched for a product$")
    public void i_have_searched_for_a_product() {
        searchPage.open("searchResultPage", withParameters("print"));
    }

    @When("^I search for '(.*)'$")
    public void i_search_for_product(String searchTerm) {
        searchPage.searchForProduct(searchTerm);
        Serenity.setSessionVariable("searchTerm").to(searchTerm);
    }

    @When("^I view the page of the first product$")
    public void i_view_the_page_of_the_first_product() {
        searchPage.viewProductDetailsPage();
    }

    @When("^I view the product preview$")
    public void i_view_the_product_preview() {
        searchPage.viewProductPreview();
    }

    @Then("^The search returns related products$")
    public void the_search_returns_related_products() {
        String searchTerm = Serenity.getCurrentSession().get("searchTerm").toString();
        searchPage.checkSearchResults(searchTerm);
    }
}
