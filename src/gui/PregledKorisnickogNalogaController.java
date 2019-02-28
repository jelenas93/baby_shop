package gui;

import babyshop.AlertHelper;
import dao.DAOKorisnickiNalog;
import dao.DAOKorisnikWrapper;
import dto.DTOKorisnikWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PregledKorisnickogNalogaController implements Initializable {

    @FXML
    private TableView<DTOKorisnikWrapper> korisnickiNalogTableView;
    @FXML
    private TableColumn<DTOKorisnikWrapper, String> korisnickoImeTableColumn;
    @FXML
    private TableColumn<DTOKorisnikWrapper, String> imeZaposlenogTableColumn;
    @FXML
    private TableColumn<DTOKorisnikWrapper, String> prezimeZaposlenogTableColumn;
    @FXML
    private TableColumn<DTOKorisnikWrapper, String> jmbgZaposlenogTableColumn;

    @FXML
    private TableColumn<DTOKorisnikWrapper, String> tipNalogaTableColumn;
    ObservableList<DTOKorisnikWrapper> nalozi;

     static PregledKorisnickogNalogaController myController;

    public PregledKorisnickogNalogaController() {
      this.nalozi = FXCollections.observableArrayList(DAOKorisnikWrapper.getKorisnickiNaloziWrappers());
    }

    public void postavi(){
        korisnickoImeTableColumn.setCellValueFactory(new PropertyValueFactory("korisnickoIme"));
        imeZaposlenogTableColumn.setCellValueFactory(new PropertyValueFactory("ime"));
        prezimeZaposlenogTableColumn.setCellValueFactory(new PropertyValueFactory("prezime"));
        jmbgZaposlenogTableColumn.setCellValueFactory(new PropertyValueFactory("JMBG"));
        tipNalogaTableColumn.setCellValueFactory(new PropertyValueFactory("tipKorisnika"));

        korisnickiNalogTableView.setItems(nalozi);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myController = this;
        postavi();

    }
@FXML
    private void izmjenaButtonOnAction(ActionEvent event) throws IOException {

        if (korisnickiNalogTableView.getSelectionModel().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali korisnika.");

        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/izmjenaKorisnickogNaloga.fxml"));
            Parent tableViewParent = loader.load();

            Scene detaljanScene = new Scene(tableViewParent);

            //access the controller and call a method
            IzmjenaKorisnickogNalogaController controller = loader.getController();
            controller.popuniPrikaz(korisnickiNalogTableView.getSelectionModel().getSelectedItem());

            //moje
            Stage window = new Stage();
            window.resizableProperty().setValue(Boolean.FALSE);
            window.setScene(detaljanScene);
            window.setTitle("Izmjena korisničkog naloga");
            window.centerOnScreen();
            window.initModality(Modality.APPLICATION_MODAL);
            window.showAndWait();
            nalozi = FXCollections.observableArrayList(DAOKorisnikWrapper.getKorisnickiNaloziWrappers());
            korisnickiNalogTableView.setItems(nalozi);
        }
    }

    @FXML
    private void deaktivirajNalogButtonOnAction(ActionEvent event) {
        if (korisnickiNalogTableView.getSelectionModel().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali korisnika.");

        } else {
            DTOKorisnikWrapper nalog = korisnickiNalogTableView.getSelectionModel().getSelectedItem();
            DAOKorisnickiNalog.deaktivirajNalog(nalog.getIdNaloga(), nalog.getIdZaposlenog());
            nalozi = FXCollections.observableArrayList(DAOKorisnikWrapper.getKorisnickiNaloziWrappers());
            korisnickiNalogTableView.setItems(nalozi);
            korisnickiNalogTableView.refresh();
        }
    }

    private void dodajNoviNalogButtonOnAction(ActionEvent event) throws IOException {

        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosKorisnickogNaloga.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.setTitle("Unos korisničkog naloga");
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }

    public void refresh(){
        nalozi = FXCollections.observableArrayList(DAOKorisnikWrapper.getKorisnickiNaloziWrappers());
        korisnickiNalogTableView.setItems(nalozi);
    }
}
