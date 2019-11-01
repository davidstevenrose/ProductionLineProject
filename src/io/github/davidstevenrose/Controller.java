package io.github.davidstevenrose;

import java.sql.PreparedStatement;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
  private TextField productTxt;
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
  private ChoiceBox<ItemType> typeBox;
  /**
   * Button that adds the new product information. Located in the Product Line tab.
   */
  @FXML
  private Button addProductBtn;
  /**
   * A table that displays all existing products. Located in the Product Line tab. Displays a row in
   * the order of product id, type, manufacturer, and name.
   */
  @FXML
  private TableView<Product> productTable;
  /**
   * A list view that displays all products. Located in the Produce Line tab. Selected items are
   * used to record production.
   */
  @FXML
  private ListView<Product> produceList;
  /**
   * An editable drop down menu. This allows a quantitative selection of a product from the Product
   * line table. Located in the Product Line tab.
   */
  @FXML
  private ComboBox<String> produceCbo;
  /**
   * A button to record production. Adds the selected amount of products from produceCbo to the
   * table in the product line tab. Located in the Product Line tab.
   */
  @FXML
  private Button produceBtn;
  /**
   * The text area in the Production Log tab. Displays all production records of items.
   */
  @FXML
  private TextArea productLogTxt;
  /**
   * A list of all the different makes of a product. Stores each new product specification as the
   * product's ID.
   */
  private final Map<Product, Integer> companyProducts = new HashMap<>();

  /**
   * Initializes the values in the produce tab combobox.
   */
  @FXML
  protected void initialize() {
    //initialize the factory for existing product table
    // col 1 cell factory set product name
    productTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
    // col 2 cell factory set product manufacturer name
    productTable.getColumns().get(1)
        .setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    // col 3 cell factory set product item type
    productTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));

    for (int i = 1; i < 11; i++) {
      produceCbo.getItems().add(String.valueOf(i));
    }
    produceCbo.getSelectionModel().selectFirst();
    //should this be true? setting to true for now.
    produceCbo.setEditable(true);
    for (ItemType itemType : ItemType.values()) {
      typeBox.getItems().add(itemType);
    }

    //when product line add button is pressed, input will be added to the existing products table.
    addProductBtn.setOnMouseClicked(event -> {
      //call method to handle
      getInputAndAddToTable();
    });

    //when produce tab add button is pressed
    produceBtn.setOnMouseClicked(event -> {
      //call method to handle
      addProductsToLine();
    });

    //anytime a product is added to the table, add to production log
    //remember to change contents of log to ProductionRecord objects
  }

  /**
   * Adds a product to the product line table in the H2 database.
   * this method is currently hooked by fxml DOM
   *
   * @param event a passed event
   */
  @FXML
  protected void addProductFromProductLine(MouseEvent event) {
    //these queries inserts items called 'nail' and its attributes into the Product table
    String queryOne = "Insert Into product(name,type,manufacturer)"
        + "Values('Nail','Construction','Home Depot');";
    String queryTwo = "INSERT INTO product(name,type,manufacturer)"
        + "VALUES('Nail','AM',?)";
    try {
      //create some placeholder statements
      PreparedStatement psOne = Main.conn.prepareStatement(queryOne);
      PreparedStatement psTwo = Main.conn.prepareStatement(queryTwo);
      psOne.executeUpdate();
      System.out.println("Product 1 added.");
      psTwo.setString(1, "Sun");
      psTwo.executeUpdate();
      System.out.println("Product 2 added.");
      psOne.close();
      psTwo.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Takes the current input from the product line tab and creates a new product object. The object
   * is then placed into the table for existing products. Then, the object in string form is added
   * to the produce tab text view.
   */
  private void getInputAndAddToTable() {
    //get input
    String prodName = productTxt.getText();
    String manuName = manufactureProd.getText();
    ItemType prodType = typeBox.getValue();
    Product newProd;
    if (prodType == ItemType.VISUAL || prodType == ItemType.VISUAL_MOBILE) {
      newProd = new MoviePlayer(prodName, manuName, prodType, new Screen("244p", 0, 0),
          MonitorType.LCD);
    } else {
      newProd = new AudioPlayer(prodName, manuName, prodType, "no specs");
    }
    //for now set a random id to the product
    newProd.setId(new Random().nextInt(Integer.MAX_VALUE));
    //clear input in UI
    productTxt.setText("");
    manufactureProd.setText("");
    typeBox.setValue(null);

    //add product to table
    ObservableList<Product> tableElements = productTable.getItems();
    tableElements.add(newProd);

    //add product to list of company products. if product is new, then update produce text area.
    //this might do a compare by ref, keep in mind.
    if (!companyProducts.containsKey(newProd)) {
      int uniqueID = new Random().nextInt(Integer.MAX_VALUE);
      while (companyProducts.containsValue(uniqueID)) {
        uniqueID = new Random().nextInt(Integer.MAX_VALUE);
      }
      newProd.setId(uniqueID);
      companyProducts.put(newProd, uniqueID);
      produceList.getItems().add(newProd);
      produceList.setCellFactory(e -> new ListCell<Product>() {
        @Override
        protected void updateItem(Product prod, boolean empty) {
          super.updateItem(prod, empty);
          if (empty || prod == null) {
            setText(null);
          } else {
            setText(prod.toString());
          }
        }
      });
    }

    productLogTxt.appendText(newProd.toString());
    productLogTxt.appendText("===============================\n");
  }

  /**
   * Gets the selected product from the produce text area and adds a quantity to the product line
   * table.
   */
  private void addProductsToLine() {
    Product selected = produceList.getSelectionModel().getSelectedItem();
    int quantity = Integer.parseInt(produceCbo.getValue());
    for (int count = 0; count < quantity; count++) {
      productTable.getItems().add(selected);
      productLogTxt.appendText(selected.toString());
      productLogTxt.appendText("===============================\n");
    }
    produceCbo.setValue(null);
  }
}
