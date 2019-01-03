package gui;

import com.jfoenix.controls.JFXComboBox;
import dao.DAOMaterijal;
import dao.DAOGrupaProizvod;
import dao.DAOProizvodjac;
import dto.DTOMaterijal;
import dto.DTOProizvodGrupa;
import dto.DTOProizvodjac;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UnosProizvodaController implements Initializable {

    @FXML
    private JFXComboBox<String> materijaliComboBox;

    @FXML
    private JFXComboBox<String> tipProizvodaComboBox;

    @FXML
    private JFXComboBox<String> JIBProizvodjacaComboBox;
    
    @FXML
    private Label duzinaLabel,sirinaLabel,visinaLabel,velicinaLabel,uzrastLabel,polLabel,bojaLabel,godisnjeDobaLabel;
    
    private void popuniMaterijale() {
        DAOMaterijal daoMaterijal = new DAOMaterijal();
        ObservableList<DTOMaterijal> listaMaterijala;
        listaMaterijala = daoMaterijal.getMaterijal();
        for (int i = 0; i < listaMaterijala.size(); i++) {
            materijaliComboBox.getItems().add(listaMaterijala.get(i).getNaziv());
        }
    }

    private void popuniProizvodjace() {
        DAOProizvodjac dAOProizvodjac = new DAOProizvodjac();
        ObservableList<DTOProizvodjac> listaProizvodjaca;
        listaProizvodjaca = dAOProizvodjac.getProizvodjace();
        for (int i = 0; i < listaProizvodjaca.size(); i++) {
            JIBProizvodjacaComboBox.getItems().add(listaProizvodjaca.get(i).getNaziv() + ", " + listaProizvodjaca.get(i).getJIBProizvodjaca());
        }
    }

    private void popuniProizvode() {
        DAOGrupaProizvod daoTip = new DAOGrupaProizvod();
        ObservableList<DTOProizvodGrupa> listaProizvoda;
        listaProizvoda = daoTip.getGrupeProizvoda();
        for (int i = 0; i < listaProizvoda.size(); i++) {
            tipProizvodaComboBox.getItems().add(listaProizvoda.get(i).getNazivTipaProizvoda());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sakrijPolja();
        popuniMaterijale();
        popuniProizvode();
        popuniProizvodjace();

    }

    private void sakrijPolja(){
        duzinaLabel.setVisible(false);
        sirinaLabel.setVisible(false);
        visinaLabel.setVisible(false);
        velicinaLabel.setVisible(false);
        uzrastLabel.setVisible(false);
        polLabel.setVisible(false);
        bojaLabel.setVisible(false);
        godisnjeDobaLabel.setVisible(false);
        
                
    }
   
    public void dodajProizvodjaca(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosProizvodjaca.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    public void dodajTipStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosGrupaProizvod.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }
    
    public void comboBoxTip(){
        provjeraKojiPodaciSePrikazuju();
    }
   
    private void provjeraKojiPodaciSePrikazuju(){
        tipProizvodaComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(!("".equals(tipProizvodaComboBox.getSelectionModel().getSelectedItem()))){
                DAOGrupaProizvod daoGrupa=new DAOGrupaProizvod();
                DTOProizvodGrupa dtoProizvodGrupa=null;
                dtoProizvodGrupa=daoGrupa.getNazivProizvoda(tipProizvodaComboBox.getSelectionModel().getSelectedItem());
                if(dtoProizvodGrupa.isBoja())
                    bojaLabel.setVisible(true);
                else if(dtoProizvodGrupa.isDuzina())
                    duzinaLabel.setVisible(true);
                else if(dtoProizvodGrupa.isGodisnjeDoba())
                    godisnjeDobaLabel.setVisible(true);
                else if(dtoProizvodGrupa.isPol())
                    polLabel.setVisible(true);
                else if(dtoProizvodGrupa.isSirina())
                    sirinaLabel.setVisible(true);
                else if(dtoProizvodGrupa.isUzrast())
                    uzrastLabel.setVisible(true);
                else if(dtoProizvodGrupa.isVelicina())
                    velicinaLabel.setVisible(true);
                else if(dtoProizvodGrupa.isVisina())
                    visinaLabel.setVisible(true);
                            
                   
                
            }
            
        });
    }
    
}
