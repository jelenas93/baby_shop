package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOMjesto;
import dao.DAOProizvodjac;
import dto.DTOMjesto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javax.swing.border.LineBorder;

public class UnosProizvodjacaController implements Initializable {

    @FXML
    private JFXTextField JIBProizvodjacaTextField;

    @FXML
    private JFXTextField nazivTextField;

    @FXML
    private JFXComboBox<DTOMjesto> mjestoComboBox;

    @FXML
    private JFXButton dodajMjestoButton;

    @FXML
    private JFXButton sacuvajButton;

    @FXML
    private JFXButton otkaziButton;

    private void popuniMjesta() {
        mjestoComboBox.getItems().clear();
        DAOMjesto daoMjesto = new DAOMjesto();
        ObservableList<DTOMjesto> listaMjesta;
        listaMjesta = daoMjesto.getMjesto();
        for (int i = 0; i < listaMjesta.size(); i++) {
            mjestoComboBox.getItems().add(listaMjesta.get(i));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popuniMjesta();
    }

    public void dodajMjesto(ActionEvent event) throws IOException {
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

    public void otkaziStisak(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    public void sacuvajStisak(ActionEvent event) throws IOException {
        if ("".equals(JIBProizvodjacaTextField.getText()) || "".equals(nazivTextField.getText()) || mjestoComboBox.getSelectionModel().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli podatke.");
        } else {
            if (!Pattern.matches("[0-9]{13}", JIBProizvodjacaTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "JIB proizvodjaca mora biti broj od 13 cifara.");
            } else {
                DAOProizvodjac dAOProizvodjac = new DAOProizvodjac();
                if (!dAOProizvodjac.upisUBazuProizvodjaca(JIBProizvodjacaTextField.getText(), nazivTextField.getText(), mjestoComboBox.getSelectionModel().getSelectedItem().getPostanskiBroj())) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa proizvođača u bazu.");
                } else {
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.close();
                }
            }
        }
    }

}
