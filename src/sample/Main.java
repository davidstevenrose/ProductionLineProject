package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main extends Application {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./res/ProductionLineDB";

    public static Main instance;

    public Connection conn;

    @Override
    public void start(Stage primaryStage) throws Exception{

        try{
            Class.forName(JDBC_DRIVER);     //register JDBC driver
            conn = DriverManager.getConnection(DB_URL);

        }catch(Exception e){
            e.printStackTrace();
        }

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Production Line");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        //Although it is poor to set the instance property from the 'start' method,
        //a better method could not yet be found that is static. This will do for now.
        Main.instance = this;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
