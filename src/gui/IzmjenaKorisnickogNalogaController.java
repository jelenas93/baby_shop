/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOKorisnickiNalog;
import dto.DTOKorisnickiNalog;
import dto.DTOKorisnikWrapper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tijana Lakic
 */
public class IzmjenaKorisnickogNalogaController implements Initializable {

    @FXML
    private  JFXTextField korisnickoImeTextField;
    @FXML
    private  JFXTextField lozinkaTextField;
    @FXML
    private JFXComboBox<String> tipKorisnickogNalogaComboBox;
    private DTOKorisnickiNalog nalog;
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
        
        nalog.setKorisnickoIme(korisnickoImeTextField.getText());
        nalog.setLozinka(lozinkaTextField.getText());
        nalog.setTipKorisnika(tipKorisnickogNalogaComboBox.getSelectionModel().getSelectedItem());
        DAOKorisnickiNalog.izmijeniNalog(nalog);
         Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
        
    }

    @FXML
    private void otkaziButtonOnAction(ActionEvent event) {
         Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
    }
    public void popuniPrikaz(DTOKorisnikWrapper korisnik){
    
        nalog=DAOKorisnickiNalog.getNalogById(korisnik.getIdNaloga());
        korisnickoImeTextField.setText(nalog.getKorisnickoIme());
        lozinkaTextField.setText(nalog.getLozinka());
        tipKorisnickogNalogaComboBox.getSelectionModel().select(nalog.getTipKorisnika()); 
    
    }
}
