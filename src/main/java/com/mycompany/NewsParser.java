package com.mycompany;

public class NewsParser {
    private String newsString;

    public NewsParser(String news) {
        this.newsString = news;
    }
    
    public NewsItem parse(){  
        String[] splitted = newsString.split(", url: ");
        String body = splitted[0].replaceAll("Suosituin uutinen on ", "").replaceAll("Viimeisin uutinen on ", "");
        
        return new NewsItem(body, splitted[1]);
    }
}
