package io.github.davidstevenrose;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

  //private ObservableList<Product> productTabTableElements;

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
    //setup produce combo box
    for (int i = 1; i < 11; i++) {
      produceCbo.getItems().add(String.valueOf(i));
    }
    produceCbo.getSelectionModel().selectFirst();
    produceCbo.setEditable(true);
    //setup type choice box
    for (ItemType itemType : ItemType.values()) {
      typeBox.getItems().add(itemType);
    }

    //assign the list from productTable to tableElements field
    //productTabTableElements = productTable.getItems();

    //initialize products in all tabs
    setupProductLineTable();
    //loadProductList();
    loadProductionLog();

    //init hooks

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
    //check for input
    if (prodName.isEmpty() || manuName.isEmpty() || prodType == null) {
      Alert a = new Alert(AlertType.ERROR);
      a.setContentText("Please provide a product name, manufacturer, or item type.");
      a.show();
      return;
    }

    //for now set a random id to the product
    //newProd.setId(new Random().nextInt(Integer.MAX_VALUE));
    //clear input in UI
    productTxt.setText("");
    manufactureProd.setText("");
    typeBox.setValue(null);

    //attempt to add data to database
    try {
      //check if product is already in produce list
      String matchingProduct = "SELECT * FROM PRODUCT WHERE NAME=? AND TYPE=? AND MANUFACTURER=?";
      String addProductQuery = "INSERT INTO product(NAME,MANUFACTURER,TYPE)"
          + "VALUES(?,?,?)";
      PreparedStatement matchStatement = Main.conn.prepareStatement(matchingProduct);
      matchStatement.setString(1, prodName);
      matchStatement.setString(2, manuName);
      matchStatement.setString(3, prodType.getCode());
      ResultSet existingProduct = matchStatement.executeQuery();
      //check if the DB already contains the product. If the db does not, then simply do nothing to
      // simulate the entry of the product.
      if (existingProduct.getFetchSize() == 0) {
        PreparedStatement apqStatement = Main.conn.prepareStatement(addProductQuery);
        apqStatement.setString(1, prodName);
        apqStatement.setString(2, manuName);
        apqStatement.setString(3, prodType.getCode());
        apqStatement.executeUpdate();
        apqStatement.close();
      }
      matchStatement.close();
    } catch (Exception e) {
      e.printStackTrace();
      Alert dbAlert = new Alert(AlertType.ERROR);
      dbAlert.setContentText(e.toString());
      return;
    }
    loadProductList();
  }

  /**
   * Gets the selected product from the produce text area and adds a quantity to the product line
   * table.
   */
  private void addProductsToLine() {
    Product selected = produceList.getSelectionModel().getSelectedItem();

    //if no product is selected
    if (selected == null) {
      Alert a = new Alert(AlertType.ERROR);
      a.setContentText("Please select a product to produce.");
      a.show();
      return;
    }

    //if no quantity is selected or is NAN
    int quantity;
    try {
      quantity = Integer.parseInt(produceCbo.getValue());
    } catch (NumberFormatException e) {
      Alert a = new Alert(AlertType.ERROR);
      a.setContentText("Please enter a valid integer in the produce combo box.");
      a.show();
      return;
    } catch (Exception e) {
      Alert a = new Alert(AlertType.ERROR);
      a.setContentText("Please enter a number of products to produce.");
      a.show();
      return;
    }

    ArrayList<ProductionRecord> productionRun = new ArrayList<>();
    ProductionRecord pr;
    int instances;
    String instancesQuery = "SELECT * FROM PRODUCTIONRECORD";
    try {
      PreparedStatement query = Main.conn.prepareStatement(instancesQuery);
      ResultSet productionTable = query.executeQuery();
      productionTable.next();
      instances = productionTable.getFetchSize();
      query.close();
    } catch (Exception e) {
      //TODO: exception handling
      e.printStackTrace();
      return;
    }
    for (int count = 0; count < quantity; count++) {
      //TODO: fix serial number generation
      pr = new ProductionRecord(selected.getId(), new Date(), selected, instances);
      instances++;
      productionRun.add(pr);
    }
    addToProductionDB(productionRun);
    loadProductionLog();
    produceCbo.setValue(null);
  }

  /**
   * initializes data from database upon application startup.
   */
  private void setupProductLineTable() {
    loadProductList();
  }

  /**
   * Loads the entire Product table from the database to the produce list view.
   */
  private void loadProductList() {
    //get products from db
    ResultSet products;
    Product pulledProd;
    String getQuery = "SELECT * FROM product";
    ArrayList<Product> newList = new ArrayList<>();
    try {
      PreparedStatement query = Main.conn.prepareStatement(getQuery);
      products = query.executeQuery();
      while (products.next()) {
        if (products.getString(3).equals(ItemType.VISUAL.getCode())) {
          pulledProd = new MoviePlayer(products.getInt(1), products.getString(2),
              products.getString(4), ItemType.VISUAL, new Screen("144x144", 240, 12),
              MonitorType.LCD);
        } else if (products.getString(3).equals(ItemType.VISUAL_MOBILE.getCode())) {
          pulledProd = new MoviePlayer(products.getInt(1), products.getString(2),
              products.getString(4), ItemType.VISUAL_MOBILE, new Screen("144x144", 240, 12),
              MonitorType.LCD);
        } else if (products.getString(3).equals(ItemType.AUDIO.getCode())) {
          pulledProd = new AudioPlayer(products.getInt(1), products.getString(2),
              products.getString(4), ItemType.AUDIO, "VANSELOWSPECS");
        } else {
          pulledProd = new AudioPlayer(products.getInt(1), products.getString(2),
              products.getString(4), ItemType.AUDIO_MOBILE, "VANSELOWSPECS");
        }
        //add products to list
        newList.add(pulledProd);
      }
      produceList.getItems().setAll(newList);
      query.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    /*/add product to table
    pulledProd.toString();
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
    }*/
  }

  /**
   * Adds the records in a list of records to the production log database.
   *
   * @param records a collection of records to add to production log DB
   */
  private void addToProductionDB(ArrayList<ProductionRecord> records) {
    String query = "insert into PRODUCTIONRECORD(product_id, serial_num, date_produced) "
        + "VALUES(?,?,?)";
    try {
      for (ProductionRecord record : records) {
        System.out.println("attempting to add to DB");
        PreparedStatement statement = Main.conn.prepareStatement(query);
        statement.setInt(1, record.getProductID());
        statement.setString(2, record.getSerialNumber());
        statement.setTimestamp(3, new Timestamp(record.getDateProduced().getTime()));
        statement.executeUpdate();
        statement.close();
      }
    } catch (Exception e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * Loads a list of all produced products from the database and calls the showProduction procedure
   * if the SQL queue was a success.
   */
  private void loadProductionLog() {
    try {
      ArrayList<ProductionRecord> newProductions = new ArrayList<>();
      ArrayList<String> newProducts = new ArrayList<>();
      String logQuery = "SELECT * FROM PRODUCTIONRECORD";
      String idQuery = "SELECT NAME FROM PRODUCT WHERE ID=?";
      PreparedStatement logStatement = Main.conn.prepareStatement(logQuery);
      PreparedStatement idStatement = Main.conn.prepareStatement(idQuery);
      ResultSet log = logStatement.executeQuery();
      while (log.next()) {
        //retrieve product
        idStatement.setInt(1, log.getInt("PRODUCT_ID"));
        ResultSet product = idStatement.executeQuery();
        product.next();
        newProductions.add(new ProductionRecord(log.getInt(1), log.getInt(2), log.getString(3),
            log.getTimestamp(4)));
        newProducts.add(product.getString("NAME"));
      }
      idStatement.close();
      logStatement.close();
      showProduction(newProductions, newProducts);
    } catch (Exception e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * Populates the text area on the production log tab with the information from the production log
   * database. Replaces the productID with the product name. Occupies one line for each product
   * produced. Every time the production log loads, new records will be loaded by clearing the text
   * area and appending the table to the text area.
   */
  private void showProduction(ArrayList<ProductionRecord> newRecords,
      ArrayList<String> newProducts) {
    productLogTxt.clear();
    for (int ind = 0; ind < newRecords.size(); ind++) {
      //add record to text log
      String line = "Name: " + newProducts.get(ind) + "; "
          + "Serial Number: " + newRecords.get(ind).getSerialNumber() + "; "
          + "Date Produced: " + newRecords.get(ind).getDateProduced().toString();
      productLogTxt.appendText(line);
      productLogTxt.appendText("\n=======================================\n");
    }
  }
}
