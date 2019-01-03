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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UnosProizvodaController implements Initializable {

    @FXML
    private JFXComboBox<String> materijaliComboBox;

    @FXML
    private JFXComboBox<String> tipProizvodaComboBox;

    @FXML
    private JFXComboBox<String> JIBProizvodjacaComboBox;

    @FXML
    private VBox vbox = new VBox(10);

    @FXML
    private final Label duzinaLabel = new Label("Dužina: ");
    @FXML
    private final Label sirinaLabel = new Label("Širina: ");
    @FXML
    private final Label visinaLabel = new Label("Visina: ");
    @FXML
    private final Label velicinaLabel = new Label("Veličina: ");
    @FXML
    private final Label uzrastLabel = new Label("Uzrast: ");
    @FXML
    private final Label polLabel = new Label("Pol: ");
    @FXML
    private final Label bojaLabel = new Label("Boja: ");
    @FXML
    private final Label godisnjeDobaLabel = new Label("Godišnje doba: ");

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
        //sakrijPolja();
        popuniMaterijale();
        popuniProizvode();
        popuniProizvodjace();

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

    public void comboBoxTip() {
        vbox.getChildren().clear();
        provjeraKojiPodaciSePrikazuju();
    }

    private void provjeraKojiPodaciSePrikazuju() {
        if (!("".equals(tipProizvodaComboBox.getSelectionModel().getSelectedItem()))) {
            DAOGrupaProizvod daoGrupa = new DAOGrupaProizvod();
            DTOProizvodGrupa dtoProizvodGrupa = null;
            dtoProizvodGrupa = daoGrupa.getNazivProizvoda(tipProizvodaComboBox.getSelectionModel().getSelectedItem());
            if (dtoProizvodGrupa.isBoja()) {
                vbox.getChildren().add(bojaLabel);
            }
            if (dtoProizvodGrupa.isDuzina()) {
                vbox.getChildren().add(duzinaLabel);
            
            }
            if (dtoProizvodGrupa.isSirina()) {
                vbox.getChildren().add(sirinaLabel);
               
            }
            if (dtoProizvodGrupa.isVisina()) {
                vbox.getChildren().add(visinaLabel);
            }
            if (dtoProizvodGrupa.isGodisnjeDoba()) {
                vbox.getChildren().add(godisnjeDobaLabel);
                
            }
            if (dtoProizvodGrupa.isPol()) {
                vbox.getChildren().add(polLabel);
                
            }

            if (dtoProizvodGrupa.isUzrast()) {
                vbox.getChildren().add(uzrastLabel);
               
            }
            if (dtoProizvodGrupa.isVelicina()) {
                vbox.getChildren().add(velicinaLabel);
            }

        }
    }

}
