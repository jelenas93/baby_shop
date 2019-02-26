package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.DAOKorisnickiNalog;
import dao.DAOMjesto;
import dao.DAOTipZaposlenog;
import dao.DAOZaposleni;
import dto.DTOMjesto;
import dto.DTOTipZaposlenog;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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

public class UnosZaposlenogController implements Initializable {

    @FXML
    private JFXComboBox<DTOTipZaposlenog> tipZaposlenogComboBox;
    @FXML
    private JFXTextField jmbgTextField;
    @FXML
    private JFXTextField imeTextField;
    @FXML
    private JFXTextField prezimeTextField;
    @FXML
    private JFXTextField plataTextField;
    @FXML
    private JFXTextField mejlTextField;
    @FXML
    private JFXComboBox<DTOMjesto> mjestoComboBox;
    @FXML
    private JFXTextField korisnickoImeTextField;
    @FXML
    private JFXPasswordField lozinkaTextField;
    @FXML
    private JFXComboBox<String> tipKorisnickogNalogaComboBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //tipKorisnickogNalogaComboBox.getItems().add(utils.Utils.PROPERTIES.getProperty("TIP_KORISNICKOG_NALOGA1"));
       // tipKorisnickogNalogaComboBox.getItems().add(utils.Utils.PROPERTIES.getProperty("TIP_KORISNICKOG_NALOGA2"));
        //tipKorisnickogNalogaComboBox.getItems().add(utils.Utils.PROPERTIES.getProperty("TIP_KORISNICKOG_NALOGA3"));

        popuniComboBoxTipZaposlenog();
        popuniComboBoxMjesta();
    }

    public void popuniComboBoxTipZaposlenog() {
        tipZaposlenogComboBox.getItems().clear();
        DAOTipZaposlenog daotz = new DAOTipZaposlenog();
        ObservableList<DTOTipZaposlenog> listaTipovaZaposlenog;
        listaTipovaZaposlenog = daotz.getTipZaposlenog();
        for (int i = 0; i < listaTipovaZaposlenog.size(); i++) {
            tipZaposlenogComboBox.getItems().add(listaTipovaZaposlenog.get(i));
        }
    }

    public void popuniComboBoxMjesta() {
        mjestoComboBox.getItems().clear();
        DAOMjesto daoMjesto = new DAOMjesto();
        ObservableList<DTOMjesto> listaMjesta;
        listaMjesta = daoMjesto.getMjesto();
        for (int i = 0; i < listaMjesta.size(); i++) {
            mjestoComboBox.getItems().add(listaMjesta.get(i));
        }

    }

    @FXML
    private void sacuvajButtonOnAction(ActionEvent event) throws IOException, NoSuchAlgorithmException {

        if ("".equals(jmbgTextField.getText()) || "".equals(imeTextField.getText()) || "".equals(prezimeTextField.getText())
                || "".equals(plataTextField.getText()) || "".equals(mejlTextField.getText())
                || mjestoComboBox.getSelectionModel().isEmpty()
                || tipZaposlenogComboBox.getSelectionModel().isEmpty() || "".equals(korisnickoImeTextField.getText())
                || "".equals(lozinkaTextField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste ispravno unijeli podatke.");
        } else if (!Pattern.matches("[0-9]{13}", jmbgTextField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Neispravan JMBG!");}
        else {
            DAOZaposleni daoZaposleni = new DAOZaposleni();
            int idZaposlenog = daoZaposleni.dodajZaposlenog(imeTextField.getText(),
                    prezimeTextField.getText(), jmbgTextField.getText(),
                    Double.parseDouble(plataTextField.getText()), mejlTextField.getText(),
                    mjestoComboBox.getSelectionModel().getSelectedItem().getPostanskiBroj(),
                    tipZaposlenogComboBox.getSelectionModel().getSelectedItem().getIdTipaZaposlenog());

            if (idZaposlenog == -1) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom dodavanja zaposlenog.");
            } else {
                DAOKorisnickiNalog daoKorisnik = new DAOKorisnickiNalog();
                boolean nalog = daoKorisnik.dodajKorisnickiNalog(korisnickoImeTextField.getText(),
                        lozinkaTextField.getText(), true, tipZaposlenogComboBox.getSelectionModel().getSelectedItem().getNazivTipa(),
                        idZaposlenog);
                if (!nalog) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa u bazu.");

                } else {
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.close();
                }
            }
        }
    }

    @FXML
    private void otkaziButtonOnAction(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    public void dodajMjesto(ActionEvent event) throws IOException {

        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosMjesta.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
        popuniComboBoxMjesta();
        mjestoComboBox.getSelectionModel().selectLast();
    }

    @FXML
    public void dodajZaposlenog(ActionEvent event) throws IOException {

        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosTipaZaposlenog.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
        popuniComboBoxTipZaposlenog();
    }
}
