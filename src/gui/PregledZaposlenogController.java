package gui;

import babyshop.AlertHelper;
import dao.DAOZaposleni;
import dto.DTOZaposleni;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PregledZaposlenogController implements Initializable {

    @FXML
    private TableView<DTOZaposleni> zaposleniTableView;
    @FXML
    private TableColumn<DTOZaposleni, String> imeZaposlenogTableColumn;
    @FXML
    private TableColumn<DTOZaposleni, String> prezimeZaposlenogTableColumn;
    @FXML
    private TableColumn<DTOZaposleni, String> jmbgZaposlenogTableColumn;

    ObservableList<DTOZaposleni> zaposleni;
    static PregledZaposlenogController myController;

    /**
     * Initializes the controller class.
     */
    public PregledZaposlenogController() {
        this.zaposleni = FXCollections.observableArrayList(DAOZaposleni.getZaposleni());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       myController = this;

        imeZaposlenogTableColumn.setCellValueFactory(new PropertyValueFactory("ime"));
        prezimeZaposlenogTableColumn.setCellValueFactory(new PropertyValueFactory("prezime"));
        jmbgZaposlenogTableColumn.setCellValueFactory(new PropertyValueFactory("JMBG"));

        zaposleniTableView.setItems(zaposleni);

    }

    @FXML
    private void izmjenaButtonOnAction(ActionEvent event) throws IOException {
        if (zaposleniTableView.getSelectionModel().isEmpty()) {

            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali zaposlenog.");
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/izmjenaZaposlenog.fxml"));
            Parent tableViewParent = loader.load();

            Scene detaljanScene = new Scene(tableViewParent);

            //access the controller and call a method
            IzmjenaZaposlenogController controller = loader.getController();
            controller.popuniPrikaz(zaposleniTableView.getSelectionModel().getSelectedItem());

            Stage window = new Stage();
            window.resizableProperty().setValue(Boolean.FALSE);
            window.setTitle("Izmjena zaposlenog");
            window.setScene(detaljanScene);
            window.centerOnScreen();
            window.initModality(Modality.APPLICATION_MODAL);
            window.showAndWait();
            zaposleni = FXCollections.observableArrayList(DAOZaposleni.getZaposleni());
            zaposleniTableView.setItems(zaposleni);
        }
    }

    @FXML
    private void detaljanPrikazButtonOnAction(ActionEvent event) throws IOException {
        if (zaposleniTableView.getSelectionModel().isEmpty()) {

            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali zaposlenog.");
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/detaljanPrikazZaposlenog.fxml"));
            Parent tableViewParent = loader.load();

            Scene detaljanScene = new Scene(tableViewParent);

            //access the controller and call a method
            DetaljanPrikazZaposlenogController controller = loader.getController();
            controller.popuniPrikaz(zaposleniTableView.getSelectionModel().getSelectedItem());

            Stage window = new Stage();
            window.resizableProperty().setValue(Boolean.FALSE);
            window.setTitle("Detaljan prikaz zaposlenog");
            window.setScene(detaljanScene);
            window.centerOnScreen();
            window.initModality(Modality.APPLICATION_MODAL);
            window.showAndWait();
        }
    }

    @FXML
    private void dodajNovogZaposlenogButtonOnAction(ActionEvent event) throws IOException {

        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosZaposlenog.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.setTitle("Unos zaposlenog");
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
        zaposleni = FXCollections.observableArrayList(DAOZaposleni.getZaposleni());
        zaposleniTableView.setItems(zaposleni);
        // PregledKorisnickogNalogaController.korisnickiNalogTableView.refresh();

    }

      public void refresh(){
        this.zaposleni = FXCollections.observableArrayList(DAOZaposleni.getZaposleni());
        zaposleniTableView.setItems(zaposleni);
    }
}
