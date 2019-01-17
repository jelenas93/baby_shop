/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.DAOKorisnickiNalog;
import dto.DTOKorisnickiNalog;
import dto.DTOKorisnikWrapper;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
    private  JFXPasswordField lozinkaTextField;
    @FXML
    private JFXComboBox<String> tipKorisnickogNalogaComboBox;
    private DTOKorisnickiNalog nalog;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tipKorisnickogNalogaComboBox.getItems().add(utils.Utils.PROPERTIES.getProperty("TIP_KORISNICKOG_NALOGA1"));
        tipKorisnickogNalogaComboBox.getItems().add(utils.Utils.PROPERTIES.getProperty("TIP_KORISNICKOG_NALOGA2"));
        tipKorisnickogNalogaComboBox.getItems().add(utils.Utils.PROPERTIES.getProperty("TIP_KORISNICKOG_NALOGA3"));

    }    

    @FXML
    private void sacuvajButtonOnAction(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        
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
        lozinkaTextField.setText(nalog.getLozinka().toString());
        String tip=tipKorisnickogNalogaComboBox.getItems().stream().filter(x -> nalog.getTipKorisnika().
                equals(x)).findAny().orElse(null);
            
        tipKorisnickogNalogaComboBox.getSelectionModel().select(tip); 
    
    }
}
