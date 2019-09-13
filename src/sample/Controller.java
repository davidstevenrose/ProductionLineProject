package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    private Button productionLineButton;
    @FXML
    private Button recordProductionButton;
    @FXML
    private Button productionLogButton;

    @FXML
    protected void doAction(MouseEvent event) {
        System.out.println("Button Pressed");
    }
}
