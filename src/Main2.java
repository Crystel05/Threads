import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main2 {

    public static void main(String args[]){
        Thread liebre = new Thread(new Animal("Liebre", 100));
        Thread tortuga = new Thread(new Animal("Tortuga", 200));

        liebre.start();
        tortuga.start();
    }
}
