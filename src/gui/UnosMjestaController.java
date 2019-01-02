package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UnosMjestaController implements Initializable {

     @FXML
    private JFXTextField nazivTextField;

    @FXML
    private JFXTextField opstinaTextField;

    @FXML
    private JFXTextField drzavaTextField;

    @FXML
    private JFXTextField postanskiBrojTextField;

    @FXML
    private JFXButton sacuvaButton;

    @FXML
    private JFXButton otkaziButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }    
    
    public void otkaziStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosProizvodjaca.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }
}
