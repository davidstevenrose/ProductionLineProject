package sample;

import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

  @FXML
  public TabPane window;
  @FXML
  private TextField addProductTxt;
  @FXML
  private TextField manufactureProd;
  @FXML
  private ChoiceBox<String> typeBox;
  @FXML
  private Button addProductBtn;
  @FXML
  private TableView productTable;//T
  @FXML
  private ListView produceList;//T
  @FXML
  private ComboBox<Integer> produceCbo;
  @FXML
  private Button produceBtn;
  @FXML
  private TextArea productLogTxt;

  /**
   * Initializes the values in the produce tab combobox
   */
  @FXML
  protected void initialize() {
    for (int i = 1; i < 11; i++) {
      produceCbo.getItems().add(i);
    }
    produceCbo.getSelectionModel().selectFirst();
    produceCbo.setEditable(true);
  }

  /**
   * Adds a product to the product line table in the H2 database
   * @param event a passed event
   */
  @FXML
  protected void addProductFromProductLine(MouseEvent event) {
    //this query inserts an item called 'nail' and its attributes into the Product table
    String queryOne = "Insert Into product(name,type,manufacturer)"
        + "Values('Nail','Construction','Home Depot');";
    try {
      Statement st = new Main().conn.createStatement();
      st.executeUpdate(queryOne);
      System.out.println("Product added.");
      st.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * placeholder event
   * @param e placeholder
   */
  @FXML
  protected void doAction(MouseEvent e) {
    System.out.println("Button Pressed");
  }
}
