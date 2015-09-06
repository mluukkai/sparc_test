package com.mycompany;

import com.mycompany.hackernewsuutiset.HackerPaivanUutiset;
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

        get("/hello2", (request, response) -> {
            return "Hello World2";
        });

        get("/hei/:name", (request, response) -> {
            return "hei " + request.params(":name");
        });

        get("/", (request, response) -> {
            return "<h1>Hacker news -uutiset</h1>"+
            "<a href='suosituin'>suosituin</a> <br>"+
            "<a href='viimeisin'>viimeisin</a>";
        });

        get("viimeisin", (request, response) -> {
            HackerPaivanUutiset hakija = new HackerPaivanUutiset();
            return hakija.haeViimeisinUutinen();
        });

        get("suosituin", (request, response) -> {
            HackerPaivanUutiset hakija = new HackerPaivanUutiset();
            return hakija.haeSuosituinUutinen();
        });

        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("name", "Lol");
            //model.put("person", new Person("Foobar"));

            return new ModelAndView(model, "hello.mustache.html");
        }, new MustacheTemplateEngine());

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
