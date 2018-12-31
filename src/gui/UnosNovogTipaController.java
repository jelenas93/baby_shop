package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOTipProizvoda;
import dto.DTOTipProizvoda;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private JFXTextField tipProizvodaTextField;

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

        if ("".equals(tipProizvodaTextField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli tip proizvoda !");
        } else {
            if (provjeraTipaDaLiPostoji(tipProizvodaTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, "", "Izabrani tip proizvoda vec postoji !");
            } else {
                DAOTipProizvoda daoTip=new DAOTipProizvoda();
              //  DTOTipProizvoda dtoTip=new DTOTipProizvoda(0, tipProizvodaTextField.getText().toUpperCase());
            //    dtoTip.setNazivTipaProizvoda(tipProizvodaTextField.getText().toUpperCase());
              //  System.out.println(dtoTip.getNazivTipaProizvoda());
                if(daoTip.upisUBazu(tipProizvodaTextField.getText().toUpperCase())){
                    AlertHelper.showAlert(Alert.AlertType.INFORMATION, "", "Super !");
                }
                
                Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/dodavanjeProizvoda.fxml"));
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene korisnikScena = new Scene(korisnikView);
                window.resizableProperty().setValue(Boolean.FALSE);
                window.setScene(korisnikScena);
                window.centerOnScreen();
                window.show();
            }
        }
    }

    private boolean provjeraTipaDaLiPostoji(String ime) {

        DAOTipProizvoda daoTip = new DAOTipProizvoda();
        ObservableList<DTOTipProizvoda> listaProizvoda;
        listaProizvoda = daoTip.getTipoveProizvoda();
        List<String> tipovi = new ArrayList<>();
        for (int i = 0; i < listaProizvoda.size(); i++) {
            tipovi.add(listaProizvoda.get(i).getNazivTipaProizvoda());
        }
        if (tipovi.contains(ime.toUpperCase())) {
            return true;
        }
        return false;

    }
}