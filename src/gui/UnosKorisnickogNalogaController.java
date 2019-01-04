/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOKorisnickiNalog;
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
public class UnosKorisnickogNalogaController implements Initializable {

    @FXML
    private JFXTextField korisnickoImeTextField;
    @FXML
    private JFXTextField lozinkaTextField;
    @FXML
    private JFXComboBox<String> tipKorisnickogNalogaComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        tipKorisnickogNalogaComboBox.getItems().add("Admin");
        tipKorisnickogNalogaComboBox.getItems().add("Korisnik");

              
    }    

    @FXML
    private void sacuvajButtonOnAction(ActionEvent event) {
           if ("".equals(korisnickoImeTextField.getText())|| "".equals(lozinkaTextField.getText()) || 
                     "".equals(tipKorisnickogNalogaComboBox.getSelectionModel().getSelectedItem())){
           AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste ispravno unijeli podatke.");
         } else {
         
                 DAOKorisnickiNalog daoKorisnik=new DAOKorisnickiNalog();
             
                 if(!daoKorisnik.dodajKorisnickiNalog(korisnickoImeTextField.getText(),
                         lozinkaTextField.getText(),true,tipKorisnickogNalogaComboBox.getSelectionModel().getSelectedItem(),
                        1 ))
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
