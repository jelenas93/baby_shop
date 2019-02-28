package gui;

import babyshop.AlertHelper;
import dao.DAORacun;
import dao.DAORazduzenje;
import dao.DAOZaposleni;
import dto.DTORacun;
import dto.DTOZaposleni;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tabele.TabelaPazar;

public class RazduzenjeController implements Initializable {

    @FXML
    private Label pazarLabel, blagajnikLabel, datumLabel;

    @FXML
    private TableView<TabelaPazar> pazarTabela;

    @FXML
    private TableColumn<TabelaPazar, String> tipRacunaKolona;

    @FXML
    private TableColumn<TabelaPazar, Double> iznosKolona;

    private DAORazduzenje dao = new DAORazduzenje();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pazarLabel.setText("" + nadjiBrojPazara());
        DTOZaposleni k = DAOZaposleni.getZaposleniById(PrijavaNaSistemController.idZaposlenog);
        blagajnikLabel.setText(""+k.getIme() + " " + k.getPrezime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        datumLabel.setText(" "+sdf.format(new Date().getTime()));
        tipRacunaKolona.setCellValueFactory(new PropertyValueFactory<>("tipRacuna"));
        iznosKolona.setCellValueFactory(new PropertyValueFactory<>("iznos"));
        pazarTabela.getItems().add(getTabela());
    }

    private int nadjiBrojPazara() {
        return dao.idZadnjegRazduzenja();
    }

    private TabelaPazar getTabela() {
        List<TabelaPazar> listaMoja = new ArrayList<>();

        DAORacun dao = new DAORacun();
        double ukupnaCijena = 0;
        ObservableList<DTORacun> lista = dao.getRacunePoDatumu(new java.sql.Date(new Date().getTime()));
        for (DTORacun racun : lista) {
            ukupnaCijena += racun.getUkupnaCijena();
        }
        return new TabelaPazar("REDOVAN RAČUN", ukupnaCijena);
    }

    @FXML
    private void razduzi(ActionEvent event) {
        if (!dao.provjeraRazduzenjaPoDatumu(new java.sql.Date(new Date().getTime()))) {
            if (!dao.upisiRazduzenjeUBazu(new java.sql.Date(new Date().getTime()), pazarTabela.getItems().get(0).getIznos(), pazarTabela.getItems().get(0).getTipRacuna())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom razduživanja kase.");
            } else {
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Već ste razdužili kasu.");
        }
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
}
