package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdministratorPocetnaFormaController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    public void dodajProizvod(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosProizvoda.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    public void dodajZaposlenog(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosZaposlenog.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    public void odjava(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/prijavaNaSistem.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    public void pregledKorisnickihNaloga(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/pregledKorisnickogNaloga.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    public void pregledProizvoda(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/pregledProizvoda.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    public void pregledZaposlenih(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/pregledZaposlenog.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }
    
}
