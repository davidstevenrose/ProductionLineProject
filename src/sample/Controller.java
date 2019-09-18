package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

import java.sql.Statement;


public class Controller {

    @FXML
    private Button productionLineButton;
    @FXML
    private Button produceButton;
    @FXML
    private Button productionLogButton;
    @FXML
    private ComboBox produceCbo;

    @FXML
    protected void initialize(){
        for(int i=1;i<11;i++){
            produceCbo.getItems().add(i);
        }
        produceCbo.getSelectionModel().selectFirst();
        produceCbo.setEditable(true);
    }
    @FXML
    protected void addProductFromProductLine(MouseEvent event) {
        //this query inserts an item called 'nail' and its attributes into the Product table
        String queryOne = "Insert Into product(name,type,manufacturer) Values('Nail','Construction','Home Depot');";
        try{
            Statement st = Main.instance.conn.createStatement();
            st.executeUpdate(queryOne);
            System.out.println("Product added.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    protected void doAction(MouseEvent e){
        System.out.println("Button Pressed");
    }
}
