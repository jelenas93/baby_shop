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
import javafx.stage.Stage;

public class PoslovodjaController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
    @FXML
    private void odjavaStisak(ActionEvent event) throws IOException {
       System.exit(0);
    }
    
}
