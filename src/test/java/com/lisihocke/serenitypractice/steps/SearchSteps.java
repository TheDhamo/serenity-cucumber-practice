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
    public void iHaveSearchedForAProduct() {
        searchPage.open("searchResultPage", withParameters("print"));
    }

    @When("^I search for '(.*)'$")
    public void iSearchForProduct(String searchTerm) {
        searchPage.searchForProduct(searchTerm);
        Serenity.setSessionVariable("searchTerm").to(searchTerm);
    }

    @When("^I view the page of the first product$")
    public void iViewThePageOfTheFirstProduct() {
        searchPage.viewProductDetailsPage();
    }

    @When("^I view the product preview$")
    public void iViewTheProductPreview() {
        searchPage.viewProductPreview();
    }

    @Then("^The search returns related products$")
    public void theSearchReturnsRelatedProducts() {
        String searchTerm = Serenity.getCurrentSession().get("searchTerm").toString();
        searchPage.checkSearchResults(searchTerm);
    }

    @When("^I view a product category$")
    public void iViewAProductCategory() {
        String category = "Summer Dresses";
        searchPage.viewCategory(category);
        Serenity.setSessionVariable("category").to(category);
    }

    @Then("^I see the products of this category$")
    public void iSeeTheProductsOfThisCategory() {
        String category = Serenity.getCurrentSession().get("category").toString();
        searchPage.checkCategory(category);
    }
}
