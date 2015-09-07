package com.mycompany;

public class NewsItem {
    public String body;
    public String url;

    public NewsItem(String body, String url) {
        this.body = body;
        this.url = url;
    }

    public String body() {
        return body;
    }

    public String url() {
        return url;
    }
    
}
