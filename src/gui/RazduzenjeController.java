package gui;

import dao.DAORacun;
import dto.DTORacun;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tabele.TabelaPazar;

public class RazduzenjeController implements Initializable {

    @FXML
    private Label pazarLabel;

    @FXML
    private Label blagajnikLabel;

    @FXML
    private TableView<TabelaPazar> pazarTabela;

    @FXML
    private TableColumn<TabelaPazar, String> tipRacunaKolona;

    @FXML
    private TableColumn<TabelaPazar, Double> iznosKolona;

    @FXML
    private Button razduziButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipRacunaKolona.setCellValueFactory(new PropertyValueFactory<>("tipRacuna"));
        iznosKolona.setCellValueFactory(new PropertyValueFactory<>("iznos"));
        pazarTabela.getItems().add(getTabela());
    }

    private TabelaPazar getTabela() {

       // ObservableList<TabelaPazar> listaZaPrikaz = FXCollections.observableArrayList();
       
        List<TabelaPazar> listaMoja = new ArrayList<>();

        DAORacun dao = new DAORacun();
        double ukupnaCijena = 0;
        ObservableList<DTORacun> lista = dao.getRacunePoDatumu(new java.sql.Date(new Date().getTime()));
        for (DTORacun racun : lista) {
            ukupnaCijena += racun.getUkupnaCijena();
        }
        //listaMoja.add(new TabelaPazar("REDOVAN RAČUN", ukupnaCijena));
       // return listaMoja;
       return new TabelaPazar("REDOVAN RAČUN", ukupnaCijena);
    }
}
