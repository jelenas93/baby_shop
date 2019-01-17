/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marija
 */
public class KusurController implements Initializable {

    @FXML
    private TextField uplacenoTextField;

    @FXML
    private Label iznosRacunaLabel;
    
    @FXML
    private Label kusurLabel;

    @FXML
    private Button nazadButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iznosRacunaLabel.setText(String.format("%.2f", KasaController.ukupnoZaProsljedjivanje));
    }    
    
    public void nazadStisak(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    
    public void racunajKusur(ActionEvent event) throws IOException {
        double kusur=0.0;
        kusur=Double.parseDouble(uplacenoTextField.getText())-KasaController.ukupnoZaProsljedjivanje;
        kusurLabel.setText(String.format("%.2f", kusur));
        KasaController.ukupnoZaProsljedjivanje=0.0;
    }
    
}
