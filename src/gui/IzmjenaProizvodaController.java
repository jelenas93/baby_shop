/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAODobavljac;
import dao.DAODobavljacProizvod;
import dao.DAOGrupaProizvod;
import dao.DAOMaterijal;
import dao.DAOProizvod;
import dao.DAOProizvodjac;
import dao.DAOSkladisteProizvod;
import dto.DTODobavljac;
import dto.DTOMaterijal;
import dto.DTOProizvod;
import dto.DTOProizvodGrupa;
import dto.DTOProizvodjac;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IzmjenaProizvodaController implements Initializable {

    @FXML
    private JFXComboBox<String> materijaliComboBox;
    @FXML
    private JFXComboBox<String> tipProizvodaComboBox;
    @FXML
    private JFXComboBox<String> JIBProizvodjacaComboBox;
    @FXML
    private JFXComboBox<String> dobavljacComboBox;
    @FXML
    private JFXTextField nazivProizvodaTextField;
    @FXML
    private VBox vboxLabel /*= new VBox()*/;
    @FXML
    private VBox vboxTextField /*= new VBox()*/;
    @FXML
    private final Label duzinaLabel = new Label("Dužina: ");
    @FXML
    private final Label sirinaLabel = new Label("Širina: ");
    @FXML
    private final Label visinaLabel = new Label("Visina: ");
    @FXML
    private final Label velicinaLabel = new Label("Veličina: ");
    @FXML
    private final Label uzrastLabel = new Label("Uzrast: ");
    @FXML
    private final Label polLabel = new Label("Pol: ");
    @FXML
    private final Label bojaLabel = new Label("Boja: ");
    @FXML
    private final Label godisnjeDobaLabel = new Label("Godišnje doba: ");

    @FXML
    private JFXTextField duzinaTextField = new JFXTextField();
    @FXML
    private JFXTextField sirinaTextField = new JFXTextField();
    @FXML
    private JFXTextField visinaTextField = new JFXTextField();
    @FXML
    private JFXTextField velicinaTextField = new JFXTextField();
    @FXML
    private JFXTextField uzrastTextField = new JFXTextField();
    @FXML
    private JFXComboBox<String> polComboBox = new JFXComboBox();
    @FXML
    private JFXComboBox<String> bojaComboBox = new JFXComboBox();
    @FXML
    private JFXComboBox<String> godisnjeDobaComboBox = new JFXComboBox();
    private int idGrupe;

    private double duzina = 0.0, sirina = 0.0, visina = 0.0;
    private Double cijena = new Double(0);
    private int velicina = 0, uzrast = 0, kolicina = 0;
    private String pol = "", boja = "", godisnjeDoba = "";

    public static DTOProizvod proizvodZaIzmjenu;

    private void popuniMaterijale() {
        DAOMaterijal daoMaterijal = new DAOMaterijal();
        ObservableList<DTOMaterijal> listaMaterijala;
        listaMaterijala = daoMaterijal.getMaterijal();
        materijaliComboBox.getItems().clear();
        for (int i = 0; i < listaMaterijala.size(); i++) {
            materijaliComboBox.getItems().add(listaMaterijala.get(i).getNaziv());
        }
    }

    private void popuniProizvodjace() {
        DAOProizvodjac dAOProizvodjac = new DAOProizvodjac();
        ObservableList<DTOProizvodjac> listaProizvodjaca;
        listaProizvodjaca = dAOProizvodjac.getProizvodjace();
        JIBProizvodjacaComboBox.getItems().clear();
        for (int i = 0; i < listaProizvodjaca.size(); i++) {
            JIBProizvodjacaComboBox.getItems().add(listaProizvodjaca.get(i).getNaziv() + ", " + listaProizvodjaca.get(i).getJIBProizvodjaca());
        }
    }

    private void popuniDobavljace() {
        DAODobavljac dAODobavljac = new DAODobavljac();
        ObservableList<DTODobavljac> listaDobavljaca;
        listaDobavljaca = dAODobavljac.getDobavljace();
        dobavljacComboBox.getItems().clear();
        for (int i = 0; i < listaDobavljaca.size(); i++) {
            dobavljacComboBox.getItems().add(listaDobavljaca.get(i).getNaziv() + ", " + listaDobavljaca.get(i).getJIBDobavljaca());
        }
    }

    private void popuniProizvode() {
        DAOGrupaProizvod daoTip = new DAOGrupaProizvod();
        ObservableList<DTOProizvodGrupa> listaProizvoda;
        listaProizvoda = daoTip.getGrupeProizvoda();
        tipProizvodaComboBox.getItems().clear();
        for (int i = 0; i < listaProizvoda.size(); i++) {
            tipProizvodaComboBox.getItems().add(listaProizvoda.get(i).getNazivTipaProizvoda());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popuniMaterijale();
        popuniProizvode();
        popuniProizvodjace();
        popuniDobavljace();
    }

    public void dodajProizvodjaca(ActionEvent event) throws IOException {
        FXMLLoader korisnikView = new FXMLLoader(getClass().getResource("/gui/unosProizvodjaca.fxml"));
        Parent root = (Parent) korisnikView.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.showAndWait();
        popuniProizvode();
    }

    public void dodajDobavljaca(ActionEvent event) throws IOException {
        FXMLLoader korisnikView = new FXMLLoader(getClass().getResource("/gui/unosDobavljaca.fxml"));
        Parent root = (Parent) korisnikView.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.showAndWait();
        popuniDobavljace();
    }

    public void dodajTipStisak(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/unosGrupaProizvod.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.showAndWait();
        popuniProizvode();
    }

    public void comboBoxTip() {
        vboxLabel.getChildren().clear();
        vboxTextField.getChildren().clear();
        provjeraKojiPodaciSePrikazuju();
    }

    private void provjeraKojiPodaciSePrikazuju() {
        if (!("".equals(tipProizvodaComboBox.getSelectionModel().getSelectedItem()))) {
            DAOGrupaProizvod daoGrupa = new DAOGrupaProizvod();
            DTOProizvodGrupa dtoProizvodGrupa = null;
            dtoProizvodGrupa = daoGrupa.getNazivProizvoda(tipProizvodaComboBox.getSelectionModel().getSelectedItem());
            idGrupe = dtoProizvodGrupa.getIdGrupe();
            if (dtoProizvodGrupa.isBoja()) {
                bojaLabel.setFont(new Font(18));
                bojaComboBox.getItems().addAll("Bijela", "Crna", "Plava", "Zelena", "Smeđa", "Žuta", "Narandžasta", "Crvena", "Roza", "Ljubičasta");
                bojaComboBox.getSelectionModel().select(proizvodZaIzmjenu.getBoja());
                vboxLabel.getChildren().add(bojaLabel);
                vboxTextField.getChildren().add(bojaComboBox);
            }
            if (dtoProizvodGrupa.isDuzina()) {
                duzinaLabel.setFont(new Font(18));
                duzinaTextField.setFont(new Font(18));
                vboxLabel.getChildren().add(duzinaLabel);
                vboxTextField.getChildren().add(duzinaTextField);
                duzinaTextField.setText(String.format("%.2", proizvodZaIzmjenu.getDuzina()));
            }
            if (dtoProizvodGrupa.isSirina()) {
                sirinaLabel.setFont(new Font(18));
                sirinaTextField.setFont(new Font(18));
                vboxLabel.getChildren().add(sirinaLabel);
                vboxTextField.getChildren().add(sirinaTextField);
                sirinaTextField.setText(String.format("%.2", proizvodZaIzmjenu.getSirina()));
            }
            if (dtoProizvodGrupa.isVisina()) {
                visinaLabel.setFont(new Font(18));
                visinaTextField.setFont(new Font(18));
                vboxLabel.getChildren().add(visinaLabel);
                vboxTextField.getChildren().add(visinaTextField);
                visinaTextField.setText(String.format("%.2f", proizvodZaIzmjenu.getVisina()));
            }
            if (dtoProizvodGrupa.isGodisnjeDoba()) {
                godisnjeDobaLabel.setFont(new Font(18));
                godisnjeDobaComboBox.getItems().addAll("Proljeće", "Ljeto", "Jesen", "Zima");
                vboxLabel.getChildren().add(godisnjeDobaLabel);
                vboxTextField.getChildren().add(godisnjeDobaComboBox);
                godisnjeDobaComboBox.getSelectionModel().select(proizvodZaIzmjenu.getGodisnjeDoba());
            }
            if (dtoProizvodGrupa.isPol()) {
                polLabel.setFont(new Font(18));
                polComboBox.getItems().addAll("M", "Ž", "U");
                vboxLabel.getChildren().add(polLabel);
                vboxTextField.getChildren().add(polComboBox);
                polComboBox.getSelectionModel().select(proizvodZaIzmjenu.getPol());
            }
            if (dtoProizvodGrupa.isUzrast()) {
                uzrastLabel.setFont(new Font(18));
                uzrastTextField.setFont(new Font(18));
                vboxLabel.getChildren().add(uzrastLabel);
                vboxTextField.getChildren().add(uzrastTextField);
                uzrastTextField.setText(String.format("%d", proizvodZaIzmjenu.getUzrast()));
            }
            if (dtoProizvodGrupa.isVelicina()) {
                velicinaLabel.setFont(new Font(18));
                velicinaTextField.setFont(new Font(18));
                vboxLabel.getChildren().add(velicinaLabel);
                vboxTextField.getChildren().add(velicinaTextField);
                velicinaTextField.setText(String.format("%.2f", proizvodZaIzmjenu.getVelicina()));
            }
        }
    }

    public void popuniPrikaz(DTOProizvod proizvod) {

        this.proizvodZaIzmjenu = proizvod;

        tipProizvodaComboBox.getSelectionModel().select(new DAOGrupaProizvod().getNazivGrupeOdIdGrupe(proizvodZaIzmjenu.getIdGrupe()));
        nazivProizvodaTextField.setText(proizvodZaIzmjenu.getNaziv());
        JIBProizvodjacaComboBox.getSelectionModel().select(new DAOProizvodjac().proizvodjacNaOsnovuJIB(proizvodZaIzmjenu.getJIBProizvodjaca()).getNaziv());
        dobavljacComboBox.getSelectionModel().select(new DAODobavljac().getNazivPoId(new DAODobavljac().IdDobavljacaOdIdProizvoda(proizvodZaIzmjenu.getIdProizvoda())));
        materijaliComboBox.getSelectionModel().select(new DAOMaterijal().nazivMaterijalaOdId(new DAOMaterijal().idMaterijalaOdIdProizvoda(proizvodZaIzmjenu.getIdProizvoda())));
        provjeraKojiPodaciSePrikazuju();

    }

    public void sacuvajStisak(ActionEvent event) throws IOException {
        if (tipProizvodaComboBox.getSelectionModel().isEmpty() || "".equals(nazivProizvodaTextField.getText())
                || JIBProizvodjacaComboBox.getSelectionModel().getSelectedIndex() < 0
                || materijaliComboBox.getSelectionModel().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli osnovne podatke.");
        } else {
            DAOGrupaProizvod daoGrupa = new DAOGrupaProizvod();
            DTOProizvodGrupa dtoProizvodGrupa = daoGrupa.getNazivProizvoda(tipProizvodaComboBox.getSelectionModel().getSelectedItem());

            if (dtoProizvodGrupa.isBoja() && bojaComboBox.getSelectionModel().getSelectedIndex() < 0) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali boju.");
            } else if (dtoProizvodGrupa.isDuzina() && "".equals(duzinaTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli dužinu.");
            } else if (dtoProizvodGrupa.isSirina() && "".equals(sirinaTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli širinu.");
            } else if (dtoProizvodGrupa.isVisina() && "".equals(visinaTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli visinu.");
            } else if (dtoProizvodGrupa.isGodisnjeDoba() && godisnjeDobaComboBox.getSelectionModel().getSelectedIndex() < 0) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste iabrali godišnje doba.");
            } else if (dtoProizvodGrupa.isPol() && polComboBox.getSelectionModel().getSelectedIndex() < 0) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali pol.");
            } else if (dtoProizvodGrupa.isUzrast() && "".equals(uzrastTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli uzrast.");
            } else if (dtoProizvodGrupa.isVelicina() && "".equals(velicinaTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli veličinu.");
            } else {
                if (dtoProizvodGrupa.isBoja()) {
                    boja = bojaComboBox.getSelectionModel().getSelectedItem();
                }
                if (dtoProizvodGrupa.isDuzina()) {
                    try {
                        duzina = Double.parseDouble(duzinaTextField.getText());
                    } catch (NumberFormatException e) {
                        duzinaTextField.requestFocus();
                        AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Duzina mora biti broj !");
                    }
                }
                if (dtoProizvodGrupa.isSirina()) {
                    try {
                        sirina = Double.parseDouble(sirinaTextField.getText());
                    } catch (NumberFormatException e) {
                        sirinaTextField.requestFocus();
                        AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Širina mora biti broj !");
                    }
                }
                if (dtoProizvodGrupa.isVisina()) {
                    try {
                        visina = Double.parseDouble(visinaTextField.getText());
                    } catch (NumberFormatException e) {
                        visinaTextField.requestFocus();
                        AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Visina mora biti broj !");
                    }
                }
                if (dtoProizvodGrupa.isGodisnjeDoba()) {
                    godisnjeDoba = godisnjeDobaComboBox.getSelectionModel().getSelectedItem();
                }
                if (dtoProizvodGrupa.isPol()) {
                    pol = polComboBox.getSelectionModel().getSelectedItem();
                }
                if (dtoProizvodGrupa.isUzrast()) {
                    try {
                        uzrast = Integer.parseInt(uzrastTextField.getText());
                    } catch (NumberFormatException e) {
                        uzrastTextField.requestFocus();
                        AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Uzrast mora biti broj !");
                    }
                }
                if (dtoProizvodGrupa.isVelicina()) {
                    try {
                        velicina = Integer.parseInt(velicinaTextField.getText());
                    } catch (NumberFormatException e) {
                        velicinaTextField.requestFocus();
                        AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Velicina mora biti broj !");
                    }
                }
                DAOProizvod daoProizvod = new DAOProizvod();
                DAOSkladisteProizvod daoUSkladiste = new DAOSkladisteProizvod();
                DAODobavljac dobavljac = new DAODobavljac();

                DAODobavljacProizvod daod = new DAODobavljacProizvod();
                String jib = JIBProizvodjacaComboBox.getSelectionModel().getSelectedItem().split(", ")[1];
                String jibDobavljava = dobavljacComboBox.getSelectionModel().getSelectedItem().split(", ")[1];
                int idDobavljaca = dobavljac.getIdPoJibu(jibDobavljava);
                if (!daoUSkladiste.dodajProizvodUSkladiste(1, daoProizvod.idProizvoda(), kolicina)) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom dodavanja proizvoda u skladiste.");
                }
                if (!daod.dodajProizvodUSkladiste(idDobavljaca, daoProizvod.idProizvoda())) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom povezivanja proizvoda i dobavljaca.");
                }
            }

        }
    }

    public void otkaziStisak() throws IOException {
        System.exit(0);
    }

}
