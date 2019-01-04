
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Tijana Lakic
 */
public class UnosTipaZaposlenogController implements Initializable {

    @FXML
    private JFXTextField nazivTipaZaposlenogTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sacuvajButtonOnAction(ActionEvent event) {
         if ("".equals(nazivTipaZaposlenogTextField.getText())){
           AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli naziv tipa.");
         } else {
         
             DAOTipZaposlenog daotz=new DAOTipZaposlenog();
             
                 if(!daotz.dodajTipZaposlenog(nazivTipaZaposlenogTextField.getText()))
                 { AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa u bazu.");
                } else {
                    AlertHelper.showAlert(Alert.AlertType.INFORMATION, "", "Uspjesno ste upisali u bazu.");

                    System.exit(0);
                }
         }
    }

    @FXML
    private void otkaziButtonOnAction(ActionEvent event) {
                System.exit(0);

    }
    
}
