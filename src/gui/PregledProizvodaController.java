package gui;

import dao.DAOProizvod;
import dto.DTOProizvod;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tabele.TabelaProizvod;

public class PregledProizvodaController implements Initializable {

    @FXML
    private TableView<TabelaProizvod> tabela;

    @FXML
    private TableColumn<TabelaProizvod, Integer> redniBrojKolona;

    @FXML
    private TableColumn<TabelaProizvod, String> barkodKolona;

    @FXML
    private TableColumn<TabelaProizvod, String> sifraKolona;

    @FXML
    private TableColumn<TabelaProizvod, String> nazivKolona;

    @FXML
    private TableColumn<TabelaProizvod, Integer> kolicinaKolona;

    @FXML
    private TableColumn<TabelaProizvod, Double> cijenaKolona;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabela.getItems().add(new TabelaProizvod());
        postaviTabelu();
    }

    private void postaviTabelu() {
        redniBrojKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
        barkodKolona.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        sifraKolona.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        nazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        kolicinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        cijenaKolona.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        tabela.setItems(getTabela());
    }

    private ObservableList<TabelaProizvod> getTabela() {
        ObservableList<DTOProizvod> lista = new DAOProizvod().getProizvode();

        List<TabelaProizvod> listaMoja = new ArrayList<>();
        for (DTOProizvod proizvod : lista) {
            listaMoja.add(new TabelaProizvod(proizvod.getIdProizvoda(),
                    proizvod.getBarkod(), proizvod.getSifra(), proizvod.getNaziv(),
                    proizvod.getKolicina(), proizvod.getCijena()));
        }
        ObservableList<TabelaProizvod> listaZaPrikaz = FXCollections.observableArrayList();
        for (TabelaProizvod proizvod : listaMoja) {
            listaZaPrikaz.add(proizvod);
        }
        return listaZaPrikaz;
    }

    public void detaljnoStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/PregledProizvodaDetaljno.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        //window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }
}
