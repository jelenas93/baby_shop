/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOMjesto;
import dao.DAOTipZaposlenog;
import dao.DAOZaposleni;
import dto.DTOMjesto;
import dto.DTOTipZaposlenog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Tijana Lakic
 */
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DAOTipZaposlenog daotz = new DAOTipZaposlenog();
        ObservableList<DTOTipZaposlenog> listaTipovaZaposlenog;
        listaTipovaZaposlenog = daotz.getTipZaposlenog();
        for (int i = 0; i < listaTipovaZaposlenog.size(); i++) {
            tipZaposlenogComboBox.getItems().add(listaTipovaZaposlenog.get(i));
        }
        DAOMjesto daoMjesto = new DAOMjesto();
        ObservableList<DTOMjesto> listaMjesta;
        listaMjesta = daoMjesto.getMjesto();
        for (int i = 0; i < listaMjesta.size(); i++) {
            mjestoComboBox.getItems().add(listaMjesta.get(i));
        }
    }    

    @FXML
    private void sacuvajButtonOnAction(ActionEvent event) {
        
             if ("".equals(jmbgTextField.getText())|| "".equals(imeTextField.getText()) || "".equals(prezimeTextField.getText()) ||
                     "".equals(plataTextField.getText()) || "".equals(mejlTextField.getText()) ||
                     "".equals(mjestoComboBox.getSelectionModel().getSelectedItem()) ||
                     "".equals(tipZaposlenogComboBox.getSelectionModel().getSelectedItem())){
           AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste ispravno unijeli podatke.");
         } else {
         
                 DAOZaposleni daoZaposleni=new DAOZaposleni();
             
                 if(!daoZaposleni.dodajZaposlenog(imeTextField.getText(),
                         prezimeTextField.getText(),jmbgTextField.getText(),
                         Double.parseDouble(plataTextField.getText()),mejlTextField.getText(),
                         mjestoComboBox.getSelectionModel().getSelectedItem().getPostanskiBroj(),
                         tipZaposlenogComboBox.getSelectionModel().getSelectedItem().getIdTipaZaposlenog()))
                 { AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa u bazu.");
                } else {
                    AlertHelper.showAlert(Alert.AlertType.INFORMATION, "", "Uspjesno ste upisali u bazu.");

                    System.exit(0);
                }
         }
    }

    @FXML
    private void OtkaziButtonOnAction(ActionEvent event) {
         System.exit(0);
    }
    
}
