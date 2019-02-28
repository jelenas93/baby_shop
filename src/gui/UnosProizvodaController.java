package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAODobavljac;
import dao.DAODobavljacProizvod;
import dao.DAOMaterijal;
import dao.DAOGrupaProizvod;
import dao.DAOProizvod;
import dao.DAOProizvodjac;
import dto.DTOMaterijal;
import dto.DTOProizvodGrupa;
import dto.DTOProizvodjac;
import dao.DAOSkladisteProizvod;
import dto.DTODobavljac;
import dto.DTOProizvod;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UnosProizvodaController implements Initializable {

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
    private JFXTextField barkodTextField;
    @FXML
    private JFXTextField sifraTextField;
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
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosProizvodjaca.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.setTitle("Unos proizvođača");
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
        popuniProizvodjace();
    }

    public void dodajDobavljaca(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosDobavljaca.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.setTitle("Unos dobavljača");
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
        popuniDobavljace();
    }

    public void dodajTipStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosGrupaProizvod.fxml"));
        Stage window = new Stage();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.setTitle("Unos tip proizvoda");
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
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
                bojaComboBox.setPrefSize(300, 40);
                vboxLabel.getChildren().add(bojaLabel);
                vboxTextField.getChildren().add(bojaComboBox);
            }
            if (dtoProizvodGrupa.isDuzina()) {
                duzinaLabel.setFont(new Font(18));
                duzinaTextField.setFont(new Font(18));
                duzinaTextField.setPrefSize(300, 40);
                vboxLabel.getChildren().add(duzinaLabel);
                vboxTextField.getChildren().add(duzinaTextField);
            }
            if (dtoProizvodGrupa.isSirina()) {
                sirinaLabel.setFont(new Font(18));
                sirinaTextField.setFont(new Font(18));
                sifraTextField.setPrefSize(300, 40);
                vboxLabel.getChildren().add(sirinaLabel);
                vboxTextField.getChildren().add(sirinaTextField);
            }
            if (dtoProizvodGrupa.isVisina()) {
                visinaLabel.setFont(new Font(18));
                visinaTextField.setFont(new Font(18));
                visinaTextField.setPrefSize(300, 40);
                vboxLabel.getChildren().add(visinaLabel);
                vboxTextField.getChildren().add(visinaTextField);
            }
            if (dtoProizvodGrupa.isGodisnjeDoba()) {
                godisnjeDobaLabel.setFont(new Font(18));
                godisnjeDobaComboBox.getItems().addAll("Proljeće", "Ljeto", "Jesen", "Zima");
                godisnjeDobaComboBox.setPrefSize(300, 40);
                vboxLabel.getChildren().add(godisnjeDobaLabel);
                vboxTextField.getChildren().add(godisnjeDobaComboBox);
            }
            if (dtoProizvodGrupa.isPol()) {
                polLabel.setFont(new Font(18));
                polComboBox.getItems().addAll("M", "Ž", "U");
                polComboBox.setPrefSize(300, 40);
                vboxLabel.getChildren().add(polLabel);
                vboxTextField.getChildren().add(polComboBox);
            }
            if (dtoProizvodGrupa.isUzrast()) {
                uzrastLabel.setFont(new Font(18));
                uzrastTextField.setFont(new Font(18));
                uzrastTextField.setPrefSize(300, 40);
                vboxLabel.getChildren().add(uzrastLabel);
                vboxTextField.getChildren().add(uzrastTextField);
            }
            if (dtoProizvodGrupa.isVelicina()) {
                velicinaLabel.setFont(new Font(18));
                velicinaTextField.setFont(new Font(18));
                velicinaTextField.setPrefSize(300, 40);
                vboxLabel.getChildren().add(velicinaLabel);
                vboxTextField.getChildren().add(velicinaTextField);
            }
        }
    }

    public void sacuvajStisak(ActionEvent event) throws IOException {
        if (tipProizvodaComboBox.getSelectionModel().isEmpty() || "".equals(nazivProizvodaTextField.getText())
                || "".equals(barkodTextField.getText()) || "".equals(sifraTextField.getText())
                || JIBProizvodjacaComboBox.getSelectionModel().getSelectedIndex() < 0
                || materijaliComboBox.getSelectionModel().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli osnovne podatke.");
        } else if (!Pattern.matches("[0-9]{13}", barkodTextField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Neispravan barkod.");
        } else if (!Pattern.matches("[0-9]{5}", sifraTextField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Neispravna šifra.");
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
                if (dtoProizvodGrupa.isGodisnjeDoba()) {
                    godisnjeDoba = godisnjeDobaComboBox.getSelectionModel().getSelectedItem();
                }
                if (dtoProizvodGrupa.isPol()) {
                    pol = polComboBox.getSelectionModel().getSelectedItem();
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
                DTOProizvod proizvodB = daoProizvod.getProizvodPoBarkodu(barkodTextField.getText());
                if (proizvodB != null) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Postoji proizvod sa barkodom " + barkodTextField.getText() + " .");
                } else {
                    DTOProizvod proizvodS = daoProizvod.getProizvodPoSifri(sifraTextField.getText());
                    if (proizvodS != null) {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Postoji proizvod sa šifrom " + sifraTextField.getText() + " .");
                    } else {
                        if (!daoProizvod.upisUBazuProizvod(barkodTextField.getText(), sifraTextField.getText(),
                                nazivProizvodaTextField.getText().toUpperCase(), jib, idGrupe, duzina, sirina, visina,
                                velicina, uzrast, pol, boja, godisnjeDoba)) {
                            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa proizvoda u bazu.");
                        } else {
                            if (!daoUSkladiste.dodajProizvodUSkladiste(1, daoProizvod.idProizvoda(), kolicina)) {
                                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom dodavanja proizvoda u skladiste.");
                            } else {
                                if (!daod.dodajUDobavljacProizvod(idDobavljaca, daoProizvod.idProizvoda())) {
                                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom povezivanja proizvoda i dobavljaca.");
                                } else {
                                    DAOMaterijal daoMaterijal = new DAOMaterijal();
                                    int idMaterijala = daoMaterijal.idMaterijalaOdNaziva(materijaliComboBox.getSelectionModel().getSelectedItem());
                                    if (!daoMaterijal.dodajUbazuProizvodMaterijal(idMaterijala, daoProizvod.idProizvoda())) {
                                        AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom povezivanja proizvoda i materijala.");
                                    } else {
                                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        window.close();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void otkaziStisak(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

}
