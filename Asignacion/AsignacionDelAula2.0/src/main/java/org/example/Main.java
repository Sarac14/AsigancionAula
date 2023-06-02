package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.example.controladores.CrudTradicionalControlador;
import org.example.controladores.PlantillasControlador;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Javalin app = Javalin.create(config ->{
            //configurando los documentos estaticos.
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.precompress=false;
                staticFileConfig.aliasCheck=null;
            });

        });

        new PlantillasControlador(app).aplicarRutas();
        new CrudTradicionalControlador(app).aplicarRutas();
    app.start(8080);

    }
}