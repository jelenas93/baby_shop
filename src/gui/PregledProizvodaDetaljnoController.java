/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAOProizvod;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        ObservableList<DTOProizvod> lista =new DAOProizvod().getProizvode();

        List<TabelaDetaljanProizvod> listaMoja = new ArrayList<>();
        for (DTOProizvod proizvod: lista) {
            listaMoja.add(new TabelaDetaljanProizvod(proizvod.getBarkod(), proizvod.getSifra(),  proizvod.getNaziv(), 
            proizvod.getJIBProizvodjaca(), proizvod.getKolicina(), proizvod.getCijena(), proizvod.getDuzina(), proizvod.getSirina(), 
                    proizvod.getVisina(), proizvod.getVelicina(), proizvod.getUzrast(), proizvod.getPol(), proizvod.getBoja(), proizvod.getGodisnjeDoba()));
        }
        ObservableList<TabelaDetaljanProizvod> listaZaPrikaz = FXCollections.observableArrayList();
        for (TabelaDetaljanProizvod proizvod : listaMoja) {
            listaZaPrikaz.add(proizvod);
        }
        return listaZaPrikaz;
    }
    
    public void nazad(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/pregledProizvoda.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }
    
    
}
