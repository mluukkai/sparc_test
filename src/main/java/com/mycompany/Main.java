package com.mycompany;

import com.mycompany.hackernewsuutiset.HackerPaivanUutiset;
import com.mycompany.paivanuutiset.PaivanUutiset;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.velocity.VelocityTemplateEngine;

// lsof -i :4567
// mvn exec:java -Dexec.mainClass=com.mycompany.Main
public class Main {
    
    static String LAYOUT = "templates/layout.html";
    
    public static void main(String[] args) {
        
        port(getHerokuAssignedPort());

        get("/", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/index.html");
            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());             
        
        get("viimeisin", (request, response) -> {
            NewsItem lastNews = new NewsParser(hackerNews().haeViimeisinUutinen()).parse();
            
            return render("Viimeisin", lastNews);
        },  new VelocityTemplateEngine());
        
        get("suosituin", (request, response) -> {
            NewsItem mostPopularNews = new NewsParser(hackerNews().haeSuosituinUutinen()).parse();
            
            return render("Suosituin", mostPopularNews);
        }, new VelocityTemplateEngine());
        
    }
       
    static ModelAndView render(String type, NewsItem news){
        return new ModelAndView(newsModel(type, news), LAYOUT);
    }
    
    static Map<String, Object> newsModel(String type, NewsItem news) {
        Map<String, Object> model = new HashMap<>();
        model.put("news", news);
        model.put("type", type);
        model.put("template", "templates/news.html");
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
