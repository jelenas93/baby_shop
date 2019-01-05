package gui;

import babyshop.AlertHelper;
import dao.DAOProizvod;
import dto.DTOProizvod;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        kasaTabela.getItems().add(new TabelaKasa());
        datumLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        ukupnaCijenaLabel.setText("0,0");
    }

    private void puniTabelu() {
        barkodKolona.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        sifraKolona.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        nazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        kolicinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        cijenaKolona.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        vrijednostKolona.setCellValueFactory(new PropertyValueFactory<>("vrijednost"));
        kasaTabela.getItems().add(getTabela());
    }

    private TabelaKasa getTabela() {
        DTOProizvod proizvod = null;
        if (barkod) {
            proizvod = new DAOProizvod().getProizvodPoBarkodu(barkodTextField.getText());
        } else {
            proizvod = new DAOProizvod().getProizvodPoSifri(sifraTextField.getText());
        }
        TabelaKasa tabelaKasa = new TabelaKasa(proizvod.getBarkod(), proizvod.getSifra(),
                proizvod.getNaziv(), Integer.parseInt(kolicinaTextField.getText()),
                proizvod.getCijena(), new Double(Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena()));
        ukupno += Integer.parseInt(kolicinaTextField.getText()) * proizvod.getCijena();
        ukupnaCijenaLabel.setText(String.format("%.2f", ukupno));
        return tabelaKasa;
    }

    private boolean provjeraBarkoda() {
        barkod = true;
        if (!"".equals(barkodTextField.getText()) && Pattern.matches("[0-9]{13}", barkodTextField.getText())) {
            kolicinaTextField.requestFocus();
            return true;
        }
        return false;
    }

    public void barkodUnos() {
        pozvanaMetodaBarkod = provjeraBarkoda();
        barkod = false;
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
}
