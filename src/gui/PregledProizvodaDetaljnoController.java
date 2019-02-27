package gui;

import dao.DAOProizvod;
import dao.DAOProizvodjac;
import dto.DTOProizvod;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tabele.TabelaDetaljanProizvod;

public class PregledProizvodaDetaljnoController implements Initializable {

    @FXML
    private TableView<TabelaDetaljanProizvod> tabela;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> barKod;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> sifra;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> naziv;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> proizvodjac;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, Integer> kolicina;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, Double> cijena;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, Double> duzina;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, Double> sirina;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, Double> visina;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, Integer> velicina;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, Integer> uzrast;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> pol;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> boja;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> godisnjeDoba;

    @FXML
    private Button nazad;

    @FXML
    private TextField nazivTextField;

    private boolean pretraga = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        postaviTabelu();
    }

    private void postaviTabelu() {
        barKod.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        proizvodjac.setCellValueFactory(new PropertyValueFactory<>("JIBProizvodjaca"));
        kolicina.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        cijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        duzina.setCellValueFactory(new PropertyValueFactory<>("duzina"));
        sirina.setCellValueFactory(new PropertyValueFactory<>("sirina"));
        visina.setCellValueFactory(new PropertyValueFactory<>("visina"));
        velicina.setCellValueFactory(new PropertyValueFactory<>("velicina"));
        uzrast.setCellValueFactory(new PropertyValueFactory<>("uzrast"));
        pol.setCellValueFactory(new PropertyValueFactory<>("pol"));
        boja.setCellValueFactory(new PropertyValueFactory<>("boja"));
        godisnjeDoba.setCellValueFactory(new PropertyValueFactory<>("godisnjeDoba"));
        tabela.setItems(getTabela());
    }

    private ObservableList<TabelaDetaljanProizvod> getTabela() {

        ObservableList<DTOProizvod> lista = new DAOProizvod().getProizvode();
        ObservableList<TabelaDetaljanProizvod> listaZaPrikaz = FXCollections.observableArrayList();
        List<TabelaDetaljanProizvod> listaMoja = new ArrayList<>();
        if (!pretraga) {
            pretraga = true;
            for (DTOProizvod proizvod : lista) {
                listaMoja.add(new TabelaDetaljanProizvod(proizvod.getBarkod(), proizvod.getSifra(), proizvod.getNaziv(),
                    new DAOProizvodjac().proizvodjacNaOsnovuJIB(proizvod.getJIBProizvodjaca()).getNaziv(), proizvod.getKolicina(), proizvod.getCijena(), proizvod.getDuzina(), proizvod.getSirina(),
                    proizvod.getVisina(), proizvod.getVelicina(), proizvod.getUzrast(), proizvod.getPol(), proizvod.getBoja(), proizvod.getGodisnjeDoba()));
            }
            for (TabelaDetaljanProizvod proizvod : listaMoja) {
                listaZaPrikaz.add(proizvod);
            }
            return listaZaPrikaz;
        } else {
            lista.clear();
            lista=new DAOProizvod().getProizvodPoNazivu(nazivTextField.getText());
            for (DTOProizvod proizvod : lista) {
                listaMoja.add(new TabelaDetaljanProizvod(proizvod.getBarkod(), proizvod.getSifra(), proizvod.getNaziv(),
                    new DAOProizvodjac().proizvodjacNaOsnovuJIB(proizvod.getJIBProizvodjaca()).getNaziv(), proizvod.getKolicina(), proizvod.getCijena(), proizvod.getDuzina(), proizvod.getSirina(),
                    proizvod.getVisina(), proizvod.getVelicina(), proizvod.getUzrast(), proizvod.getPol(), proizvod.getBoja(), proizvod.getGodisnjeDoba()));
            }
            for (TabelaDetaljanProizvod proizvod : listaMoja) {
                listaZaPrikaz.add(proizvod);
            }
            return listaZaPrikaz;
        }
    }
    
    public void nazivUnos(){
        nazivTextField.getOnInputMethodTextChanged();
        //nazivTextField.requestFocus();
        postaviTabelu();
      }

    public void dodajProizvod(ActionEvent event) throws IOException {
         Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosProizvoda.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }

}
