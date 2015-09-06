package com.mycompany;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class NewsParserTestTest {
    
    @Test 
    public void suosituin(){
        String news = "Suosituin uutinen on Alda: A music programming language by daveyarwood, url: http://daveyarwood.github.io/alda/2015/09/05/alda-a-manifesto-and-gentle-introduction/";
        NewsParser p = new NewsParser(news);
        String[] parts = p.parse();
        assertEquals("Alda: A music programming language by daveyarwood", parts[0]);
        assertEquals("http://daveyarwood.github.io/alda/2015/09/05/alda-a-manifesto-and-gentle-introduction/", parts[1]);
    }
    
    @Test 
    public void viimeisin(){ 
    String news = "Viimeisin uutinen on How Insurance Companies Profit from “Wearables” by cybernot, url: https://www.sherbit.io/the-insurance-industry-and-the-quantified-self/";
        NewsParser p = new NewsParser(news);
        String[] parts = p.parse();
        assertEquals("How Insurance Companies Profit from “Wearables” by cybernot", parts[0]);
        assertEquals("https://www.sherbit.io/the-insurance-industry-and-the-quantified-self/", parts[1]);
    }
    
}
