import static spark.Spark.*;
import java.util.HashMap;

import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
//        get("/", (request, response) -> {
//
//            return new ModelAndView(new HashMap(), "hello.hbs");
//
//        }, new HandlebarsTemplateEngine());
//
//        get("/favourite-photos", (request, response) -> {
//            return new ModelAndView(new HashMap(), "favourite-photos.hbs");
//        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/greeting_card", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String recipient = request.queryParams("recipient");
            String sender = request.queryParams("sender");
            model.put("recipient", recipient);
            model.put("sender", sender);
            return new ModelAndView(model, "greeting_card.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
