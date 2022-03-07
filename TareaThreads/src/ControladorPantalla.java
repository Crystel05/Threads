import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ControladorPantalla implements Initializable, DragWindow {

    private Animal tortugaA;
    private Animal liebreA;

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
    private Button ajustador;


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

    public Button getAjustador() {
        return ajustador;
    }

    public Slider getLiebreSlider() {
        return liebreSlider;
    }

    public Slider getTortugaSlider() {
        return tortugaSlider;
    }

    @FXML
    void comenzar(ActionEvent event) {
        tortugaA = new Animal("Tortuga", tortuga, meta, 25, this);
        liebreA = new Animal("Liebre", liebre, meta, 20, this);
        Thread tortu = new Thread(tortugaA);
        Thread lie = new Thread(liebreA);
        lie.start();
        tortu.start();
    }

    @FXML
    void reajustarVelocidad(ActionEvent event) {

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

