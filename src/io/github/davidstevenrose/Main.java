package io.github.davidstevenrose;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the driver class that creates a connection to the database, loads ProductionLine.fxml, and opens
 * the application.
 *
 * @author drose
 */
public class Main extends Application {

  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./res/ProductionLineDB";
  /**
   * The connection to the provided database.
   */
  public static Connection conn;

  @Override
  public void start(Stage primaryStage) throws Exception {
    //loads the fxml file, then the css stylesheet
    Parent root = FXMLLoader.load(getClass().getResource("ProductionLine.fxml"));
    Scene scene = new Scene(root, 600, 400);
    scene.getStylesheets().add(
        Main.class.getResource("\\..\\..\\..\\ProductionLine.css").toExternalForm()
    );
    //opens the application
    primaryStage.setTitle("Production Line");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  /**
   * Main method.
   *
   * @param args passed arguments
   */
  public static void main(String[] args) {
    try {
      //register JDBC driver
      Class.forName(JDBC_DRIVER);
      //creates a connection for every instance of Main
      Main.conn = DriverManager.getConnection(DB_URL);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //test the AudioPlayer class
    AudioPlayer ap = new AudioPlayer("ipod", "apple", ItemType.AUDIO, "High Spec");
    System.out.println(ap.toString());

    //opens the application
    launch(args);
  }
}
