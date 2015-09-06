package com.mycompany;

public class NewsParser {
    private String news;

    public NewsParser(String news) {
        this.news = news;
    }
    
    public String[] parse(){
        String[] parts = new String[2];
        
        String[] splitted = news.split(", url: ");
        parts[0] = splitted[0].replaceAll("Suosituin uutinen on ", "").replaceAll("Viimeisin uutinen on ", "");
        parts[1] = splitted[1];
        
        return parts;
    }
}
