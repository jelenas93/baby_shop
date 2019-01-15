/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.Date;
import java.util.Properties;
 
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
/**
 * FXML Controller class
 *
 * @author Jovana Trkulja
 */
public class NarudzbenicaController implements Initializable {

    /**
     * Initializes the controller class.
     */
   



    @FXML
    private TableView<TabelaNarudzba> narudzba;

    @FXML
    private TableColumn<TabelaNarudzba,String> sifra;

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
    private TableColumn<TabelaNarudzbenica,String> sifraNarucenog;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         DAODobavljac daod = new DAODobavljac();
        ObservableList<DTODobavljac> listaDobavljaca;
        listaDobavljaca = daod.dobavljaci();
        for (int i = 0; i < listaDobavljaca.size(); i++) {
            imeDobavljacaComboBox.getItems().add(listaDobavljaca.get(i).getNaziv());
        }
        postaviTabelu();
    }    
  
    private void postaviTabelu() {
        
        sifra.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        barKod.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        stanje.setCellValueFactory(new PropertyValueFactory<>("stanje"));
        cijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        
        
        sifraNarucenog.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        barKodNarucenog.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        nazivNarucenog.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        naruceno.setCellValueFactory(new PropertyValueFactory<>("naruceno"));
        
         imeDobavljacaComboBox.getSelectionModel().selectedItemProperty().addListener((v,staraVrijednost,novaVrijednost) -> narudzba.setItems(getTabela()) );
        
        
    }
    
    
     private ObservableList<TabelaNarudzba> getTabela() {

       narudzbenica.getItems().clear();
   
        ObservableList<TabelaNarudzba> listaZaPrikaz = FXCollections.observableArrayList();
        List<TabelaNarudzba> listaMoja = new ArrayList<>();
       
           DAODobavljac daod = new DAODobavljac();  
           DTODobavljac dtod = daod.getDobavljacPoNazivu(imeDobavljacaComboBox.getSelectionModel().getSelectedItem());
           int IdDobavljaca = dtod.getIdDobavljaca();
           
     
           DAOProizvod daop= new DAOProizvod();
             ObservableList<DTOProizvod> lista = daop.getSveProizvodeOdDobavljaca(IdDobavljaca);
            for (DTOProizvod proizvod : lista) {
                listaMoja.add(new TabelaNarudzba(proizvod.getSifra(), proizvod.getBarkod(), proizvod.getNaziv(),proizvod.getCijena(),
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
        
         if ("".equals(KolicinaTextField.getText()) ) {  
          AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli kolicinu.");
         }
         else {
     ObservableList<TabelaNarudzba> selektovano;
    selektovano= narudzba.getSelectionModel().getSelectedItems();
    int kolicina =Integer.valueOf( KolicinaTextField.getText());
    String barKod = selektovano.get(0).getBarKod();
     String sifra = selektovano.get(0).getSifra(); 
      String naziv = selektovano.get(0).getNaziv();  
      
      TabelaNarudzbenica novo = new TabelaNarudzbenica(sifra, naziv, kolicina,barKod); 
      narudzbenica.getItems().add(novo);
    KolicinaTextField.clear();
    
         }
    }
    
     @FXML
    public void ukloniArtiklButtonOnAction(ActionEvent event) {
        
    ObservableList<TabelaNarudzbenica> selektovano, sviProizvodi;
    selektovano= narudzbenica.getSelectionModel().getSelectedItems(); 
    if ( selektovano.size() == 0) { AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali artikl."); }
    else {
        sviProizvodi=narudzbenica.getItems();
    selektovano.forEach(sviProizvodi::remove);
    }
    
    }
    
     @FXML       
      public void posaljiNarudzbuButtonOnAction(ActionEvent event) {  
          
          ObservableList<TabelaNarudzbenica>  sviProizvodi;
    sviProizvodi= narudzbenica.getItems(); 
    if ( sviProizvodi.size() == 0) { AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Narudzbenica je prazna."); }

    else {
     FileWriter fileWriter = null;
     String heder ="  Sifra  ,  Bar Kod  ,  Naziv  ,  Naruceno  ";
    String noviRed = "\n";
    String delimiter = "  ,  ";
  
    try {
        
        
                fileWriter = new FileWriter("narudzbenica.csv");
                 fileWriter.append(heder.toString());
                fileWriter.append(noviRed);
                
                 for (int i = 0; i < sviProizvodi.size(); i++) {
                

	                fileWriter.append(String.valueOf(sviProizvodi.get(i).getSifra()));
	                fileWriter.append(delimiter);
	                fileWriter.append(String.valueOf(sviProizvodi.get(i).getBarKod()));
	                fileWriter.append(delimiter);
	                fileWriter.append(String.valueOf(sviProizvodi.get(i).getNaziv()));
	                fileWriter.append(delimiter);
	                fileWriter.append(Integer.toString(sviProizvodi.get(i).getNaruceno()));
	                fileWriter.append(noviRed);}
               
            } catch (IOException ex) {
                Logger.getLogger(TabelaNarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
	            try {

	                fileWriter.flush();
	                fileWriter.close();
	            } catch (IOException e) {
	                System.out.println("Greska prilikom zatvaranja fajla !!!");
	                e.printStackTrace();
	            }	            
	
    
    }      
           DAODobavljac daod = new DAODobavljac(); 
           DTODobavljac dtod = daod.getDobavljacPoNazivu(imeDobavljacaComboBox.getSelectionModel().getSelectedItem());
           String mejlDobavljaca = dtod.getEmail();
           
           
           String host = "smtp.gmail.com";
        String port = "587";
        String posiljalac = "shopbaby273@gmail.com";
        String lozinka = "babyshop273";
 
        
        String primalac = "jelenas9393@gmail.com";
        String predmet = "Novi mejl od Jovane";
        String poruka = "Nova narudzba";
 
        
        String[] attachFiles = new String[1];
        attachFiles[0] = "narudzbenica.csv";
        
 
        try {
            sendEmailWithAttachments(host, port, posiljalac, lozinka, primalac,
                predmet, poruka, attachFiles);
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Narudzba je poslata dobavljacu.");
        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Narudzba nije poslata.");
            ex.printStackTrace();
        
    }
           
           
           DAONarudzbenica daon= new DAONarudzbenica();
           
           
           daon.upisiNarudzbenicuUBazu(new java.sql.Date(new Date().getTime()), true, 1, 0);
           DAOStavkaNarudzbe daosn = new DAOStavkaNarudzbe();
           daosn.upisiUbazuSveStavke(sviProizvodi);
           
    
    
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
        InternetAddress[] toAddresses = { new InternetAddress(primalac) };
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
    
}