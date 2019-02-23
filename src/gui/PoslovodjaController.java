package gui;

import babyshop.AlertHelper;
import dao.DAODobavljac;
import dao.DAOKalkulacija;
import dao.DAONarudzbenica;
import dao.DAOProizvod;
import dao.DAOProizvodjac;
import dao.DAORacun;
import dao.DAOSkladisteProizvod;
import dao.DAOStavka;
import dao.DAOStavkaKalkulacije;
import dao.DAOStavkaNarudzbe;
import dao.DAOStorniranRacun;
import dao.DAOZaposleni;
import dto.DTODobavljac;
import dto.DTONarudzbenica;
import dto.DTOProizvod;
import dto.DTORacun;
import dto.DTOStavka;
import dto.DTOStavkaNarudzbe;
import dto.DTOZaposleni;
import static gui.KasaController.ukupnoZaProsljedjivanje;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import pdf.PDF;
import tabele.TabelaDetaljanProizvod;
import tabele.TabelaKalkulacija;
import tabele.TabelaKasa;
import tabele.TabelaNarudzba;
import tabele.TabelaNarudzbenica;

public class PoslovodjaController implements Initializable {

    @FXML
    private Tab pregledTab;
//narudzbenica   
    @FXML
    private TableView<TabelaNarudzba> narudzba;

    @FXML
    private TableColumn<TabelaNarudzba, String> sifra;

    @FXML
    private TableColumn<TabelaNarudzba, String> barKod;

    @FXML
    private TableColumn<TabelaNarudzba, String> naziv;

    @FXML
    private TableColumn<TabelaNarudzba, Double> cijena;

    @FXML
    private TableColumn<TabelaNarudzba, Integer> stanje;

    @FXML
    private Button DodajArtiklButton;

    @FXML
    private TextField kolicinaNarudzbaTextField;

    @FXML
    private TableView<TabelaNarudzbenica> narudzbenica;

    @FXML
    private TableColumn<TabelaNarudzbenica, String> sifraNarucenog;

    @FXML
    private TableColumn<TabelaNarudzbenica, String> barKodNarucenog;

    @FXML
    private TableColumn<TabelaNarudzbenica, String> nazivNarucenog;

    @FXML
    private TableColumn<TabelaNarudzbenica, Integer> naruceno;

    @FXML
    private Button posaljiNarudzbuButton;

    @FXML
    private Button ukloniArtiklButton;

    @FXML
    private ComboBox<String> imeDobavljacaComboBox;

    private double ukupnaCijena = 0;

//kalkulacija
    @FXML
    private TableView<TabelaKalkulacija> kalkulacija;

    @FXML
    private TableColumn<TabelaKalkulacija, String> barKodKalkulacija;

    @FXML
    private TableColumn<TabelaKalkulacija, String> sifraKalkulacija;

    @FXML
    private TableColumn<TabelaKalkulacija, String> nazivKalkulacija;

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
    private Label proizvodLabel;

    @FXML
    private TextField marzaTextField;

    int idDobavljaca; // za kalkulaciju treba

    
//proizvod
    @FXML
    private TableView<TabelaDetaljanProizvod> tabela;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> barKodProizvod;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> sifraProizvod;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> nazivProizvod;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, String> proizvodjac;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, Integer> kolicinaProizvod;

    @FXML
    private TableColumn<TabelaDetaljanProizvod, Double> cijenaProizvod;

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
    
//kasa
    
    @FXML
    private Label prodavacLabel;

    @FXML
    private Label datumLabel;

    @FXML
    private TextField sifraTextField;

    @FXML
    private TextField barkodTextField;

    @FXML
    private TextField kolicinaKasaTextField;

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
        postaviDobavljace();
        postaviDobavljaceZaKalkulaciju();
        postaviTabeluProizvod();
        kasaPocetno();
    }

    private void postaviDobavljace() {
        DAODobavljac daod = new DAODobavljac();
        ObservableList<DTODobavljac> listaDobavljaca;
        listaDobavljaca = daod.getDobavljace();
        for (int i = 0; i < listaDobavljaca.size(); i++) {
            imeDobavljacaComboBox.getItems().add(listaDobavljaca.get(i).getNaziv());
        }
        postaviTabelu();
       
    }

    private void postaviTabelu() {

        sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        barKod.setCellValueFactory(new PropertyValueFactory<>("barKod"));
        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        stanje.setCellValueFactory(new PropertyValueFactory<>("stanje"));
        cijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));

        sifraNarucenog.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        barKodNarucenog.setCellValueFactory(new PropertyValueFactory<>("barKod"));
        nazivNarucenog.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        naruceno.setCellValueFactory(new PropertyValueFactory<>("naruceno"));

        imeDobavljacaComboBox.getSelectionModel().selectedItemProperty().addListener((v, staraVrijednost, novaVrijednost) -> narudzba.setItems(getTabela()));

    }

    private ObservableList<TabelaNarudzba> getTabela() {

        narudzbenica.getItems().clear();

        ObservableList<TabelaNarudzba> listaZaPrikaz = FXCollections.observableArrayList();
        List<TabelaNarudzba> listaMoja = new ArrayList<>();

        DAODobavljac daod = new DAODobavljac();
        DTODobavljac dtod = daod.getDobavljacPoNazivu(imeDobavljacaComboBox.getSelectionModel().getSelectedItem());
        int IdDobavljaca = dtod.getIdDobavljaca();

        DAOProizvod daop = new DAOProizvod();
        ObservableList<DTOProizvod> lista = daop.getSveProizvodeOdDobavljaca(IdDobavljaca);
        for (DTOProizvod proizvod : lista) {
            //  System.out.println(proizvod.getBarkod());
            listaMoja.add(new TabelaNarudzba(proizvod.getSifra(), proizvod.getBarkod(), proizvod.getNaziv(), proizvod.getCijena(),
                    proizvod.getKolicina()
            ));
        }
        for (TabelaNarudzba proizvod : listaMoja) {
            listaZaPrikaz.add(proizvod);
        }
        return listaZaPrikaz;

    }

    @FXML
    public void dodajArtiklButtonOnAction(ActionEvent event) {
        ObservableList<TabelaNarudzba> selektovano;
        selektovano = narudzba.getSelectionModel().getSelectedItems();

        boolean pogresno = false;

        try {
            Integer.parseInt(kolicinaNarudzbaTextField.getText());

        } catch (NumberFormatException e) {
            pogresno = true;
        }

        if ("".equals(kolicinaNarudzbaTextField.getText()) || (pogresno) || ((Integer.parseInt(kolicinaNarudzbaTextField.getText())) < 0)) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste ispravno unijeli kolicinu.");
            kolicinaNarudzbaTextField.setText("");
        } else if (selektovano.size() == 0) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Nista nije izabrano.");
        } else {
            //  ObservableList<TabelaNarudzba> selektovano;
            //   selektovano = narudzba.getSelectionModel().getSelectedItems();
            int kolicina = Integer.valueOf(kolicinaNarudzbaTextField.getText());
            String barkod = selektovano.get(0).getBarKod();
            System.out.println(barkod);
            String sifraa = selektovano.get(0).getSifra();
            String naziiv = selektovano.get(0).getNaziv();

            TabelaNarudzbenica novo = new TabelaNarudzbenica(sifraa, naziiv, kolicina, barkod);
            narudzbenica.getItems().add(novo);
            kolicinaNarudzbaTextField.clear();
        }
    }

    @FXML
    public void ukloniArtiklButtonOnAction(ActionEvent event) {

        ObservableList<TabelaNarudzbenica> selektovano, sviProizvodi;
        selektovano = narudzbenica.getSelectionModel().getSelectedItems();
        if (selektovano.size() == 0) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali artikl.");
        } else {
            sviProizvodi = narudzbenica.getItems();
            selektovano.forEach(sviProizvodi::remove);
        }

    }

    public void upisiUbazuSveStavke(ObservableList<TabelaNarudzbenica> lista) {
        DAOStavkaNarudzbe daoStavka = new DAOStavkaNarudzbe();
        DAOProizvod daoProizvod = new DAOProizvod();
        DAONarudzbenica daoNarudzbenica = new DAONarudzbenica();
        int idNarudbenice = new DAONarudzbenica().idZadnjeNarudzbenice();
        double ukupno = 0;
        for (TabelaNarudzbenica izTabele : lista) {
            DTOProizvod proizvod = daoProizvod.getProizvodPoSifri(izTabele.getSifra());
            ukupno += proizvod.getCijena() * izTabele.getNaruceno();
            if (!daoStavka.upisUBazuStavkuNarudzbe(idNarudbenice, izTabele.getNaruceno(), proizvod.getCijena(), proizvod.getIdProizvoda())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška pri upisu stavke narudžbe u bazu.");
                break;
            }

        }
        daoNarudzbenica.azurirajNarudzbenicu(idNarudbenice, ukupno);
    }

    @FXML
    public void posaljiNarudzbuButtonOnAction(ActionEvent event) {

        ObservableList<TabelaNarudzbenica> sviProizvodi;
        sviProizvodi = narudzbenica.getItems();
        if (sviProizvodi.size() == 0) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Narudzbenica je prazna.");
        } else {

            DAODobavljac daod = new DAODobavljac();
            DTODobavljac dtod = daod.getDobavljacPoNazivu(imeDobavljacaComboBox.getSelectionModel().getSelectedItem());
            String nazivDobavljaca = dtod.getNaziv();

            //    String primalac = dtod.getEmail();
            String host = "smtp.gmail.com";
            String port = "587";
            String posiljalac = "shopbaby273@gmail.com";
            String lozinka = "babyshop273";

            String primalac = "jelenas9393@gmail.com";
            String predmet = " Nova narudzba od Baby Shop-a";
            String poruka = "Narudzba je prilozenom fajlu";

            String[] attachFiles = new String[1];
            attachFiles[0] = PDF.kreirajFajlNarudzbe(sviProizvodi, nazivDobavljaca);

            if ("".equals(attachFiles[0])) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Narudzbenica nije kreirana");
            } else {
                try {
                    sendEmailWithAttachments(host, port, posiljalac, lozinka, primalac,
                            predmet, poruka, attachFiles);
                    AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Narudzba je poslata dobavljacu.");
                } catch (Exception ex) {
                    AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Narudzba nije poslata.");
                    ex.printStackTrace();

                }

                DAONarudzbenica daon = new DAONarudzbenica();

                daon.upisiNarudzbenicuUBazu(new java.sql.Date(new Date().getTime()), true, 1, 0, false, dtod.getIdDobavljaca());
                //    DAOStavkaNarudzbe daosn = new DAOStavkaNarudzbe();
                upisiUbazuSveStavke(sviProizvodi);
                narudzbenica.getItems().clear();
            }
        }
    }

    public static void sendEmailWithAttachments(String host, String port,
            final String posiljalac, final String lozinka, String primalac,
            String predmet, String poruka, String[] fajlovi)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", posiljalac);
        properties.put("mail.password", lozinka);

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(posiljalac, lozinka);
            }
        };
        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(posiljalac));
        InternetAddress[] toAddresses = {new InternetAddress(primalac)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(predmet);
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(poruka, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (fajlovi != null && fajlovi.length > 0) {
            for (String filePath : fajlovi) {
                MimeBodyPart attachPart = new MimeBodyPart();

                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(attachPart);
            }
        }

        msg.setContent(multipart);

        Transport.send(msg);

    }

    // kalkulacija
    private void postaviDobavljaceZaKalkulaciju() {
        DAODobavljac daod = new DAODobavljac();
        ObservableList<DTODobavljac> listaDobavljaca;
        listaDobavljaca = daod.getDobavljace();
        for (int i = 0; i < listaDobavljaca.size(); i++) {
            dobavljacComboBox.getItems().add(listaDobavljaca.get(i).getNaziv());
        }
        kalkulacija.getItems().clear();
        narudzbaComboBox.getItems().clear();
    }
    
    public void postaviTabeluKalkulacija() {
        sifraKalkulacija.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        barKodKalkulacija.setCellValueFactory(new PropertyValueFactory<>("barKod"));
        nazivKalkulacija.setCellValueFactory(new PropertyValueFactory<>("naziv"));
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
        kalkulacija.setItems(getTabelaKalkulacija());
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

    private ObservableList<TabelaKalkulacija> getTabelaKalkulacija() {
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
    
    private void postaviTabeluProizvod() {
        barKodProizvod.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        sifraProizvod.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        nazivProizvod.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        proizvodjac.setCellValueFactory(new PropertyValueFactory<>("JIBProizvodjaca"));
        kolicinaProizvod.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        cijenaProizvod.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        duzina.setCellValueFactory(new PropertyValueFactory<>("duzina"));
        sirina.setCellValueFactory(new PropertyValueFactory<>("sirina"));
        visina.setCellValueFactory(new PropertyValueFactory<>("visina"));
        velicina.setCellValueFactory(new PropertyValueFactory<>("velicina"));
        uzrast.setCellValueFactory(new PropertyValueFactory<>("uzrast"));
        pol.setCellValueFactory(new PropertyValueFactory<>("pol"));
        boja.setCellValueFactory(new PropertyValueFactory<>("boja"));
        godisnjeDoba.setCellValueFactory(new PropertyValueFactory<>("godisnjeDoba"));
        tabela.setItems(getTabelaProizvod());
    }

    private ObservableList<TabelaDetaljanProizvod> getTabelaProizvod() {

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
       // nazivTextField.requestFocus();
        postaviTabelu();
      }


    
    private void kasaPocetno(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        datumLabel.setText(sdf.format(new Date().getTime()));
        ukupnaCijenaLabel.setText("0,00");
        DTOZaposleni k = DAOZaposleni.getZaposleniById(PocetnaFormaController.idZaposlenog);
        prodavacLabel.setText(k.getIme() + " " + k.getPrezime());
/*
        if (k.getIdTipZaposlenog() == 13) {
            nazadButton.setText("Odjava");
        }    */    
    }
    
     private void puniTabelu() {
        barkodKolona.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        sifraKolona.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        nazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        kolicinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        cijenaKolona.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        vrijednostKolona.setCellValueFactory(new PropertyValueFactory<>("vrijednost"));
        kasaTabela.getItems().add(getTabelaKasa());
        for (int i = 0; i < kasaTabela.getItems().size(); i++) {
            if (kasaTabela.getItems().get(i) == null) {
                kasaTabela.getItems().remove(i);
            }
        }
    }

    private TabelaKasa getTabelaKasa() {
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
                                    proizvod.getNaziv(), Integer.parseInt(kolicinaKasaTextField.getText()) + el.getKolicina(),
                                    proizvod.getCijena(),
                                    Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena() + el.getVrijednost())));
                            for (DTOStavka stavka : listaStavki) {
                                if (stavka.getIdProizvoda() == proizvod.getIdProizvoda()) {
                                    listaStavki.remove(stavka);
                                    break;
                                }
                            }
                            listaStavki.add(new DTOStavka(Integer.parseInt(kolicinaKasaTextField.getText()) + el.getKolicina(),
                                    Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena() + el.getVrijednost(), proizvod.getIdProizvoda()));
                            ukupno += Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena();
                            ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));

                            break;
                        }
                    }
                    indeks++;
                }
            } else {
                kasaZaVratiti = new TabelaKasa(proizvod.getBarkod(), proizvod.getSifra(),
                        proizvod.getNaziv(), Integer.parseInt(kolicinaKasaTextField.getText()),
                        proizvod.getCijena(), Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena());

                listaStavki.add(
                        new DTOStavka(Integer.parseInt(kolicinaKasaTextField.getText()),
                                Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena(), proizvod.getIdProizvoda()));
                ukupno += Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena();

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
                                    proizvod.getNaziv(), Integer.parseInt(kolicinaKasaTextField.getText()) + el.getKolicina(),
                                    proizvod.getCijena(),
                                    Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena() + el.getVrijednost())));
                            for (DTOStavka stavka : listaStavki) {
                                if (stavka.getIdProizvoda() == proizvod.getIdProizvoda()) {
                                    listaStavki.remove(stavka);
                                    break;
                                }
                            }
                            listaStavki.add(new DTOStavka(Integer.parseInt(kolicinaKasaTextField.getText()) + el.getKolicina(),
                                    Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena() + el.getVrijednost(), proizvod.getIdProizvoda()));
                            ukupno += Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena();
                            ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));

                            break;
                        }
                    }
                    indeks++;
                }
            } else {
                kasaZaVratiti = new TabelaKasa(proizvod.getBarkod(), proizvod.getSifra(),
                        proizvod.getNaziv(), Integer.parseInt(kolicinaKasaTextField.getText()),
                        proizvod.getCijena(), Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena());

                listaStavki.add(
                        new DTOStavka(Integer.parseInt(kolicinaKasaTextField.getText()),
                                Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena(), proizvod.getIdProizvoda()));
                ukupno += Integer.parseInt(kolicinaKasaTextField.getText()) * proizvod.getCijena();

                ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));
                return kasaZaVratiti;
            }
        }
        return kasaZaVratiti;

    }

    private boolean provjeraBarkoda() {
        barkod = true;
        if (!("".equals(barkodTextField.getText())) && Pattern.matches("[0-9]{13}", barkodTextField.getText())) {
            kolicinaKasaTextField.requestFocus();
            return true;
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
            kolicinaKasaTextField.requestFocus();
            return true;
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
            boolean daLiMoguAzurirati = dao.azurirajProizvod(proizvod.getKolicina() - Integer.parseInt(kolicinaKasaTextField.getText()),
                    proizvod.getIdProizvoda());
            if (daLiMoguAzurirati) {
                //  stanjeLabel.setText((proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText())) + "");
                return proizvod.getKolicina() - Integer.parseInt(kolicinaKasaTextField.getText());
            }
        } else {
            proizvod = dao.getProizvodPoSifri(sifraTextField.getText());
            boolean daLiMoguAzurirati = dao.azurirajProizvod(proizvod.getKolicina() - Integer.parseInt(kolicinaKasaTextField.getText()),
                    proizvod.getIdProizvoda());

            /* boolean azuriranjeSkladista = daoSkladiste.azurirajProizvodUSkladistu(proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText()),
                    proizvod.getIdProizvoda());*/
            if (daLiMoguAzurirati) {
                //  stanjeLabel.setText((proizvod.getKolicina() - Integer.parseInt(kolicinaTextField.getText())) + "");
                return proizvod.getKolicina() - Integer.parseInt(kolicinaKasaTextField.getText());
            }
        }
        stanjeLabel.setText(proizvod.getKolicina() + "");
        return -1;
    }

    @FXML
    public void klikNaKolicinu() {
        if (!("".equals(kolicinaKasaTextField.getText()))) {

            Integer.parseInt(kolicinaKasaTextField.getText());
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
                    kolicinaKasaTextField.setText("1");
                } else {
                    kolicinaKasaTextField.setText("1");
                    barkodTextField.clear();
                    sifraTextField.clear();
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Nemate dovoljno proizvoda na stanju.");
                }
            } catch (NumberFormatException e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Unesite broj.");
            }
        }
    }

    @FXML
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
            attachFiles[0] = PDF.kreirajFajlRacun(listaStavki, new DAORacun().vratiRacunPoId(idRacuna));
            ukupno = 0;
            ukupnaCijenaLabel.setText("0,00");
            stanjeLabel.setText("");
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

    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
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
}
