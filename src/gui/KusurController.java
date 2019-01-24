package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    @FXML
    void nazadStisak(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    void racunajKusur(ActionEvent event) {
        double kusur=0.0;
        kusur=Double.parseDouble(uplacenoTextField.getText())-KasaController.ukupnoZaProsljedjivanje;
        kusurLabel.setText(String.format("%.2f", kusur));
       // KasaController.ukupnoZaProsljedjivanje=0.0;
    }

}
