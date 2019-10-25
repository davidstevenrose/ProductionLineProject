package io.github.davidstevenrose;

import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.Date;
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
  private ChoiceBox<ItemType> typeBox;
  /**
   * Button that adds the new product information. Located in the Product Line tab.
   */
  @FXML
  private Button addProductBtn;
  /**
   * A table that displays Products (more details TBA). Located in the Product Line tab.
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
   * The text area in the Production Log tab. Displays all production records of items.
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
    //should this be true? setting to true for now.
    produceCbo.setEditable(true);
    for (ItemType itemType : ItemType.values()) {
      typeBox.getItems().add(itemType);
    }

    testMultiMedia();
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
   * Test the various products built up to Sprint 2 week 10.
   */
  private void testMultiMedia() {
    //change itemType arguments from a String to ItemType in
    // newAudioProduct and newMovieProduct
    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Vanselow Microsystems",
        ItemType.AUDIO, "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct = new MoviePlayer("PAULALLEN MK101", "OracleProduction",
        ItemType.VISUAL, newScreen,
        MonitorType.LCD);

    ArrayList<MultimediaControl> products = new ArrayList<>();
    products.add(newAudioProduct);
    products.add(newMovieProduct);
    for (MultimediaControl p : products) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }

    //Test ProductionRecord class
    ProductionRecord rec1 = new ProductionRecord(21);
    System.out.println(rec1);
    productLogTxt.setText(rec1.toString());
    productLogTxt.appendText("\n---------------------------------------\n");

    //Test serial number generator
    MoviePlayer applePlayer = new MoviePlayer("PAULALLEN MK101", "OracleProduction",
        ItemType.VISUAL, newScreen,
        MonitorType.LCD);
    ProductionRecord rec2 = new ProductionRecord(22, 7124, new Date(), applePlayer, 5000);
    System.out.println("Serial number:");
    System.out.println(rec2.getSerialNumber());
  }
}
