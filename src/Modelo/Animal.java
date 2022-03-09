package Modelo;

import Vista.ControladorPantalla;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static java.lang.Thread.sleep;

public class Animal implements Runnable{

    private Tipos tipo;
    private ImageView imagenPersonaje;
    private int velocidad;
    private boolean dormido;
    private int tiempo = 0;
    private boolean borde;
    private Timeline moverse;
    private boolean bordeDerecho;
    private ImageView meta;
    private ControladorPantalla controladorPantalla;


    public Animal(Tipos tipo, ImageView imagenPersonaje, ImageView meta, int velocidad, ControladorPantalla controladorPantalla) {
        this.tipo = tipo;
        this.imagenPersonaje = imagenPersonaje;
        this.velocidad = velocidad;
        this.meta = meta;
        this.controladorPantalla = controladorPantalla;
    }

    public Animal(Tipos tipo, int velocidad) {
        this.tipo = tipo;
        this.velocidad = velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public void run() {
        System.out.println(tipo + " comenzó a correr");
        moverPersonaje();
        if (tipo.equals(Tipos.Liebre)){
            controladorPantalla.getLiebreVelocidad().setText(String.valueOf(velocidad));
        }else if (tipo.equals(Tipos.Tortuga)){
            controladorPantalla.getTortugaVelocidad().setText(String.valueOf(velocidad));
        }
        while (true){
            if (imagenPersonaje.getLayoutX() >= 290){
                System.out.println(tipo + " " + imagenPersonaje.getLayoutX());
            }
            System.out.println(tipo + " " + tiempo);
            if (tiempo >=100 && tiempo <= 110 && tipo.equals(Tipos.Liebre)){
                try {
                    cambiarImagen(true);
                    dormido = true;
                    sleep(5000);
                    dormido = false;
                    cambiarImagen(false);
                } catch (FileNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (borde){
                moverse.stop();
                break;
            }

            controladorPantalla.getAjustadorLie().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    velocidad = (int) controladorPantalla.getLiebreSlider().getValue();
                    moverse.stop();
                    moverPersonaje();
                    controladorPantalla.getLiebreVelocidad().setText(String.valueOf(velocidad));
                }
            });

            controladorPantalla.getAjustadorTor().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    velocidad = (int) controladorPantalla.getTortugaSlider().getValue();
                    moverse.stop();
                    moverPersonaje();
                    controladorPantalla.getTortugaVelocidad().setText(String.valueOf(velocidad));
                }
            });
        }
        System.out.println(tipo + " terminó de correr\n\n\n");

    }

    private void moverPersonaje(){
        moverse = new Timeline(new KeyFrame(Duration.millis(velocidad), new EventHandler<ActionEvent>() {
            int pos = 2;
            @Override
            public void handle(ActionEvent actionEvent) {
                imagenPersonaje.setLayoutX(imagenPersonaje.getLayoutX() + pos);
                int bordeMax = 865;
                bordeDerecho = imagenPersonaje.getLayoutX() >= bordeMax;
                tiempo++;
                if (dormido){
                    pos = 0;
                }else{
                    pos = 2;
                }
                if (bordeDerecho) {
//                    if (tipo.equals(Tipos.Liebre))
//                        controladorPantalla.getLiebreTiempo().setText(String.valueOf(tiempo/50));
//                    else if (tipo.equals(Tipos.Tortuga))
//                        controladorPantalla.getTortugaTiempo().setText(String.valueOf(tiempo/50));
                    try {
                        cambiarMeta();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    borde = true;
                    pos = 0;
                }
//                }else{
//                    if (tipo.equals(Tipos.Liebre))
//                        controladorPantalla.getLiebreTiempo().setText(String.valueOf(tiempo/50));
//                    else if (tipo.equals(Tipos.Tortuga))
//                        controladorPantalla.getTortugaTiempo().setText(String.valueOf(tiempo/50));
//                }
            }
        }));
        moverse.setCycleCount(Animation.INDEFINITE);
        moverse.play();
    }

    private void cambiarMeta() throws FileNotFoundException {
        String path = "src/Vista/Imagenes/metaAmbos.png";
        InputStream stream = new FileInputStream(path);
        Image image = new Image(stream);
        meta.setImage(image);
    }

    private void cambiarImagen(boolean dormir) throws FileNotFoundException {
        String path;
        if (dormir) {
            path = "src/Vista/Imagenes/liebreDormida.png";
        }else{
            path = "src/Vista/Imagenes/liebre.png";
        }
        InputStream stream = new FileInputStream(path);
        Image image = new Image(stream);
        imagenPersonaje.setImage(image);
    }
}
