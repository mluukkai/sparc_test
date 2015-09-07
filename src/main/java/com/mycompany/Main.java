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
            return "<h1>Hackernews-uutiset</h1>"
                    + "<a href='suosituin'>suosituin</a> <br>"
                    + "<a href='viimeisin'>viimeisin</a>";
        });

        get("viimeisin", (request, response) -> {
            NewsItem news = new NewsParser(hackerNews().haeViimeisinUutinen()).parse();

            return htmlFor("Viimeisin", news);
        });

        get("suosituin", (request, response) -> {
            NewsItem news = new NewsParser(hackerNews().haeSuosituinUutinen()).parse();

            return htmlFor("Suosituin", news);
        });

        get("hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("name", "Lol");
            //model.put("person", new Person("Foobar"));

            return new ModelAndView(model, "hello.mustache.html");
        }, new MustacheTemplateEngine());

    }
    
    static PaivanUutiset uutisHakija = new HackerPaivanUutiset();
    
    static void setUutishakija(PaivanUutiset hakija){
        uutisHakija = hakija;
    }
    
    static PaivanUutiset hackerNews(){
        return uutisHakija;
    }
    
    static String htmlFor(String header, NewsItem news) {
        return "<h1>"+header+" uutinen</h1>"
                + "<p><em>" + news.body + "</em></p>"
                + "<a href='" + news.url + "'>"+news.url +"</a>"
                + "<br><br>"
                + "<a href='..'>takaisin</<a>";
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
