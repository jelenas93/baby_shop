package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.DAOMjesto;
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
import javafx.scene.control.Alert;
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
        if (UnosProizvodjacaController.proizvodjac) {
            UnosProizvodjacaController.proizvodjac=false;
            Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosProizvodjaca.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene korisnikScena = new Scene(korisnikView);
            window.resizableProperty().setValue(Boolean.FALSE);
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        } else {
            Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosDobavljaca.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene korisnikScena = new Scene(korisnikView);
            window.resizableProperty().setValue(Boolean.FALSE);
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }
    }

    public void sacuvajStisak(ActionEvent event) throws IOException {
        
        if ("".equals(nazivTextField.getText()) || "".equals(opstinaTextField.getText())
                || "".equals(drzavaTextField.getText()) || "".equals(postanskiBrojTextField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli podatke.");
        } else {

            DAOMjesto daoMjesto = new DAOMjesto();
            try {
                if (!daoMjesto.upisUBazuMjesta(Integer.parseInt(postanskiBrojTextField.getText()),
                        nazivTextField.getText(), opstinaTextField.getText(), drzavaTextField.getText())) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa mjesta u bazu.");
                } else {
                    if (UnosProizvodjacaController.proizvodjac) {
            UnosProizvodjacaController.proizvodjac=false;
            Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosProizvodjaca.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene korisnikScena = new Scene(korisnikView);
            window.resizableProperty().setValue(Boolean.FALSE);
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        } else {
            Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosDobavljaca.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene korisnikScena = new Scene(korisnikView);
            window.resizableProperty().setValue(Boolean.FALSE);
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }
                }
            } catch (NumberFormatException e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Poštanski broj ne može biti slovo !");
            }

        }
    }

}
