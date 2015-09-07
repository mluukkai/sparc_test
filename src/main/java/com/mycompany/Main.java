package com.mycompany;

import com.mycompany.hackernewsuutiset.HackerPaivanUutiset;
import com.mycompany.paivanuutiset.PaivanUutiset;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.mustache.MustacheTemplateEngine;
import spark.template.velocity.VelocityTemplateEngine;

// lsof -i :4567
// mvn exec:java -Dexec.mainClass=com.mycompany.Main
public class Main {
    
    public static void main(String[] args) {
        
        port(getHerokuAssignedPort());


        get("/", (request, response) -> {
            return new ModelAndView(new HashMap<>(), "templates/index.html");
        }, new VelocityTemplateEngine());       
        
        get("viimeisin", (request, response) -> {
            NewsItem news = new NewsParser(hackerNews().haeViimeisinUutinen()).parse();
            
            return new ModelAndView(newsModel("Viimeisin", news), "templates/news.html");
        },  new VelocityTemplateEngine());
        
        get("suosituin", (request, response) -> {
            NewsItem news = new NewsParser(hackerNews().haeSuosituinUutinen()).parse();
            
            return new ModelAndView(newsModel("Suosituin", news), "templates/news.html");
        }, new VelocityTemplateEngine());
        
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
        if ( portFromEnv!=null ) {
            return Integer.parseInt(portFromEnv);
        }
        
        return 4567;
    }
    
    static String portFromEnv = new ProcessBuilder().environment().get("PORT");
    
    static void setEnvPort(String port){
        portFromEnv = port;
    }
}
