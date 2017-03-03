package com.mytest;


import com.codeborne.selenide.*;
import com.pages.GooglePage;
import com.pages.SearchResultsPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.open;


public class Test {

    @BeforeSuite
    public void setUp() {
        ChromeDriverManager.getInstance().version("2.25").setup();
        Configuration.browser = "chrome";
    }

    @org.testng.annotations.Test
    public void test(){
        GooglePage page = open("http://google.com.ua", GooglePage.class);
        SearchResultsPage results = page.searchFor("Agile fuel");

        ElementsCollection res = results.getResults();

        boolean isEquals = false;

        for(int i = 0; i < 5; i++){
            String link = res.get(i).$("._Rm").getText();
            if(link.equals("www.agilefuel.com/")){
                isEquals = true;
            }
        }

        Assert.assertTrue(isEquals, "Agile fuel website does not present in first 5 records");
    }

    @AfterSuite
    public void tearDown(){
        WebDriverRunner.closeWebDriver();
    }

}
