package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOKorisnickiNalog;
import dao.DAOMjesto;
import dao.DAOTipZaposlenog;
import dao.DAOZaposleni;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dto.DTOMjesto;
import dto.DTOTipZaposlenog;
import dto.DTOZaposleni;
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

public class IzmjenaZaposlenogController implements Initializable {

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

    private DTOZaposleni worker=new DTOZaposleni();
    ObservableList<DTOMjesto> listaMjesta;
    ObservableList<DTOTipZaposlenog> listaTipovaZaposlenog;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //  popuniComboBoxTipZaposlenog();
        // popuniComboBoxMjesta();
    }

    public void popuniComboBoxTipZaposlenog() {
        tipZaposlenogComboBox.getItems().clear();
        DAOTipZaposlenog daotz = new DAOTipZaposlenog();
        listaTipovaZaposlenog = daotz.getTipZaposlenog();
        for (int i = 0; i < listaTipovaZaposlenog.size(); i++) {
            tipZaposlenogComboBox.getItems().add(listaTipovaZaposlenog.get(i));
        }
    }

    public void popuniComboBoxMjesta() {
        mjestoComboBox.getItems().clear();
        DAOMjesto daoMjesto = new DAOMjesto();
        listaMjesta = daoMjesto.getMjesto();
        for (int i = 0; i < listaMjesta.size(); i++) {
            mjestoComboBox.getItems().add(listaMjesta.get(i));
        }
    }

    @FXML
    private void dodajMjesto(ActionEvent event) throws IOException {
        int counter=tipZaposlenogComboBox.getItems().size();
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosMjesta.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
        popuniComboBoxMjesta();
      if(tipZaposlenogComboBox.getItems().size()>counter){
        tipZaposlenogComboBox.getSelectionModel().selectLast();}    }

    @FXML
    private void sacuvajButtonOnAction(ActionEvent event) {
        if ("".equals(jmbgTextField.getText()) || "".equals(imeTextField.getText()) || "".equals(prezimeTextField.getText())
                || "".equals(plataTextField.getText()) || "".equals(mejlTextField.getText())
                || mjestoComboBox.getSelectionModel().isEmpty()
                || tipZaposlenogComboBox.getSelectionModel().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste ispravno unijeli podatke.");
        } else {
            worker.setIme(imeTextField.getText());
            worker.setPrezime(prezimeTextField.getText());
            worker.setIznosPlate(Double.parseDouble(plataTextField.getText()));
            worker.setJMBG(jmbgTextField.getText());
            worker.setMejl(mejlTextField.getText());
            worker.setPostanskiBroj(mjestoComboBox.getSelectionModel().getSelectedItem().getPostanskiBroj());
            worker.setIdTipZaposlenog(tipZaposlenogComboBox.getSelectionModel().getSelectedItem().getIdTipaZaposlenog());
            worker.toString();

            if (!DAOZaposleni.izmjeniZaposlenog(worker)) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa u bazu.");
            } else {
                int idZap=DAOZaposleni.getIdZaposleniByJMBG(worker.getJMBG());
                DAOKorisnickiNalog.izmijeniTipKorisnika(tipZaposlenogComboBox.getSelectionModel().getSelectedItem().getNazivTipa(),idZap );
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
        }
    }

    @FXML
    private void otkaziButtonOnAction(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    public void popuniPrikaz(DTOZaposleni zaposleni) {

        this.worker = zaposleni;

        imeTextField.setText(worker.getIme());
        prezimeTextField.setText(worker.getPrezime());
        plataTextField.setText("" + worker.getIznosPlate());
        jmbgTextField.setText(worker.getJMBG());
        mejlTextField.setText(worker.getMejl());
        popuniComboBoxTipZaposlenog();
        popuniComboBoxMjesta();
        DTOMjesto m = listaMjesta.stream().filter(mjesto -> worker.getPostanskiBroj() == (mjesto.getPostanskiBroj())).findAny().orElse(null);
        mjestoComboBox.getSelectionModel().select(m);
        DTOTipZaposlenog t = listaTipovaZaposlenog.stream().filter(tip -> worker.getIdTipZaposlenog() == tip.getIdTipaZaposlenog()).findAny().orElse(null);
        tipZaposlenogComboBox.getSelectionModel().select(t);

    }

    @FXML
    private void dodajTip(ActionEvent event) throws IOException {
        int counter=tipZaposlenogComboBox.getItems().size();
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosTipaZaposlenog.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
        popuniComboBoxTipZaposlenog();
        if(tipZaposlenogComboBox.getItems().size()>counter){
        tipZaposlenogComboBox.getSelectionModel().selectLast();}
    }

}
