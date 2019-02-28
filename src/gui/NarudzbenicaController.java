package gui;

import babyshop.AlertHelper;
import dao.DAODobavljac;
import dao.DAONarudzbenica;
import dao.DAOProizvod;
import dao.DAOProizvodjac;
import dao.DAOStavkaNarudzbe;
import dto.DTODobavljac;
import dto.DTODobavljacProizvod;
import dto.DTOProizvod;
import java.io.FileWriter;
import java.io.IOException;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import static com.itextpdf.kernel.pdf.PdfName.Table;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import tabele.TabelaDetaljanProizvod;
import tabele.TabelaNarudzba;
import tabele.TabelaNarudzbenica;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class NarudzbenicaController implements Initializable {

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
    private TextField KolicinaTextField;

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
    private HashMap<String,Integer >listaProizvoda  =  new HashMap<String,Integer> ();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        listaProizvoda.clear();
        ObservableList<TabelaNarudzba> listaZaPrikaz = FXCollections.observableArrayList();
        List<TabelaNarudzba> listaMoja = new ArrayList<>();
        DAODobavljac daod = new DAODobavljac();
        DTODobavljac dtod = daod.getDobavljacPoNazivu(imeDobavljacaComboBox.getSelectionModel().getSelectedItem());
        int IdDobavljaca = dtod.getIdDobavljaca();
        DAOProizvod daop = new DAOProizvod();
        ObservableList<DTOProizvod> lista = daop.getSveProizvodeOdDobavljaca(IdDobavljaca);
        for (DTOProizvod proizvod : lista) {
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
            Integer.parseInt(KolicinaTextField.getText());
        } catch (NumberFormatException e) {
            pogresno = true;
        }

        if ("".equals(KolicinaTextField.getText()) || (pogresno) || ((Integer.parseInt(KolicinaTextField.getText())) < 0)) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste ispravno unijeli kolicinu.");
            KolicinaTextField.setText("");
        } else if (selektovano.size() == 0) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Nista nije izabrano.");
        } else {
            if ( !listaProizvoda.containsKey(selektovano.get(0).getBarKod())) {  
                   
                   listaProizvoda.put(selektovano.get(0).getBarKod(),Integer.valueOf(KolicinaTextField.getText()));
                    int kolicina = Integer.valueOf(KolicinaTextField.getText());
            String barkod = selektovano.get(0).getBarKod();
           
            String sifraa = selektovano.get(0).getSifra();
            String naziiv = selektovano.get(0).getNaziv();

            TabelaNarudzbenica novo = new TabelaNarudzbenica(sifraa, naziiv, kolicina, barkod);
            narudzbenica.getItems().add(novo);
            KolicinaTextField.clear();
                   }
                   else  {  
                   
                   listaProizvoda.computeIfPresent(selektovano.get(0).getBarKod(), (k, v) -> (v + (Integer.valueOf(KolicinaTextField.getText()))) );
                 List<TabelaNarudzbenica> tabela = narudzbenica.getItems();
                   for (TabelaNarudzbenica el : tabela) {
              
                        if (el.getBarKod().equals( selektovano.get(0).getBarKod() ) ) { 
                            int staraKolicina = el.getNaruceno();
                             String barkod = selektovano.get(0).getBarKod();
           
            String sifraa = selektovano.get(0).getSifra();
            String naziiv = selektovano.get(0).getNaziv();

                            narudzbenica.getItems().remove(el);
                            narudzbenica.getItems().add(new TabelaNarudzbenica(sifraa, naziiv, (staraKolicina+ Integer.valueOf(KolicinaTextField.getText())), barkod));
                   
                                    
                    }
                
                }
            
            KolicinaTextField.clear();
        
                   
                   }
          
            
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
    
     @FXML
    public void ponisti(ActionEvent event) {
        ObservableList<TabelaNarudzbenica> sviProizvodi;
        sviProizvodi = narudzbenica.getSelectionModel().getSelectedItems();
        sviProizvodi = narudzbenica.getItems();
        sviProizvodi.clear();
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

                String primalac = dtod.getEmail();
            String host = "smtp.gmail.com";
            String port = "587";
            String posiljalac = "shopbaby273@gmail.com";
            String lozinka = "babyshop273";

         //   String primalac = "jelenas9393@gmail.com";
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

    public void nazadStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/PocetnaForma.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

}
