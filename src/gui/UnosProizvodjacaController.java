package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class UnosProizvodjacaController implements Initializable {

      @FXML
    private JFXTextField JIBProizvodjacaTextField;

    @FXML
    private JFXTextField nazivTextField;

    @FXML
    private JFXComboBox<Integer> postanskiBrojComboBox;

    @FXML
    private JFXButton dodajMjestoButton;

    @FXML
    private JFXButton sacuvajButton;

    @FXML
    private JFXButton otkaziButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
