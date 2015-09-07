package com.mycompany;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class NewsParserTestTest {
    
    @Test 
    public void suosituin(){
        String news = "Suosituin uutinen on Alda: A music programming language by daveyarwood, url: http://daveyarwood.github.io/alda/2015/09/05/alda-a-manifesto-and-gentle-introduction/";
        NewsParser p = new NewsParser(news);
        NewsItem newsItem = p.parse();
        assertEquals("Alda: A music programming language by daveyarwood", newsItem.body);
        assertEquals("http://daveyarwood.github.io/alda/2015/09/05/alda-a-manifesto-and-gentle-introduction/", newsItem.url);
    }
    
    @Test 
    public void viimeisin(){ 
        String news = "Viimeisin uutinen on How Insurance Companies Profit from “Wearables” by cybernot, url: https://www.sherbit.io/the-insurance-industry-and-the-quantified-self/";
        NewsParser p = new NewsParser(news);
        NewsItem newsItem = p.parse();
        assertEquals("How Insurance Companies Profit from “Wearables” by cybernot", newsItem.body);
        assertEquals("https://www.sherbit.io/the-insurance-industry-and-the-quantified-self/", newsItem.url);
    }
    
}
