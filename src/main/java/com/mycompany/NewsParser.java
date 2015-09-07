package com.mycompany;

public class NewsParser {
    private String news;

    public NewsParser(String news) {
        this.news = news;
    }
    
    public NewsItem parse(){  
        String[] splitted = news.split(", url: ");
        String body = splitted[0].replaceAll("Suosituin uutinen on ", "").replaceAll("Viimeisin uutinen on ", "");
        
        return new NewsItem(body, splitted[1]);
    }
}
