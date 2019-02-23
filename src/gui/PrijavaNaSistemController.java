/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.DAOKorisnickiNalog;
import dto.DTOKorisnickiNalog;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bouncycastle.operator.OperatorCreationException;

public class PrijavaNaSistemController implements Initializable {

    @FXML
    private JFXPasswordField lozinkaField;
    @FXML
    private JFXTextField korisnickoImeTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for (DTOKorisnickiNalog x : DAOKorisnickiNalog.getKorisnickiNalozi()) {

        }
    }

    @FXML
    private void prijavaButtonOnAction(ActionEvent event) throws CertificateException,
            NoSuchAlgorithmException,
            FileNotFoundException,
            InvalidKeySpecException,
            OperatorCreationException,
            IOException {

        if (korisnickoImeTextField.getText().equals("") || lozinkaField.getText().equals("")) {

            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Unesite podatke za prijavu na sistem.");

        } else {

            boolean nepostojeciNalog = false;
            boolean pogresnaLozinka = false;
            String unesenaLozinkaUFormu = lozinkaField.getText();
            String korisnickoIme = korisnickoImeTextField.getText();
            DTOKorisnickiNalog nalog = DAOKorisnickiNalog.getKorisnickiNalozi().stream().filter(x -> korisnickoIme.equals(x.getKorisnickoIme())).findAny().orElse(null);
            if (nalog != null) {
                PocetnaFormaController.idZaposlenog = nalog.getIdZaposlenog();

                int byteCounter = 0;
                byte[] hesLozinkeIzdvojenIzBaze = new byte[32];
                byte[] hesLozinkeISaltIzBaze = nalog.getLozinka();
                int duzinaSalta = Math.abs(hesLozinkeISaltIzBaze.length - hesLozinkeIzdvojenIzBaze.length);
                byte[] salt = new byte[duzinaSalta];

                for (byteCounter = 0; byteCounter < 32; byteCounter++) {
                    //ucitavamo prvi dio iz polja lozinka iz baze tj hesiranu lozinku
                    hesLozinkeIzdvojenIzBaze[byteCounter] = hesLozinkeISaltIzBaze[byteCounter];
                }
                for (int i = byteCounter, j = 0; (i < hesLozinkeISaltIzBaze.length) && (j < duzinaSalta); i++, j++) {
                    //ucitavamo drugi dio iz lozinka baze tj salt
                    salt[j] = hesLozinkeISaltIzBaze[i];
                }
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                digest.reset();
                //dodamo salt koji smo ucitali iz baze u algoritam za hesiranje
                digest.update(salt);
                //Izgenerisan novi hes sa lozinke unesene na login formi
                byte[] hesiranaUnesenaLozinka = digest.digest(unesenaLozinkaUFormu.getBytes());
                //uporedimo hes iz baze sa novim hesom
                if (!Arrays.equals(hesLozinkeIzdvojenIzBaze, hesiranaUnesenaLozinka)) {

                    pogresnaLozinka = true;
                    AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Unijeli ste pogrešnu lozinku. Molim Vas, pokušajte ponovo.");

                } else {
                    System.out.println("Administrator".equals(nalog.getTipKorisnika()));
                    System.out.println(nalog.getTipKorisnika());

                    //AlertHelper.showAlert(Alert.AlertType.INFORMATION, "", "Uspješna prijava.");
                    if ("Administrator".equals(nalog.getTipKorisnika())) {
                        ((Node) event.getSource()).getScene().getWindow().hide();
                        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/adminFormaSva.fxml"));
                        Stage window = new Stage();
                        Scene korisnikScena = new Scene(korisnikView);
                        window.setScene(korisnikScena);
                        window.centerOnScreen();
                        window.initModality(Modality.APPLICATION_MODAL);
                        window.showAndWait();
                    } else if ("Poslovođa".equals(nalog.getTipKorisnika())) {
                        ((Node) event.getSource()).getScene().getWindow().hide();

                        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/poslovodja.fxml"));
                        Stage window = new Stage();
                        Scene korisnikScena = new Scene(korisnikView);
                        window.resizableProperty().setValue(Boolean.FALSE);
                        window.setScene(korisnikScena);
                        window.centerOnScreen();
                        window.initModality(Modality.APPLICATION_MODAL);
                        window.showAndWait();
                    } else if ("Kasir".equals(nalog.getTipKorisnika())) {
                        ((Node) event.getSource()).getScene().getWindow().hide();

                        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/kasa.fxml"));
                        Stage window = new Stage();
                        Scene korisnikScena = new Scene(korisnikView);
                        window.resizableProperty().setValue(Boolean.FALSE);
                        window.setScene(korisnikScena);
                        window.centerOnScreen();
                        window.initModality(Modality.APPLICATION_MODAL);
                        window.showAndWait();
                    }
                }
            } else {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Unijeli ste nepostojeći nalog. Molim Vas, pokušajte ponovo.");

            }
        }
    }

}
