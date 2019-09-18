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
    static final String DB_URL = "jdbc:h2:../ProductionLine/res/h2";

    Connection conn = null;
    Statement st = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        String user = null;
        String pword = null;

        try{
            Class.forName(JDBC_DRIVER);     //register JDBC driver
            conn = DriverManager.getConnection(DB_URL);
            st = conn.createStatement();

            String quireOne = "CREATE TABLE Product;";
            String quireTwo = "DROP TABLE Product;";
            st.execute(quireOne);
            st.execute(quireTwo);
        }catch(Exception e){
            e.printStackTrace();
        }

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Production Line");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
