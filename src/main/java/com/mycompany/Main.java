package com.mycompany;

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

        get("/hello", (req, res) -> "Hello World2");

        get("/hello2", (request, response) -> {
            return "Hello World2";
        });

        get("/hei/:name", (request, response) -> {
            return "heippa " + request.params(":name");
        });

        get("/", (request, response) -> {
            return "<h1>lol</h1>";
        });

        Map map = new HashMap();
        map.put("name", "Foobar");
        get("/moi", (rq, rs) -> new ModelAndView(map, "hello.mustache"), new MustacheTemplateEngine());

        get("/moido", (request, response) -> {
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
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
