package Vista;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import Modelo.Animal;
import Modelo.Tipos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ControladorPantalla implements Initializable, DragWindow {

    private Animal tortugaA;
    private Animal liebreA;
    private Thread tortu;
    private Thread lie;

    @FXML
    private Pane panel;

    @FXML
    private ImageView liebre;

    @FXML
    private ImageView meta;

    @FXML
    private ImageView tortuga;

    @FXML
    private Slider liebreSlider;

    @FXML
    private Text liebreTiempo;

    @FXML
    private Text liebreVelocidad;

    @FXML
    private Slider tortugaSlider;

    @FXML
    private Text tortugaTiempo;

    @FXML
    private Text tortugaVelocidad;

    @FXML
    private Button ajustadorTor;

    @FXML
    private Button ajustadorLie;


    public Text getLiebreTiempo() {
        return liebreTiempo;
    }

    public Text getLiebreVelocidad() {
        return liebreVelocidad;
    }

    public Text getTortugaTiempo() {
        return tortugaTiempo;
    }

    public Text getTortugaVelocidad() {
        return tortugaVelocidad;
    }

    public Button getAjustadorLie() {
        return ajustadorLie;
    }

    public Button getAjustadorTor() {
        return ajustadorTor;
    }

    public Slider getLiebreSlider() {
        return liebreSlider;
    }

    public Slider getTortugaSlider() {
        return tortugaSlider;
    }

    @FXML
    void comenzar(ActionEvent event) {
        tortugaA = new Animal(Tipos.Tortuga, tortuga, meta, 25, this);
        liebreA = new Animal(Tipos.Liebre, liebre, meta, 20, this);
        tortu = new Thread(tortugaA);
        lie = new Thread(liebreA);
        lie.start();
        tortu.start();
    }

   

    @FXML
    void cerrar(MouseEvent event){
        System.exit(1);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onDraggedScene(panel);
    }
}

