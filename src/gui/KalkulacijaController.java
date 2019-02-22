package gui;

import babyshop.AlertHelper;
import dao.DAODobavljac;
import dao.DAOKalkulacija;
import dao.DAONarudzbenica;
import dao.DAOProizvod;
import dao.DAOStavkaKalkulacije;
import dao.DAOStavkaNarudzbe;
import dto.DTODobavljac;
import dto.DTONarudzbenica;
import dto.DTOProizvod;
import dto.DTOStavkaKalkulacije;
import dto.DTOStavkaNarudzbe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pdf.PDF;
import tabele.TabelaKalkulacija;

public class KalkulacijaController implements Initializable {

    @FXML
    private TableView<TabelaKalkulacija> kalkulacija;

    @FXML
    private TableColumn<TabelaKalkulacija, String> barKod;

    @FXML
    private TableColumn<TabelaKalkulacija, String> sifra;

    @FXML
    private TableColumn<TabelaKalkulacija, String> naziv;

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
    private TableColumn<TabelaKalkulacija, Integer> stopa;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> pdv;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> prodajnaVrijednost;

    @FXML
    private TableColumn<TabelaKalkulacija, Double> prodajnaCijena;

    @FXML
    private ComboBox<String> dobavljacComboBox;

    @FXML
    private ComboBox<String> narudzbaComboBox;

    @FXML
    private TextField fakturnaCijenaTextField;

    @FXML
    private TextField rabatTextField;

    @FXML
    private TextField kolicinaTextField;

    @FXML
    private Label proizvodLabel;

    @FXML
    private TextField marzaTextField;

    int idDobavljaca;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DAODobavljac daod = new DAODobavljac();
        ObservableList<DTODobavljac> listaDobavljaca;
        listaDobavljaca = daod.getDobavljace();
        for (int i = 0; i < listaDobavljaca.size(); i++) {
            dobavljacComboBox.getItems().add(listaDobavljaca.get(i).getNaziv());
        }
        kalkulacija.getItems().clear();
        narudzbaComboBox.getItems().clear();
    }

    public void postaviTabelu() {
        sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        barKod.setCellValueFactory(new PropertyValueFactory<>("barKod"));
        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        kolicina.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        jedMjere.setCellValueFactory(new PropertyValueFactory<>("jedMjere"));
        fakturnaCijena.setCellValueFactory(new PropertyValueFactory<>("fakturnaCijena"));
        rabat.setCellValueFactory(new PropertyValueFactory<>("rabat"));
        FakturnaVrNaRabat.setCellValueFactory(new PropertyValueFactory<>("fakturnaVrijednostNaRabat"));
        nabavnaCijena.setCellValueFactory(new PropertyValueFactory<>("nabavnaCijena"));
        nabavnaVrijednost.setCellValueFactory(new PropertyValueFactory<>("nabavnaVrijednost"));
        marza.setCellValueFactory(new PropertyValueFactory<>("marza"));
        marzaIznos.setCellValueFactory(new PropertyValueFactory<>("marzaIznos"));
        vrijednostBezPdv.setCellValueFactory(new PropertyValueFactory<>("vrijednostBezPdv"));
        stopa.setCellValueFactory(new PropertyValueFactory<>("stopa"));
        pdv.setCellValueFactory(new PropertyValueFactory<>("pdv"));
        prodajnaVrijednost.setCellValueFactory(new PropertyValueFactory<>("prodajnaVrijednost"));
        prodajnaCijena.setCellValueFactory(new PropertyValueFactory<>("prodajnaCijena"));
        kalkulacija.setItems(getTabela());
    }

    public void popuniNarudzbeComboBox() {
        narudzbaComboBox.getItems().clear();
        String imeDobavljaca = dobavljacComboBox.getSelectionModel().getSelectedItem();
        DAODobavljac daoDobavljac = new DAODobavljac();
        DTODobavljac dtoDobavljac = daoDobavljac.getDobavljacPoNazivu(imeDobavljaca);
        idDobavljaca = dtoDobavljac.getIdDobavljaca();
        DAONarudzbenica daoNarudzbenica = new DAONarudzbenica();
        ObservableList<DTONarudzbenica> narudzbenice = daoNarudzbenica.getNekalkulisaneNarudzbenicePoDobavljacu(dtoDobavljac.getIdDobavljaca());

        for (int i = 0; i < narudzbenice.size(); i++) {
            narudzbaComboBox.getItems().add(narudzbenice.get(i).getIdNarudzbenice() + " " + narudzbenice.get(i).getDatumNarudzbenice());
        }

    }

    private ObservableList<TabelaKalkulacija> getTabela() {
        kalkulacija.getItems().clear();

        ObservableList<TabelaKalkulacija> listaZaPrikaz = FXCollections.observableArrayList();
        List<TabelaKalkulacija> listaMoja = new ArrayList<>();

        DAODobavljac daod = new DAODobavljac();
        DTODobavljac dtod = daod.getDobavljacPoNazivu(dobavljacComboBox.getSelectionModel().getSelectedItem());
        int IdDobavljaca = dtod.getIdDobavljaca();

        DAONarudzbenica daoNarudzbenica = new DAONarudzbenica();
        DAOStavkaNarudzbe daoStavkaNarudzbe = new DAOStavkaNarudzbe();
        ArrayList<DTOStavkaNarudzbe> listaStavkinarudzbe = daoStavkaNarudzbe.stavkeNaNaruzbenici(Integer.parseInt(narudzbaComboBox.getSelectionModel().getSelectedItem().split(" ")[0]));
        DAOProizvod daoProizvod = new DAOProizvod();
        for (DTOStavkaNarudzbe stavka : listaStavkinarudzbe) {
            DTOProizvod dtoProizvod = daoProizvod.getProizvodPoId(stavka.getIdProizvoda());
            listaMoja.add(new TabelaKalkulacija(dtoProizvod.getSifra(), dtoProizvod.getBarkod(), dtoProizvod.getNaziv(), stavka.getKolicina(), "KOM",dtoProizvod.getIdProizvoda()));
        }

        for (TabelaKalkulacija kalkulacija : listaMoja) {
            listaZaPrikaz.add(kalkulacija);
        }

        return listaZaPrikaz;
    }

    private boolean provjeraCijene() {
        if (!kalkulacija.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            try {
                Double.parseDouble(fakturnaCijenaTextField.getText());
                rabatTextField.requestFocus();
                return true;
            } catch (NumberFormatException e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Cijena mora biti broj.");
                fakturnaCijenaTextField.setText("");
            }
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali proizvod.");
        }
        return false;
    }

    public void cijenaUnos() {
        boolean fleg = provjeraCijene();
    }

    private boolean provjeraRabata() {
        if (!kalkulacija.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            try {
                Integer.parseInt(rabatTextField.getText());
                marzaTextField.requestFocus();
                return true;
            } catch (NumberFormatException e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Rabat mora biti cijeli broj.");
                rabatTextField.setText("");
            }
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali proizvod.");
        }
        return false;
    }

    public void rabatUnos() {
        boolean fleg = provjeraRabata();
    }

    private boolean provjeraMarze() {
        if (!kalkulacija.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            try {
                Double.parseDouble(marzaTextField.getText());
                return true;
            } catch (NumberFormatException e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Marža mora biti broj.");
                marzaTextField.setText("");
            }
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali proizvod.");
        }
        return false;
    }

    public void marzaUnos() {
        if (provjeraMarze()) {
            TabelaKalkulacija red = kalkulacija.getSelectionModel().getSelectedItem();
            red.setFakturnaCijena(Double.parseDouble(fakturnaCijenaTextField.getText()));
            red.setRabat(Integer.parseInt(rabatTextField.getText()));
            red.setFakturnaVrijednostNaRabat(red.getKolicina() * red.getFakturnaCijena() - (red.getKolicina() * red.getFakturnaCijena()) * Integer.parseInt(rabatTextField.getText()) / 100);
            red.setNabavnaCijena(red.getFakturnaCijena() - red.getFakturnaCijena() * Integer.parseInt(rabatTextField.getText()) / 100);
            red.setNabavnaVrijednost(red.getNabavnaCijena() * red.getKolicina());
            red.setMarza(Double.parseDouble(marzaTextField.getText()));
            red.setMarzaIznos(red.getNabavnaVrijednost()* Double.parseDouble(marzaTextField.getText()) / 100);
            red.setVrijednostBezPdv(red.getNabavnaVrijednost() + red.getMarzaIznos());
            red.setPdv(red.getVrijednostBezPdv() * red.getStopa() / 100);
            double marzaPostotak = red.getNabavnaCijena() + red.getNabavnaCijena() * red.getMarza() / 100;
            double prodajnaCijena = marzaPostotak + marzaPostotak * red.getStopa() / 100;
            red.setProdajnaCijena(prodajnaCijena);
            red.setProdajnaVrijednost(red.getKolicina() * prodajnaCijena);
            kalkulacija.refresh();
            cistiPolja();
        }
    }

    private void cistiPolja() {
        proizvodLabel.setText("");
        fakturnaCijenaTextField.setText("");
        rabatTextField.setText("");
        marzaTextField.setText("");
    }

    public void labelUnos() {
        if (!kalkulacija.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            proizvodLabel.setText(kalkulacija.getSelectionModel().getSelectedItem().getNaziv());
        }
    }

    public void sacuvajStisak() {
        if (kalkulacija.getItems().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Kalkulacija je prazna.");
        } else {
            double ukupno = 0;
            for (int i = 0; i < kalkulacija.getItems().size(); i++) {
                ukupno += kalkulacija.getItems().get(i).getProdajnaVrijednost();
            }
            DAOKalkulacija daoKalkulacija = new DAOKalkulacija();
            if (!daoKalkulacija.upisiKalkulacijuUBazu(new java.sql.Date(new Date().getTime()),
                    idDobavljaca, 1, ukupno)) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greska pru upisu kalkulacije u bazu");
            } else {
                DAOStavkaKalkulacije daoStavka=new DAOStavkaKalkulacije();
                int idKalkulacije=daoKalkulacija.idZadnjeKalkulacije();
                for (int i = 0; i < kalkulacija.getItems().size(); i++) {
                   if(!daoStavka.upisUBazuStavkuKalkulacije(idKalkulacije, 
                           kalkulacija.getItems().get(i).getIdProizvoda(),
                           kalkulacija.getItems().get(i).getFakturnaCijena(),
                           kalkulacija.getItems().get(i).getKolicina(),
                           kalkulacija.getItems().get(i).getJedMjere(), 
                           kalkulacija.getItems().get(i).getRabat(), 
                           kalkulacija.getItems().get(i).getMarza(),
                           kalkulacija.getItems().get(i).getStopa(),
                           kalkulacija.getItems().get(i).getProdajnaCijena())){
                       AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greska pru upisu stavke kalkulacije u bazu");
                   }
                }
                DAONarudzbenica daoNarudzbenica=new DAONarudzbenica();
                if(!daoNarudzbenica.setujNaruzbuKalkulisana(Integer.parseInt(narudzbaComboBox.getSelectionModel().getSelectedItem().split(" ")[0]))){
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greska!");
                }
                
                ObservableList<TabelaKalkulacija> lista=kalkulacija.getItems();
                PDF.kreirajFajlKalkulacija(lista, dobavljacComboBox.getSelectionModel().getSelectedItem(), idKalkulacije);
                kalkulacija.getItems().clear();
                cistiPolja();
            }
        }
    }
    public void nazadStisak(ActionEvent event) throws IOException{
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/PocetnaForma.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }
}
