package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.example.controladores.CrudTradicionalControlador;
import org.example.controladores.PlantillasControlador;
import org.example.servicios.BootStrapServices;
import org.example.servicios.DataBaseServices;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
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
        app.start(8080);

        //Iniciando el servicio
        BootStrapServices.startDb();
        DataBaseServices.getInstancia().testConexion();

        BootStrapServices.crearTablas();

        new PlantillasControlador(app).aplicarRutas();
        new CrudTradicionalControlador(app).aplicarRutas();


        //Parando el servicio
        BootStrapServices.stopDb();

    }
}