package babyshop;

import java.io.IOException;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pdf.PDF;

public class Babyshop extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/prijavaNaSistem.fxml"));
       // PDF.kreirajIzvjestajPoDobavljacuZaMjesec(new Date(119,01,21), new Date(119, 03, 24), 1);

    //  Parent root = FXMLLoader.load(getClass().getResource("/gui/pregledKorisnickogNaloga.fxml"));
        
       //Parent root = FXMLLoader.load(getClass().getResource("/gui/unosZaposlenog.fxml"));
        //primaryStage.getIcons().add(new Image("file:" + String.valueOf(Paths.get(System.getProperty("user.dir"), "film.png"))));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
