import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static java.lang.Thread.sleep;

public class Animal implements Runnable{

    private String tipo;
    private ImageView imageView;
    private int velocidad;
    private boolean dormido;
    private int tiempo = 0;
    private boolean borde;
    private Timeline moverse;
    private boolean bordeDerecho;
    private ImageView meta;
    private ControladorPantalla controladorPantalla;


    public Animal(String tipo, ImageView imageView, ImageView meta, int velocidad, ControladorPantalla controladorPantalla) {
        this.tipo = tipo;
        this.imageView = imageView;
        this.velocidad = velocidad;
        this.meta = meta;
        this.controladorPantalla = controladorPantalla;
    }

    public Animal(String tipo, int velocidad) {
        this.tipo = tipo;
        this.velocidad = velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public void run() {
        System.out.println(tipo + " comenzó a correr");
        moverPersonaje(velocidad, imageView);
        if (tipo.equals("Liebre")){
            controladorPantalla.getLiebreVelocidad().setText(String.valueOf(velocidad));
        }else if (tipo.equals("Tortuga")){
            controladorPantalla.getTortugaVelocidad().setText(String.valueOf(velocidad));
        }
        while (true){
            if (imageView.getLayoutX() >= 290){
                System.out.println(tipo + " " + imageView.getLayoutX());
            }
            System.out.println(tipo + " " + tiempo);
            if (tiempo >=100 && tiempo <= 110 && tipo.equals("Liebre")){
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
                //System.out.println("\n\nENTRA " + tipo);
                break;
            }

            controladorPantalla.getAjustador().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (tipo.equals("Liebre")){
                        velocidad = (int) controladorPantalla.getLiebreSlider().getValue();
                    }else if (tipo.equals("Tortuga")){
                        velocidad = (int) controladorPantalla.getTortugaSlider().getValue();
                    }
                    moverse.stop();
                    moverPersonaje(velocidad, imageView);
                    if (tipo.equals("Liebre")){
                        controladorPantalla.getLiebreVelocidad().setText(String.valueOf(velocidad));
                    }else if (tipo.equals("Tortuga")){
                        controladorPantalla.getTortugaVelocidad().setText(String.valueOf(velocidad));
                    }
                }
            });
        }
//        while (true) {
//                try {
//                    System.out.println(imageView.getLayoutX());
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                sleep(100);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//
//                    imageView.setLayoutX(imageView.getLayoutX() + 2);
//                    if (imageView.getLayoutX() + 10 >= 870) {
//                        break;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
        System.out.println(tipo + " terminó de correr\n\n\n");

    }

    private void moverPersonaje(int velocidad, ImageView animal){
        moverse = new Timeline(new KeyFrame(Duration.millis(velocidad), new EventHandler<ActionEvent>() {
            int pos = 2;
            @Override
            public void handle(ActionEvent actionEvent) {
                animal.setLayoutX(animal.getLayoutX() + pos);
                int bordeMax = 865;
                bordeDerecho = animal.getLayoutX() >= bordeMax;
                tiempo++;
                if (dormido){
                    pos = 0;
                }else{
                    pos = 2;
                }
                if (bordeDerecho) {
                    if (tipo.equals("Liebre"))
                        controladorPantalla.getLiebreTiempo().setText(String.valueOf(tiempo/50));
                    else if (tipo.equals("Tortuga"))
                        controladorPantalla.getTortugaTiempo().setText(String.valueOf(tiempo/50));
                    try {
                        cambiarMeta();
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                    borde = true;
                    pos = 0;
                }else{
                    if (tipo.equals("Liebre"))
                        controladorPantalla.getLiebreTiempo().setText(String.valueOf(tiempo/50));
                    else if (tipo.equals("Tortuga"))
                        controladorPantalla.getTortugaTiempo().setText(String.valueOf(tiempo/50));
                }
            }
        }));
        moverse.setCycleCount(Animation.INDEFINITE);
        moverse.play();
    }

    private void cambiarMeta() throws FileNotFoundException {
        String path = "src/Vista.Imagenes/metaAmbos.png";
        InputStream stream = new FileInputStream(path);
        Image image = new Image(stream);
        meta.setImage(image);
    }

    private void cambiarImagen(boolean dormir) throws FileNotFoundException {
        String path;
        if (dormir) {
            path = "src/Vista.Imagenes/liebreDormida.png";
        }else{
            path = "src/Vista.Imagenes/liebre.png";
        }
        InputStream stream = new FileInputStream(path);
        Image image = new Image(stream);
        imageView.setImage(image);
    }
}
