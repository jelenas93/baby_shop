package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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

public class UnosNovogTipaController implements Initializable {

    @FXML
    private JFXCheckBox polCheckBox;

    @FXML
    private JFXCheckBox velicinaCheckBox;

    @FXML
    private JFXCheckBox uzrastCheckBox;

    @FXML
    private JFXCheckBox duzinaCheckBox;

    @FXML
    private JFXCheckBox sirinaCheckBox;

    @FXML
    private JFXCheckBox visinaCheckBox;

    @FXML
    private JFXCheckBox godisnjeDobaCheckBox;

    @FXML
    private JFXButton sacuvajButton;

    @FXML
    private JFXButton otkaziButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void otkaziStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/dodavanjeProizvoda.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    public void sacuvajStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/dodavanjeProizvoda.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

}
