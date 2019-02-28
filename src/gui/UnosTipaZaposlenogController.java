package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXTextField;
import dao.DAOTipZaposlenog;
import dto.DTOTipZaposlenog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class UnosTipaZaposlenogController implements Initializable {

    @FXML
    private JFXTextField nazivTipaZaposlenogTextField;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sacuvajButtonOnAction(ActionEvent event) {
        if ("".equals(nazivTipaZaposlenogTextField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli naziv tipa.");
        } else {

            DAOTipZaposlenog daotz = new DAOTipZaposlenog();

            if (!daotz.dodajTipZaposlenog(nazivTipaZaposlenogTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa u bazu.");
            } else {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, "", "Uspjesno ste upisali u bazu.");
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

}
