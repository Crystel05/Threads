import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Controller {

    public static void moverPersonaje(int velocidad, ImageView animal){
        Timeline moverse = new Timeline(new KeyFrame(Duration.millis(velocidad), new EventHandler<ActionEvent>() {
            int pos = 2;
            @Override
            public void handle(ActionEvent actionEvent) {
                animal.setLayoutX(animal.getLayoutX() + pos);
                int bordeMax = 680;
                boolean bordeDerecho = animal.getLayoutX() >= bordeMax;
                System.out.println(bordeDerecho);
                System.out.println(animal.getLayoutX());

                if (bordeDerecho)
                    pos = 0;
            }
        }));
        moverse.setCycleCount(Animation.INDEFINITE);
        moverse.play();
    }
}
