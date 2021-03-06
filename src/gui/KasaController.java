package gui;

import babyshop.AlertHelper;
import dao.DAOProizvod;
import dao.DAORacun;
import dao.DAOSkladisteProizvod;
import dao.DAOStavka;
import dao.DAOStorniranRacun;
import dao.DAOTipZaposlenog;
import dao.DAOZaposleni;
import dto.DTOProizvod;
import dto.DTORacun;
import dto.DTOStavka;
import dto.DTOZaposleni;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pdf.PDF;
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
    private Button ponistiButton;

    //@FXML
    //private Label stanjeLabel;
    @FXML
    private Button storniranjeButton;

    @FXML
    private TextField brojRacunaTextField;

    @FXML
    private Button razduzenjeButton;

    public boolean barkod = false;

    public double ukupno = 0;

    public static double ukupnoZaProsljedjivanje;

    public boolean pozvanaMetodaBarkod = false;

    public boolean pozvanaMetodaSifra = false;

    public ArrayList<DTOStavka> listaStavki = new ArrayList<>();
    @FXML
    private Button nazadButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        datumLabel.setText("" + sdf.format(new Date().getTime()));
        ukupnaCijenaLabel.setText("0,00");
        DTOZaposleni k = DAOZaposleni.getZaposleniById(PrijavaNaSistemController.idZaposlenog);
        prodavacLabel.setText(" " + k.getIme() + " " + k.getPrezime());
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
                                    proizvod.getCijena(), proizvod.getIdProizvoda()));
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
                                proizvod.getCijena(), proizvod.getIdProizvoda()));
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
                                    proizvod.getCijena(), proizvod.getIdProizvoda()));
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
                                proizvod.getCijena(), proizvod.getIdProizvoda()));
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
            DTOProizvod p = new DAOProizvod().getProizvodPoBarkodu(barkodTextField.getText());
            if (p != null) {
                kolicinaTextField.requestFocus();
                return true;
            }
        }
        return false;
    }

    @FXML
    public void barkodUnos() {
        pozvanaMetodaBarkod = provjeraBarkoda();
    }

    private boolean provjeraSifre() {
        barkod = false;
        if (!"".equals(sifraTextField.getText()) && Pattern.matches("[0-9]{5}", sifraTextField.getText())) {
            DTOProizvod p = new DAOProizvod().getProizvodPoSifri(sifraTextField.getText());
            if (p != null) {
                kolicinaTextField.requestFocus();
                return true;
            }
        }
        return false;
    }

    @FXML
    public void sifraUnos() {
        pozvanaMetodaSifra = provjeraSifre();
    }

    private int provjeraStanja() {
        DAOProizvod dao = new DAOProizvod();
        DAOSkladisteProizvod daoSkladiste = new DAOSkladisteProizvod();
        DTOProizvod proizvod = null;
        if (barkod) {
            proizvod = dao.getProizvodPoBarkodu(barkodTextField.getText());
            boolean daLiMoguAzurirati = dao.daLiImaDovoljnoNaStanju(proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText()),
                    proizvod.getIdProizvoda());
            if (daLiMoguAzurirati) {
                return proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText());
            }
        } else {
            proizvod = dao.getProizvodPoSifri(sifraTextField.getText());
            boolean daLiMoguAzurirati = dao.daLiImaDovoljnoNaStanju(proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText()),
                    proizvod.getIdProizvoda());
            if (daLiMoguAzurirati) {
                return proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText());
            }
        }
        return -1;
    }

    @FXML
    public void klikNaKolicinu() {
        if (!("".equals(kolicinaTextField.getText()))) {
            if (Integer.parseInt(kolicinaTextField.getText()) < 0) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Količina ne može biti negativna.");
            } else {
                Integer.parseInt(kolicinaTextField.getText());
                try {
                    int fleg = provjeraStanja();
                    if (fleg >= 0) {
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
                    } else {
                        kolicinaTextField.setText("1");
                        barkodTextField.clear();
                        sifraTextField.clear();
                        AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Nemate dovoljno proizvoda na stanju.");
                    }
                } catch (NumberFormatException e) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Unesite broj.");
                }
            }
        }
    }

    @FXML
    public void stampajRacun() {
        DTOProizvod proizvod = null;
        DAOProizvod dao = new DAOProizvod();
        if (kasaTabela.getItems().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Nemate stavke na računu.");
        } else {
            DAORacun daoRacun = new DAORacun();
            if (!daoRacun.dodajRacun(2, new java.sql.Date(new Date().getTime()), Double.parseDouble(ukupnaCijenaLabel.getText()), false)) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Nije moguće kreirati račun.");
            }
            int idRacuna = daoRacun.idZadnjegRacuna();
            DAOStavka daoStavka = new DAOStavka();
            for (DTOStavka stavka : listaStavki) {
                if (!daoStavka.upisUBazuStavku(idRacuna, stavka.getKolicina(), stavka.getCijena(), stavka.getIdProizvoda())) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška pri upisu stavke u bazu.");
                } else {
                    if (barkod) {
                        proizvod = dao.getProizvodPoBarkodu(barkodTextField.getText());
                        dao.azurirajProizvod(proizvod.getKolicina() - stavka.getKolicina(),
                                proizvod.getIdProizvoda());
                    } else {
                        proizvod = dao.getProizvodPoSifri(sifraTextField.getText());
                        dao.azurirajProizvod(proizvod.getKolicina() - stavka.getKolicina(),
                                proizvod.getIdProizvoda());
                    }
                }
            }
            PregledProizvodaDetaljnoController.pregledProizvodaController.postaviTabelu();

            String[] attachFiles = new String[1];
            attachFiles[0] = PDF.kreirajFajlRacun(listaStavki, new DAORacun().vratiRacunPoId(idRacuna));
            ukupno = 0;
            ukupnaCijenaLabel.setText("0,00");
            listaStavki.clear();
            kasaTabela.getItems().clear();
        }
    }

    @FXML
    public void brisanjeSaRacuna() {
        if (!kasaTabela.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            TabelaKasa selektovanRed = kasaTabela.getSelectionModel().getSelectedItem();
            ukupno -= selektovanRed.getVrijednost();
            ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));
            kasaTabela.getItems().remove(selektovanRed);
            DAOProizvod daoProizvod = new DAOProizvod();
            DAOSkladisteProizvod skladiste = new DAOSkladisteProizvod();
            for (DTOStavka stavka : listaStavki) {
                DTOProizvod proizvod = daoProizvod.getProizvodPoId(stavka.getIdProizvoda());
                boolean uspjeno = daoProizvod.azurirajProizvod(proizvod.getKolicina() + selektovanRed.getKolicina(), proizvod.getIdProizvoda());
                if (!uspjeno) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom ažuriranja proizvoda.");
                } else {
                    boolean upisanoUsklasite = skladiste.azurirajProizvodUSkladistu(proizvod.getKolicina() + selektovanRed.getKolicina(), proizvod.getIdProizvoda());
                    if (!upisanoUsklasite) {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom ažuriranja proizvoda u skladištu.");
                    } else {
                        if (selektovanRed.getSifra().equals(proizvod.getSifra())) {
                            listaStavki.remove(stavka);
                            break;
                        }
                    }
                }

            }
        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali stavku.");
            return;
        }
    }

    @FXML
    public void pronadjiRacunZaStorniranje() {
        barkodTextField.setDisable(true);
        sifraTextField.setDisable(true);
        knjizenjeButton.setDisable(true);
        kolicinaTextField.setDisable(true);
        kusurButton.setDisable(true);
        razduzenjeButton.setDisable(true);
        brisanjeButton.setDisable(true);
        kasaTabela.getItems().clear();
        int idRacuna = Integer.parseInt(brojRacunaTextField.getText());
        DTORacun racunZaStorniranje = new DAORacun().vratiRacunPoId(idRacuna);
        if (racunZaStorniranje != null) {
            if (!racunZaStorniranje.getStorniran()) {
                listaStavki.clear();
                listaStavki = new DAOStavka().stavkeNaRacunu(idRacuna);
                barkodKolona.setCellValueFactory(new PropertyValueFactory<>("barkod"));
                sifraKolona.setCellValueFactory(new PropertyValueFactory<>("sifra"));
                nazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv"));
                kolicinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
                cijenaKolona.setCellValueFactory(new PropertyValueFactory<>("cijena"));
                vrijednostKolona.setCellValueFactory(new PropertyValueFactory<>("vrijednost"));
                for (DTOStavka stavka : listaStavki) {
                    DTOProizvod proizvod = new DAOProizvod().getProizvodPoId(stavka.getIdProizvoda());
                    kasaTabela.getItems().add(new TabelaKasa(proizvod.getBarkod(), proizvod.getSifra(), proizvod.getNaziv(),
                            stavka.getKolicina(), proizvod.getCijena(), stavka.getCijena() * stavka.getKolicina()));
                }
                ukupno = racunZaStorniranje.getUkupnaCijena();
                ukupnaCijenaLabel.setText(String.format("%.2f", racunZaStorniranje.getUkupnaCijena()));
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Račun je već storniran.");
                barkodTextField.setDisable(false);
                sifraTextField.setDisable(false);
                knjizenjeButton.setDisable(false);
                kolicinaTextField.setDisable(false);
                kusurButton.setDisable(false);
                razduzenjeButton.setDisable(false);
                brisanjeButton.setDisable(false);
                ponistiButton.setDisable(false);
            }
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Broj računa ne postoji.");
            barkodTextField.setDisable(false);
            sifraTextField.setDisable(false);
            knjizenjeButton.setDisable(false);
            kolicinaTextField.setDisable(false);
            kusurButton.setDisable(false);
            razduzenjeButton.setDisable(false);
            brisanjeButton.setDisable(false);
            ponistiButton.setDisable(false);
        }
    }

    @FXML
    public void stornirajRacun() {
        pronadjiRacunZaStorniranje();

        for (TabelaKasa kasa : kasaTabela.getItems()) {
            DTOProizvod proizvodZaStorniranje = new DAOProizvod().getProizvodPoBarkodu(kasa.getBarkod());
            new DAOProizvod().azurirajProizvod(proizvodZaStorniranje.getKolicina() + kasa.getKolicina(), proizvodZaStorniranje.getIdProizvoda());
        }
        int idRacuna = Integer.parseInt(brojRacunaTextField.getText());
        DTORacun racunZaStorniranje = new DAORacun().vratiRacunPoId(idRacuna);
        double negativnoUkupno = -ukupno;
        new DAORacun().azurirajRacun(idRacuna, true);
        new DAOStorniranRacun().dodajStorniraniRacun(new java.sql.Date(new Date().getTime()), negativnoUkupno, racunZaStorniranje.getIdZaposlenog(), idRacuna);
        ukupno = 0;
        ukupnaCijenaLabel.setText("0,00");
        brojRacunaTextField.setText("");
        listaStavki.clear();
        kasaTabela.getItems().clear();
        barkodTextField.setDisable(false);
        sifraTextField.setDisable(false);
        knjizenjeButton.setDisable(false);
        kolicinaTextField.setDisable(false);
        kusurButton.setDisable(false);
        razduzenjeButton.setDisable(false);
        brisanjeButton.setDisable(false);
        ponistiButton.setDisable(false);
    }

    @FXML
    public void racunajKusur(ActionEvent event) throws IOException {
        ukupnoZaProsljedjivanje = ukupno;
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/kusur.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.setTitle("Kusur");
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }

    @FXML
    public void razduziRacun(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/razduzenje.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.setTitle("Zakljucenje kase");
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }

    @FXML
    public void ponisti(ActionEvent event) throws IOException {
        ukupnaCijenaLabel.setText("0.00");
        brojRacunaTextField.setText("");
        for (DTOStavka stavka : listaStavki) {
            DAOProizvod daoProizvod = new DAOProizvod();
            DAOSkladisteProizvod skladiste = new DAOSkladisteProizvod();
            DTOProizvod proizvod = daoProizvod.getProizvodPoId(stavka.getIdProizvoda());
            boolean uspjeno = daoProizvod.azurirajProizvod(proizvod.getKolicina() + stavka.getKolicina(), proizvod.getIdProizvoda());
            if (!uspjeno) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom ažuriranja proizvoda.");
            } else {
                boolean upisanoUsklasite = skladiste.azurirajProizvodUSkladistu(proizvod.getKolicina() + stavka.getKolicina(), proizvod.getIdProizvoda());
                if (!upisanoUsklasite) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom ažuriranja proizvoda u skladištu.");
                } else {
                    listaStavki.clear();
                    kasaTabela.getItems().clear();
                }
            }
        }
    }
}
