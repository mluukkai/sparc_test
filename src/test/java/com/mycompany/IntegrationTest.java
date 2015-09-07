
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
    public void helloTest() {
        goTo("http://localhost:4567/");
        assertThat(pageSource()).contains("Hackernews-uutiset");
        assertThat(pageSource()).contains("viimeisin");
        assertThat(pageSource()).contains("suosituin");
    }

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }
}