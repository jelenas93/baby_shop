/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAOKorisnickiNalog;
import dao.DAOKorisnikWrapper;
import dto.DTOKorisnickiNalog;
import dto.DTOKorisnikWrapper;
import dto.DTOTipZaposlenog;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tijana Lakic
 */
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

    /**
     * Initializes the controller class.
     */
    public PregledKorisnickogNalogaController() {
        this.nalozi = FXCollections.observableArrayList(DAOKorisnikWrapper.getKorisnickiNaloziWrappers());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        korisnickoImeTableColumn.setCellValueFactory(new PropertyValueFactory("korisnickoIme"));
        imeZaposlenogTableColumn.setCellValueFactory(new PropertyValueFactory("ime"));
        prezimeZaposlenogTableColumn.setCellValueFactory(new PropertyValueFactory("prezime"));
        jmbgZaposlenogTableColumn.setCellValueFactory(new PropertyValueFactory("JMBG"));
        tipNalogaTableColumn.setCellValueFactory(new PropertyValueFactory("tipKorisnika"));

        korisnickiNalogTableView.setItems(nalozi);

    }

    @FXML
    private void izmjenaButtonOnAction(ActionEvent event) throws IOException {
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
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
        nalozi = FXCollections.observableArrayList(DAOKorisnikWrapper.getKorisnickiNaloziWrappers());
        korisnickiNalogTableView.setItems(nalozi);

    }

    @FXML
    private void deaktivirajNalogButtonOnAction(ActionEvent event) {
        DTOKorisnikWrapper nalog = korisnickiNalogTableView.getSelectionModel().getSelectedItem();
        DAOKorisnickiNalog.deaktivirajNalog(nalog.getIdNaloga(), nalog.getIdZaposlenog());
        korisnickiNalogTableView.refresh();
    }

}
