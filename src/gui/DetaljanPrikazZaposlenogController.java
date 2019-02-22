package gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOMjesto;
import dao.DAOTipZaposlenog;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dto.DTOMjesto;
import dto.DTOTipZaposlenog;
import dto.DTOZaposleni;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DetaljanPrikazZaposlenogController implements Initializable {

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
    private JFXTextField tipZaposlenogTextField;
    @FXML
    private JFXTextField mjestoTextField;

    private DTOZaposleni zaposleni;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void nazadButtonOnAction(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    public void popuniPrikaz(DTOZaposleni zaposleni) {
        this.zaposleni = zaposleni;
        imeTextField.setText(zaposleni.getIme());
        prezimeTextField.setText(zaposleni.getPrezime());
        plataTextField.setText("" + zaposleni.getIznosPlate());
        jmbgTextField.setText(zaposleni.getJMBG());
        mejlTextField.setText(zaposleni.getMejl());
        mjestoTextField.setText(DAOMjesto.getMjestoByPostanskiBroj(zaposleni.getPostanskiBroj()).toString());
        tipZaposlenogTextField.setText(DAOTipZaposlenog.getTipZaposlenogById(zaposleni.getIdTipZaposlenog()).toString());
    }

}
