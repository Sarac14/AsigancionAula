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

        }).start(7010);


        app.before("/", ctx -> {
            if (!isUserLoggedIn(ctx)) {
                ctx.redirect("/login");
            }
        });



        app.get("/", ctx -> {
            ctx.result("Pagina Inicio");
        });

        app.post("/login", ctx -> {
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");

            if (isValidCredentials(username, password)) {
                // Establecer una sesión de usuario (por ejemplo, mediante una cookie)
                ctx.sessionAttribute("username", username);
                ctx.redirect("/");
            } else {
                ctx.redirect("/login");
            }
        });

        app.get("/logeado", ctx -> {
            String username = ctx.sessionAttribute("username");
            if (username != null) {
                ctx.result("Usted ya está logeado :D");
            } else {
                ctx.redirect("/login");
            }
        });

        app.get("/login", ctx -> {
            ctx.redirect("/publico/formulario.html");

        });

    }

    private static boolean isValidCredentials(String username, String password) {
        return username.equals("admin") && password.equals("12345");
    }

    private static boolean isUserLoggedIn(Context ctx) {
        String username = ctx.sessionAttribute("username");
        return username != null;
    }

}