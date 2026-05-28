package com.saucedemo.tests;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseTest {

    @Test(description = "Verify product search functionality")
    public void testProductSearch() {
        HomePage homePage = new HomePage(getDriver());
        
        // Verify logo is displayed on homepage
        Assert.assertTrue(homePage.isLogoDisplayed(), "Sauce Demo logo is not displayed on Homepage.");
        
        // Search for 'jacket'
        SearchPage searchPage = homePage.searchProduct("jacket");
        
        // Verify search results header is displayed
        Assert.assertTrue(searchPage.isSearchHeaderDisplayed(), "Search results header is not displayed.");
        
        // Verify result items are found and contain expected titles
        List<String> results = searchPage.getSearchResultTitles();
        Assert.assertTrue(results.size() > 0, "No search results found for 'jacket'.");
        
        System.out.println("Search results found:");
        for (String title : results) {
            System.out.println("- " + title);
            Assert.assertTrue(title.toLowerCase().contains("jacket"), 
                    "Search result '" + title + "' does not contain searched keyword 'jacket'.");
        }
    }
}
