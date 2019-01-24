package gui;

import babyshop.AlertHelper;
import dao.DAOProizvod;
import dao.DAORacun;
import dao.DAOSkladisteProizvod;
import dao.DAOStavka;
import dao.DAOStorniranRacun;
import dao.DAOZaposleni;
import dto.DTOProizvod;
import dto.DTORacun;
import dto.DTOStavka;
import dto.DTOZaposleni;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private Label stanjeLabel;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        datumLabel.setText(sdf.format(new Date().getTime()));
        ukupnaCijenaLabel.setText("0,00");
        DTOZaposleni k = DAOZaposleni.getZaposleniById(PocetnaFormaController.idZaposlenog);
        prodavacLabel.setText(k.getIme()+" "+k.getPrezime());
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

    private int provjeraStanja() {
        DAOProizvod dao = new DAOProizvod();
        DAOSkladisteProizvod daoSkladiste = new DAOSkladisteProizvod();
        DTOProizvod proizvod = null;
        if (barkod) {
            proizvod = dao.getProizvodPoBarkodu(barkodTextField.getText());
            boolean daLiMoguAzurirati = dao.azurirajProizvod(proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText()),
                    proizvod.getIdProizvoda());
            if (daLiMoguAzurirati) {
                //  stanjeLabel.setText((proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText())) + "");
                return proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText());
            }
        } else {
            proizvod = dao.getProizvodPoSifri(sifraTextField.getText());
            boolean daLiMoguAzurirati = dao.azurirajProizvod(proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText()),
                    proizvod.getIdProizvoda());

            /* boolean azuriranjeSkladista = daoSkladiste.azurirajProizvodUSkladistu(proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText()),
                    proizvod.getIdProizvoda());*/
            if (daLiMoguAzurirati) {
                //  stanjeLabel.setText((proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText())) + "");
                return proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText());
            }
        }
        stanjeLabel.setText(proizvod.getKolicina() + "");
        return -1;
    }

    public void klikNaKolicinu() {
        if (!("".equals(kolicinaTextField.getText()))) {

            Integer.parseInt(kolicinaTextField.getText());
            try {
                int fleg = provjeraStanja();
                if (fleg >= 0) {
                    stanjeLabel.setText(fleg + "");
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

    public void stampajRacun() {
        if (kasaTabela.getItems().size() == 0) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Nemate stavke na računu.");
        } else {
            DAORacun daoRacun = new DAORacun();
            if (!daoRacun.dodajRacun(2, new java.sql.Date(new Date().getTime()), ukupno, false)) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greska racuna");
            }
            int idRacuna = daoRacun.idZadnjegRacuna();
            DAOStavka daoStavka = new DAOStavka();
            for (DTOStavka stavka : listaStavki) {
                if (!daoStavka.upisUBazuStavku(idRacuna, stavka.getKolicina(), stavka.getCijena(), stavka.getIdProizvoda())) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška pri upisu stavke u bazu.");
                }
            }
            String[] attachFiles = new String[1];
            attachFiles[0] = PDF.kreirajFajlRacun(listaStavki,new DAORacun().vratiRacunPoId(idRacuna));
            ukupno = 0;
            ukupnaCijenaLabel.setText("0,00");
            stanjeLabel.setText("");
            listaStavki.clear();
            kasaTabela.getItems().clear();
        }
    }

    public void brisanjeSaRacuna() {
        if (!kasaTabela.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            TabelaKasa selektovanRed = kasaTabela.getSelectionModel().getSelectedItem();
            ukupno -= selektovanRed.getVrijednost();
            ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));
            stanjeLabel.setText("");
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

    public void pronadjiRacunZaStorniranje() {
        int idRacuna = Integer.parseInt(brojRacunaTextField.getText());
        DTORacun racunZaStorniranje = new DAORacun().vratiRacunPoId(idRacuna);
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
                    stavka.getKolicina(), proizvod.getCijena(), stavka.getCijena()));
        }
        ukupno = racunZaStorniranje.getUkupnaCijena();
        ukupnaCijenaLabel.setText(String.format("%.2f", racunZaStorniranje.getUkupnaCijena()));
    }

    public void stornirajRacun() {
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
    }

    public void racunajKusur(ActionEvent event) throws IOException {
        ukupnoZaProsljedjivanje = ukupno;
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/kusur.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }

    public void razduziRacun(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/razduzenje.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
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
