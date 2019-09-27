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

/**
 * This class handles events within the application opened in Main.java.
 *
 * @author drose
 */
public class Controller {

  /**
   * The tab pane that contains all the modules of the program. This is the parent frame of the
   * graphic user interface.
   */
  @FXML
  public TabPane window;
  /**
   * Text field to add the name of a new product. Located in the Product Line tab.
   */
  @FXML
  private TextField addProductTxt;
  /**
   * Text field to add the name the manufacturer of the new product. Located in the Product Line
   * tab.
   */
  @FXML
  private TextField manufactureProd;
  /**
   * Drop down box to select the type of the new product. Located in the Product Line tab.
   */
  @FXML
  private ChoiceBox<String> typeBox;
  /**
   * Button that adds the new product information. Located in the Product Line tab.
   */
  @FXML
  private Button addProductBtn;
  /**
   * A table (Details TBA). Located in the Product Line tab.
   */
  @FXML
  private TableView<Product> productTable;
  /**
   * A list view (Details TBA). Located in the Produce Line tab.
   */
  @FXML
  private ListView produceList;//T
  /**
   * An editable drop down menu (Details TBA). Located in the Product Line tab.
   */
  @FXML
  private ComboBox<Integer> produceCbo;
  /**
   * A button to record production (Details TBA). Located in the Product Line tab.
   */
  @FXML
  private Button produceBtn;
  /**
   * The text area in the Production Log tab (Details TBA).
   */
  @FXML
  private TextArea productLogTxt;

  /**
   * Initializes the values in the produce tab combobox.
   */
  @FXML
  protected void initialize() {
    for (int i = 1; i < 11; i++) {
      produceCbo.getItems().add(i);
    }
    produceCbo.getSelectionModel().selectFirst();
    //should this be true?
    produceCbo.setEditable(true);
    String typeLabel;
    for (ItemType itemType : ItemType.values()) {
      typeLabel = itemType.toString();
      typeBox.getItems().add(typeLabel);
    }
  }

  /**
   * Adds a product to the product line table in the H2 database.
   *
   * @param event a passed event
   */
  @FXML
  protected void addProductFromProductLine(MouseEvent event) {
    //these queries inserts items called 'nail' and its attributes into the Product table
    String queryOne = "Insert Into product(name,type,manufacturer)"
        + "Values('Nail','Construction','Home Depot');";
    String queryTwo = "INSERT INTO product(name,type,manufacturer)"
        + "VALUES('Nail','AM','Sun')";
    try {
      Statement st = Main.conn.createStatement();
      st.executeUpdate(queryOne);
      System.out.println("Product 1 added.");
      st.executeUpdate(queryTwo);
      System.out.println("Product 2 added.");
      st.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //add a widget to the product table
    Widget wid = new Widget("Hammer");
    wid.setManufacturer("Lowes");
    productTable.getItems().add(wid);
  }

  /**
   * placeholder event.
   *
   * @param e placeholder
   */
  @FXML
  protected void doAction(MouseEvent e) {
    System.out.println("Button Pressed");
  }
}
