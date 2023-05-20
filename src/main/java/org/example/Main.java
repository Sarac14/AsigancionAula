package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/publico", Location.CLASSPATH);

        }).start(8060);

        app.before("/logeado", ctx -> {
            if (!isUserLoggedIn(ctx)) {
                ctx.redirect("/login");
            }
        });


        app.get("/logeado", ctx -> {
            ctx.result("Usted ya está logeado :D");
        });


    }

    private static boolean isUserLoggedIn(Context ctxContextContext) {

        return false;
    }
}
