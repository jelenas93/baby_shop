/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAODobavljac;
import dao.DAONarudzbenica;
import dto.DTODobavljac;
import dto.DTONarudzbenica;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tabele.TabelaKalkulacija;


/**
 * FXML Controller class
 *
 * @author Jovana Trkulja
 */
public class KalkulacijaController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    
    
 
    @FXML
    private TableView<TabelaKalkulacija> kalkulacija;

    @FXML
    private TableColumn<TabelaKalkulacija, String> barKod;

    @FXML
    private TableColumn<TabelaKalkulacija, String> sifra;

    @FXML
    private TableColumn<TabelaKalkulacija,String> naziv;

    @FXML
    private TableColumn<TabelaKalkulacija, Integer> kolicina;

    @FXML
    private TableColumn<TabelaKalkulacija, String> jedMjere;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> fakturnaCijena;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> rabat;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> FakturnaVrNaRabat;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> nabavnaCijena;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> nabavnaVrijednost;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> marza;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> marzaIznos;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> vrijednostBezPdv;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> stopa;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> pdv;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> prodajnaVrijednost;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> prodajnaCijena;

    @FXML
    private ComboBox<String> DobavljacComboBox;

    @FXML
    private ComboBox<String> NarudzbaComboBox;

    @FXML
    private TextField FakturnaCijenaTextField;

    @FXML
    private TextField RabatTextField;

    @FXML
    private TextField KolicinaTextField;

    @FXML
    private Button sacuvajButton;
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         DAODobavljac daod = new DAODobavljac();
        ObservableList<DTODobavljac> listaDobavljaca;
        listaDobavljaca = daod.dobavljaci();
        for (int i = 0; i < listaDobavljaca.size(); i++) {
            DobavljacComboBox.getItems().add(listaDobavljaca.get(i).getNaziv());
            
        }
         postaviTabelu();
        
    }  
    
    private void postaviTabelu() {  
     sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
     barKod.setCellValueFactory(new PropertyValueFactory<>("barKod"));
        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        kolicina.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        jedMjere.setCellValueFactory(new PropertyValueFactory<>("jedMjere"));
        fakturnaCijena.setCellValueFactory(new PropertyValueFactory<>("fakturnaCijena"));
        rabat.setCellValueFactory(new PropertyValueFactory<>("rabat"));
        FakturnaVrNaRabat.setCellValueFactory(new PropertyValueFactory<>("FakturnaVrNaRabat"));
        nabavnaCijena.setCellValueFactory(new PropertyValueFactory<>("nabavnaCijena"));
        nabavnaVrijednost.setCellValueFactory(new PropertyValueFactory<>("nabavnaVrijednost"));
        marza.setCellValueFactory(new PropertyValueFactory<>("marza"));
       marzaIznos.setCellValueFactory(new PropertyValueFactory<>("marzaIznos"));
        vrijednostBezPdv.setCellValueFactory(new PropertyValueFactory<>("vrijednostBezPdv")); 
        stopa.setCellValueFactory(new PropertyValueFactory<>("stopa"));
       pdv.setCellValueFactory(new PropertyValueFactory<>("pdv"));
        prodajnaVrijednost.setCellValueFactory(new PropertyValueFactory<>("prodajnaVrijednost")); 
        prodajnaCijena.setCellValueFactory(new PropertyValueFactory<>("prodajnaCijena"));
       
        
    DobavljacComboBox.getSelectionModel().selectedItemProperty().addListener((v, staraVrijednost, novaVrijednost) -> popuniNarudzbeComboBox());
    
    
    }  
    private void popuniNarudzbeComboBox ()  {   
        // Jelena napisati funkciju koja vraca Stringove narudzbi od tog Dobavljaca i puno Combo Box s njima
        String imeDobavljaca = DobavljacComboBox.getSelectionModel().getSelectedItem().toString();
        DAODobavljac daoDobavljac=new DAODobavljac();
DTODobavljac dtoDobavljac=daoDobavljac.getDobavljacPoNazivu(imeDobavljaca);
DAONarudzbenica daoNarudzbenica=new DAONarudzbenica();
ObservableList<DTONarudzbenica> narudzbenice=daoNarudzbenica.getNarudzbenicePoDobavljacu(dtoDobavljac.getIdDobavljaca());

  NarudzbaComboBox.getItems().clear();
      
        for (int i = 0; i < narudzbenice.size(); i++) {
            NarudzbaComboBox.getItems().add(narudzbenice.get(i).getDatumNarudzbenice()+"");
            System.out.println(narudzbenice.get(i).getDatumNarudzbenice()+"");
        }
        
     
      NarudzbaComboBox.getSelectionModel().selectedItemProperty().addListener((v, staraVrijednost, novaVrijednost) ->  kalkulacija.setItems(getTabela()));   
    
    
    }
    
    private ObservableList<TabelaKalkulacija> getTabela() {  
        
        
       
         kalkulacija.getItems().clear();

        ObservableList<TabelaKalkulacija> listaZaPrikaz = FXCollections.observableArrayList();
        List<TabelaKalkulacija> listaMoja = new ArrayList<>();
/*
        DAODobavljac daod = new DAODobavljac();
        DTODobavljac dtod = daod.getDobavljacPoNazivu(imeDobavljacaComboBox.getSelectionModel().getSelectedItem());
        int IdDobavljaca = dtod.getIdDobavljaca();
        

        DAOProizvod daop = new DAOProizvod();
        ObservableList<DTOProizvod> lista = daop.getSveProizvodeOdDobavljaca(IdDobavljaca);
        for (DTOProizvod proizvod : lista) {
            System.out.println(proizvod.getBarkod());
            listaMoja.add(new TabelaNarudzba(proizvod.getSifra(), proizvod.getBarkod(), proizvod.getNaziv(), proizvod.getCijena(),
                    proizvod.getKolicina()
            ));
        }
        for (TabelaNarudzba proizvod : listaMoja) {
            listaZaPrikaz.add(proizvod);
        }
*/
        return listaZaPrikaz;
        
    
        
    
//    return null;
    
    }
}


