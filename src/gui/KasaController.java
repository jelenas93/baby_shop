package gui;

import babyshop.AlertHelper;
import dao.DAOProizvod;
import dao.DAORacun;
import dao.DAOStavka;
import dao.DAOStorniranRacun;
import dto.DTOProizvod;
import dto.DTORacun;
import dto.DTOStavka;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tabele.TabelaKasa;

public class KasaController implements Initializable {

    @FXML
    private Label prodavacLabel;

    @FXML
    private Label datumLabel;

    @FXML
    private TextField sifraTextField;

    @FXML
    private TextField barkodTextField;

    @FXML
    private TextField kolicinaTextField;

    @FXML
    private Button kusurButton;

    @FXML
    private Button knjizenjeButton;

    @FXML
    private Button brisanjeButton;

    @FXML
    private TableView<TabelaKasa> kasaTabela;

    @FXML
    private TableColumn<TabelaKasa, String> barkodKolona;

    @FXML
    private TableColumn<TabelaKasa, String> sifraKolona;

    @FXML
    private TableColumn<TabelaKasa, String> nazivKolona;

    @FXML
    private TableColumn<TabelaKasa, Integer> kolicinaKolona;

    @FXML
    private TableColumn<TabelaKasa, Double> cijenaKolona;

    @FXML
    private TableColumn<TabelaKasa, Double> vrijednostKolona;

    @FXML
    private Label ukupnaCijenaLabel;

    @FXML
    private Label stanjeLabel;

    @FXML
    private Button storniranjeButton;

    @FXML
    private TextField brojRacunaTextField;

    @FXML
    private Button razduzenjeButton;

    public boolean barkod = false;

    public double ukupno = 0;

    public boolean pozvanaMetodaBarkod = false;

    public boolean pozvanaMetodaSifra = false;

    public ArrayList<DTOStavka> listaStavki = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datumLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        ukupnaCijenaLabel.setText("0,00");
    }

    private void puniTabelu() {
        barkodKolona.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        sifraKolona.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        nazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        kolicinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        cijenaKolona.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        vrijednostKolona.setCellValueFactory(new PropertyValueFactory<>("vrijednost"));
        kasaTabela.getItems().add(getTabela());
        for (int i = 0; i < kasaTabela.getItems().size(); i++) {
            if (kasaTabela.getItems().get(i) == null) {
                kasaTabela.getItems().remove(i);
            }
        }
    }

    private TabelaKasa getTabela() {
        DTOProizvod proizvod = null;
        TabelaKasa kasaZaVratiti = null;
        if (barkod) {
             barkod = false;
            proizvod = new DAOProizvod().getProizvodPoBarkodu(barkodTextField.getText());
            boolean ima = false;
            List<TabelaKasa> tabela = kasaTabela.getItems();
            if (!tabela.isEmpty()) {
                for (TabelaKasa el : tabela) {
                    if (el != null) {
                        if (el.getBarkod().equals(barkodTextField.getText())) {
                            ima = true;
                        }
                    }
                }
            }
            if (ima) {
                int indeks = 0;
                for (TabelaKasa el : tabela) {
                    if (el != null) {
                        if (el.getBarkod().equals(barkodTextField.getText())) {
                            System.out.println(el.getBarkod());
                            kasaTabela.getItems().remove(el);
                            kasaTabela.getItems().add(indeks, (new TabelaKasa(proizvod.getBarkod(), proizvod.getSifra(),
                                    proizvod.getNaziv(), Integer.parseInt(kolicinaTextField.getText()) + el.getKolicina(),
                                    proizvod.getCijena(),
                                    Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena() + el.getVrijednost())));
                            for (DTOStavka stavka : listaStavki) {
                                if (stavka.getIdProizvoda() == proizvod.getIdProizvoda()) {
                                    listaStavki.remove(stavka);
                                    break;
                                }
                            }
                            listaStavki.add(new DTOStavka(Integer.parseInt(kolicinaTextField.getText()) + el.getKolicina(),
                                    Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena() + el.getVrijednost(), proizvod.getIdProizvoda()));
                            ukupno += Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena();
                            ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));

                            break;
                        }
                    }
                    indeks++;
                }
            } else {
                kasaZaVratiti = new TabelaKasa(proizvod.getBarkod(), proizvod.getSifra(),
                        proizvod.getNaziv(), Integer.parseInt(kolicinaTextField.getText()),
                        proizvod.getCijena(), Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena());

                listaStavki.add(
                        new DTOStavka(Integer.parseInt(kolicinaTextField.getText()),
                                Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena(), proizvod.getIdProizvoda()));
                ukupno += Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena();

                ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));
                return kasaZaVratiti;
            }
        } else {
            proizvod = new DAOProizvod().getProizvodPoSifri(sifraTextField.getText());
            boolean ima = false;
            List<TabelaKasa> tabela = kasaTabela.getItems();
            if (!tabela.isEmpty()) {
                for (TabelaKasa el : tabela) {
                    if (el != null) {
                        if (el.getSifra().equals(sifraTextField.getText())) {
                            ima = true;
                        }
                    }
                }
            }
            if (ima) {
                int indeks = 0;
                for (TabelaKasa el : tabela) {
                    if (el != null) {
                        if (el.getSifra().equals(sifraTextField.getText())) {
                            kasaTabela.getItems().remove(el);
                            kasaTabela.getItems().add(indeks, (new TabelaKasa(proizvod.getBarkod(), proizvod.getSifra(),
                                    proizvod.getNaziv(), Integer.parseInt(kolicinaTextField.getText()) + el.getKolicina(),
                                    proizvod.getCijena(),
                                    Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena() + el.getVrijednost())));
                            for (DTOStavka stavka : listaStavki) {
                                if (stavka.getIdProizvoda() == proizvod.getIdProizvoda()) {
                                    listaStavki.remove(stavka);
                                    break;
                                }
                            }
                            listaStavki.add(new DTOStavka(Integer.parseInt(kolicinaTextField.getText()) + el.getKolicina(),
                                    Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena() + el.getVrijednost(), proizvod.getIdProizvoda()));
                            ukupno += Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena();
                            ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));

                            break;
                        }
                    }
                    indeks++;
                }
            } else {
                kasaZaVratiti = new TabelaKasa(proizvod.getBarkod(), proizvod.getSifra(),
                        proizvod.getNaziv(), Integer.parseInt(kolicinaTextField.getText()),
                        proizvod.getCijena(), Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena());

                listaStavki.add(
                        new DTOStavka(Integer.parseInt(kolicinaTextField.getText()),
                                Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena(), proizvod.getIdProizvoda()));
                ukupno += Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena();

                ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));
                return kasaZaVratiti;
            }
        }
        return kasaZaVratiti;

    }

    private boolean provjeraBarkoda() {
        barkod = true;
        if (!("".equals(barkodTextField.getText())) && Pattern.matches("[0-9]{13}", barkodTextField.getText())) {
            kolicinaTextField.requestFocus();
            return true;
        }
        return false;
    }

    public void barkodUnos() {
        pozvanaMetodaBarkod = provjeraBarkoda();
    }

    private boolean provjeraSifre() {
        barkod = false;
        if (!"".equals(sifraTextField.getText()) && Pattern.matches("[0-9]{5}", sifraTextField.getText())) {
            kolicinaTextField.requestFocus();
            return true;
        }
        return false;
    }

    public void sifraUnos() {
        pozvanaMetodaSifra = provjeraSifre();
    }

    public void klikNaKolicinu() {
        if (!("".equals(kolicinaTextField.getText()))) {
            try {
                Integer.parseInt(kolicinaTextField.getText());
                puniTabelu();
                if (pozvanaMetodaBarkod) {
                    barkodTextField.clear();
                    pozvanaMetodaBarkod = false;
                    barkodTextField.requestFocus();
                } else if (pozvanaMetodaSifra) {
                    sifraTextField.clear();
                    pozvanaMetodaSifra = false;
                    sifraTextField.requestFocus();
                }
                kolicinaTextField.setText("1");
            } catch (NumberFormatException e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Unesite broj.");
            }
        }
    }

    public void stampajRacun() {
        DAORacun daoRacun = new DAORacun();
        if (!daoRacun.dodajRacun(2, new java.sql.Date(new Date().getTime()), ukupno,false)) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greeska racuna");
        }
        int idRacuna = daoRacun.idZadnjegRacuna();
        DAOStavka daoStavka = new DAOStavka();
        for (DTOStavka stavka : listaStavki) {
            if (!daoStavka.upisUBazuStavku(idRacuna, stavka.getKolicina(), stavka.getCijena(), stavka.getIdProizvoda())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška pri upisu stavke u bazu.");
            }
        }
        ukupno = 0;
        ukupnaCijenaLabel.setText("0,00");
        listaStavki.clear();
        kasaTabela.getItems().clear();
    }

    public void brisanjeSaRacuna() {
        if (!kasaTabela.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            TabelaKasa selektovanRed = kasaTabela.getSelectionModel().getSelectedItem();
            ukupno -= selektovanRed.getVrijednost();
            ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));
            kasaTabela.getItems().remove(selektovanRed);
            DAOProizvod daoProizvod = new DAOProizvod();
            for (DTOStavka stavka : listaStavki) {
                DTOProizvod proizvod = daoProizvod.getProizvodPoId(stavka.getIdProizvoda());
                if (selektovanRed.getSifra().equals(proizvod.getSifra())) {
                    listaStavki.remove(stavka);
                    break;
                }
            }
        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali stavku.");
            return;
        }
    }

    public void pronadjiRacunZaStorniranje(){
        int idRacuna=Integer.parseInt(brojRacunaTextField.getText());
        DTORacun racunZaStorniranje=new DAORacun().vratiRacunPoId(idRacuna);
        listaStavki=new DAOStavka().stavkeNaRacunu(idRacuna);
        barkodKolona.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        sifraKolona.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        nazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        kolicinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        cijenaKolona.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        vrijednostKolona.setCellValueFactory(new PropertyValueFactory<>("vrijednost"));
        for(DTOStavka stavka: listaStavki){
            DTOProizvod proizvod=new DAOProizvod().getProizvodPoId(stavka.getIdProizvoda());
            kasaTabela.getItems().add(new TabelaKasa(proizvod.getBarkod(), proizvod.getSifra(), proizvod.getNaziv(), 
                    stavka.getKolicina(), proizvod.getCijena(), stavka.getCijena()));
        }
        ukupno=racunZaStorniranje.getUkupnaCijena();
        ukupnaCijenaLabel.setText(String.format("%.2f",racunZaStorniranje.getUkupnaCijena()));
    }
    
    public void stornirajRacun(){
        for(TabelaKasa kasa:kasaTabela.getItems()){
            DTOProizvod proizvodZaStorniranje=new DAOProizvod().getProizvodPoBarkodu(kasa.getBarkod());
            new DAOProizvod().azurirajProizvod(proizvodZaStorniranje.getKolicina()+kasa.getKolicina(), proizvodZaStorniranje.getIdProizvoda());
        }
        int idRacuna=Integer.parseInt(brojRacunaTextField.getText());
        DTORacun racunZaStorniranje=new DAORacun().vratiRacunPoId(idRacuna);
        double negativnoUkupno=-ukupno;
        new DAORacun().azurirajRacun(idRacuna, true);
        new DAOStorniranRacun().dodajStorniraniRacun(new java.sql.Date(new Date().getTime()), negativnoUkupno, racunZaStorniranje.getIdZaposlenog(), idRacuna);
        ukupno = 0;
        ukupnaCijenaLabel.setText("0,00");
        brojRacunaTextField.setText("");
        listaStavki.clear();
        kasaTabela.getItems().clear();
    }
}
