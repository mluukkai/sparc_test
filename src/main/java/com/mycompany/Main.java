package com.mycompany;

import com.mycompany.hackernewsuutiset.HackerPaivanUutiset;
import com.mycompany.paivanuutiset.PaivanUutiset;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.mustache.MustacheTemplateEngine;

// lsof -i :4567
// mvn exec:java -Dexec.mainClass=com.mycompany.Main
public class Main {
    
    public static void main(String[] args) {
        
        port(getHerokuAssignedPort());

        get("/", (request, response) -> {
            return new ModelAndView(new HashMap<>(), "index.html");
        }, new MustacheTemplateEngine());       
        
        get("viimeisin", (request, response) -> {
            NewsItem news = new NewsParser(hackerNews().haeViimeisinUutinen()).parse();
            
            return new ModelAndView(newsModel("Viimeisin", news), "news.html");
        }, new MustacheTemplateEngine());
        
        get("suosituin", (request, response) -> {
            NewsItem news = new NewsParser(hackerNews().haeSuosituinUutinen()).parse();
            
            return new ModelAndView(newsModel("Suosituin", news), "news.html");
        }, new MustacheTemplateEngine());
        
    }
       
    static Map<String, Object> newsModel(String type, NewsItem news) {
        Map<String, Object> model = new HashMap<>();
        model.put("news", news);
        model.put("type", type);
        return model;
    }
    
    static PaivanUutiset uutisHakija = new HackerPaivanUutiset();
    
    static void setUutishakija(PaivanUutiset hakija){
        uutisHakija = hakija;
    }
    
    static PaivanUutiset hackerNews(){
        return uutisHakija;
    }
    
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
