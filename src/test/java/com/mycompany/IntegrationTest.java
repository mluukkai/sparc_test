
package com.mycompany;

import org.junit.Before;
import org.junit.Test;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest extends FluentTest {
    public WebDriver webDriver = new HtmlUnitDriver();

    @ClassRule
    public static ServerRule server = new ServerRule(4567);

    @Test
    public void mainPage() {
        goTo("http://localhost:4567/");
        assertThat(pageSource()).contains("Hackernews-uutiset");
        assertThat(pageSource()).contains("viimeisin");
        assertThat(pageSource()).contains("suosituin");
    }

    @Test
    public void mostPopularNews() {
        goTo("http://localhost:4567/suosituin");
        assertThat(pageSource()).contains("Suosituin uutinen");
        assertThat(pageSource()).contains("Alda: A music programming language by daveyarwood");
    }

    @Test
    public void mostRecentNews() {
        goTo("http://localhost:4567/viimeisin");
        assertThat(pageSource()).contains("Viimeisin uutinen");
        assertThat(pageSource()).contains("How Insurance Companies Profit from “Wearables” by cybernot");
    }     
    
    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }
}