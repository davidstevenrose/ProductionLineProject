package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the driver class that creates a connection to the database, loads sample.fxml, and opens
 * the application.
 *
 * @author drose
 */
public class Main extends Application {

  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./res/ProductionLineDB";
  public Connection conn;

  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      //register JDBC driver
      Class.forName(JDBC_DRIVER);
      //creates a connection for every instance of Main
      conn = DriverManager.getConnection(DB_URL);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //loads the fxml file, then the css stylesheet
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Scene scene = new Scene(root, 600, 400);
    scene.getStylesheets().add(
        Main.class.getResource("\\..\\ProductionLine.css").toExternalForm()
    );
    //opens the application
    primaryStage.setTitle("Production Line");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
