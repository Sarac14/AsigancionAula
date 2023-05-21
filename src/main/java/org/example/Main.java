package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;

public class Main {
    public static void main(String[] args) {
       /* Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/publico");
        }).start(7000);*/

        Javalin app = Javalin.create(config -> {
            //configurando los documentos estaticos.
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.precompress = false;
                staticFileConfig.aliasCheck = null;
            });
        });
        app.start(7010);

        app.before("/", ctx -> {
            if (!isUserLoggedIn(ctx)) {
                ctx.redirect("/login");
            }
        });

        app.get("/", ctx -> {
            ctx.result("Pagina Inicio");
        });

        app.post("/login", ctx -> {
            //String username = ctx.formParam("username");
            // String password = ctx.formParam("password");


            ctx.redirect("/");
        });

        app.get("/logeado", ctx -> {
            ctx.result("Usted ya está logeado :D");
        });

       app.get("/login", ctx -> {
            ctx.render("/publico/formulario.html");
        });


    }

    private static boolean isUserLoggedIn(Context ctx) {
        return false;
    }

}