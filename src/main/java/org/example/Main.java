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


        app.post("/login", ctx -> {
            //cookie
            ctx.cookie("loggedIn", "true");
            ctx.redirect("/logeado");
        });
    }

    private static boolean isUserLoggedIn(Context ctxContextContext) {
        String loggedInCookie = ctxContextContext.cookie("loggedIn");
        return loggedInCookie != null && loggedInCookie.equals("true");
    }
}
/*import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Main {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/publico");
        }).start(7000);

        // Filtro para verificar la autenticación
        app.before("/isc415", ctxContextContext -> {
            if (!isUserLoggedIn(ctxContextContext)) {
                ctxContextContext.redirect("/login");
            }
        });

        // Ruta de inicio
        app.get("/isc415", ctxContextContext -> {
            ctxContextContext.html("<h1>¡Bienvenido!</h1>");
        });

        // Ruta para mostrar el formulario de inicio de sesión
        app.get("/login", ctxContextContext -> {
            ctxContextContext.html("<h1>Iniciar sesion</h1>" +
                    "<form action=\"/login\" method=\"post\">" +
                    "  <input type=\"text\" name=\"username\" placeholder=\"Usuario\"><br>" +
                    "  <input type=\"password\" name=\"password\" placeholder=\"Contrasena\"><br>" +
                    "  <input type=\"submit\" value=\"Iniciar sesion\">" +
                    "</form>");
        });

        // Proceso para validar el formulario de inicio de sesión
       /* app.post("/login", ctxContextContext -> {
            String username = ctxContextContext.formParam("username");
            String password = ctxContextContext.formParam("password");

            // Validar las credenciales aquí

            // Si las credenciales son válidas, redireccionar a la página de inicio
            ctxContextContext.redirect("/");
        });*/

        // Incluir CSS en la página de inicio y formulario
        /*app.get("/styles.css", ctx -> {
            ctx.contentType("text/css");
        //});
       /* app.after(ctxContextContext -> {

        });
    }

    private static boolean isUserLoggedIn(Context ctx) {
        String loggedInCookie = ctx.cookie("loggedIn");
        return loggedInCookie != null && loggedInCookie.equals("true");
    }
}
*/