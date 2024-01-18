package cz.vse.ambr00.adventurasi.main;


import cz.vse.ambr00.adventurasi.Hra;
import cz.vse.ambr00.adventurasi.IHra;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        if(args.length > 0 &&args[0].equals("text")) {
           IHra hra = new Hra();
        } else {
            launch();
        }
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
        stage.setTitle("Adventura");
        stage.show();
    }
}