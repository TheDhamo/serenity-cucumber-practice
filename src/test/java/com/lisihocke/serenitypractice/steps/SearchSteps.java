package com.lisihocke.serenitypractice.steps;

import com.lisihocke.serenitypractice.pages.SearchPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;

public class SearchSteps {

    private SearchPage searchPage;

    @Given("^I am on the homepage$")
    public void i_am_on_the_home_page() {
        searchPage.open();
    }

    @When("^I search for '(.*)'$")
    public void i_search_for_product(String searchTerm) {
        searchPage.searchForProduct(searchTerm);
        Serenity.setSessionVariable("searchTerm").to(searchTerm);
    }

    @Then("^The search returns related products$")
    public void the_search_returns_related_products() {
        String searchTerm = Serenity.getCurrentSession().get("searchTerm").toString();
        searchPage.checkSearchResults(searchTerm);
    }
}
