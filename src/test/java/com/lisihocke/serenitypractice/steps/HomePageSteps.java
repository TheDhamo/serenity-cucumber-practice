package com.lisihocke.serenitypractice.steps;

import com.lisihocke.serenitypractice.pages.HomePage;
import cucumber.api.java.en.Given;

public class HomePageSteps {

    private HomePage homePage;

    @Given("^I am on the homepage$")
    public void iAmOnTheHomePage() {
        homePage.open();
    }
}
