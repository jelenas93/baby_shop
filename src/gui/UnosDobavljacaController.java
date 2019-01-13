package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAODobavljac;
import dao.DAOMjesto;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dto.DTOMjesto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UnosDobavljacaController implements Initializable {


    @FXML
    private JFXTextField nazivTextField;

    @FXML
    private JFXTextField jibTextField;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXTextField adresaTextField;

    @FXML
    private JFXTextField telefonTextField;

    @FXML
    private JFXComboBox<DTOMjesto> mjestoComboBox;

    @FXML
    private FontAwesomeIconView dodajMjestoButton;

    @FXML
    private JFXButton sacuvajButton;

    @FXML
    private JFXButton otkaziButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popuniMjesta();
    }    
    
    private void popuniMjesta() {
        mjestoComboBox.getItems().clear();
        DAOMjesto daoMjesto = new DAOMjesto();
        ObservableList<DTOMjesto> listaMjesta;
        listaMjesta = daoMjesto.getMjesto();
        for (int i = 0; i < listaMjesta.size(); i++) {
            mjestoComboBox.getItems().add(listaMjesta.get(i));
        }
    }
    
    public void dodajMjesto(ActionEvent event) throws IOException{
     Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosMjesta.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
        popuniMjesta();
    }
    
    public void otkazi(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosProizvoda.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }
    
    public void sacuvaj(ActionEvent event) throws IOException {

        if ("".equals(nazivTextField.getText()) || "".equals(jibTextField.getText()) || "".equals(emailTextField.getText())
                || "".equals(mjestoComboBox.getSelectionModel().getSelectedItem())
                || "".equals(adresaTextField.getText()) || "".equals(telefonTextField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli podatke.");
        } else {

            DAODobavljac dAODobavljac = new DAODobavljac();
           // int postanskiBroj=Integer.parseInt(mjestoComboBox.getSelectionModel().getSelectedItem().split(", ")[1]);
            if (!dAODobavljac.dodajDobavljaca(mjestoComboBox.getSelectionModel().getSelectedItem().getPostanskiBroj(),
                    nazivTextField.getText(),emailTextField.getText(),adresaTextField.getText(),
                    telefonTextField.getText(), jibTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa proizvođača u bazu.");
            } else {
                Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosProizvoda.fxml"));
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene korisnikScena = new Scene(korisnikView);
                window.resizableProperty().setValue(Boolean.FALSE);
                window.setScene(korisnikScena);
                window.centerOnScreen();
                window.show();
            }
        }
    }
}
