package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOMaterijal;
import dao.DAOGrupaProizvod;
import dao.DAOProizvod;
import dao.DAOProizvodjac;
import dto.DTOMaterijal;
import dto.DTOProizvodGrupa;
import dto.DTOProizvodjac;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

public class UnosProizvodaController implements Initializable {

    @FXML
    private JFXComboBox<String> materijaliComboBox;
    @FXML
    private JFXComboBox<String> tipProizvodaComboBox;
    @FXML
    private JFXComboBox<String> JIBProizvodjacaComboBox;
    @FXML
    private JFXTextField nazivProizvodaTextField;
    @FXML
    private JFXTextField barkodTextField;
    @FXML
    private JFXTextField sifraTextField;
    @FXML
    private JFXTextField kolicinaTextField;
    @FXML
    private JFXTextField cijenaTextField;
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

    private void popuniMaterijale() {
        DAOMaterijal daoMaterijal = new DAOMaterijal();
        ObservableList<DTOMaterijal> listaMaterijala;
        listaMaterijala = daoMaterijal.getMaterijal();
        for (int i = 0; i < listaMaterijala.size(); i++) {
            materijaliComboBox.getItems().add(listaMaterijala.get(i).getNaziv());
        }
    }

    private void popuniProizvodjace() {
        DAOProizvodjac dAOProizvodjac = new DAOProizvodjac();
        ObservableList<DTOProizvodjac> listaProizvodjaca;
        listaProizvodjaca = dAOProizvodjac.getProizvodjace();
        for (int i = 0; i < listaProizvodjaca.size(); i++) {
            JIBProizvodjacaComboBox.getItems().add(listaProizvodjaca.get(i).getNaziv() + ", " + listaProizvodjaca.get(i).getJIBProizvodjaca());
        }
    }

    private void popuniProizvode() {
        DAOGrupaProizvod daoTip = new DAOGrupaProizvod();
        ObservableList<DTOProizvodGrupa> listaProizvoda;
        listaProizvoda = daoTip.getGrupeProizvoda();
        for (int i = 0; i < listaProizvoda.size(); i++) {
            tipProizvodaComboBox.getItems().add(listaProizvoda.get(i).getNazivTipaProizvoda());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popuniMaterijale();
        popuniProizvode();
        popuniProizvodjace();

    }

    public void dodajProizvodjaca(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosProizvodjaca.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    public void dodajTipStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosGrupaProizvod.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene korisnikScena = new Scene(korisnikView);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
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
                //    bojaComboBox.set
                //bojaTextField.setFont(new Font(18));
                bojaComboBox.getItems().addAll("Bijela", "Crna", "Plava", "Zelena", "Smeđa", "Žuta", "Narandžasta", "Crvena", "Roza", "Ljubičasta");
                vboxLabel.getChildren().add(bojaLabel);
                vboxTextField.getChildren().add(bojaComboBox);
            }
            if (dtoProizvodGrupa.isDuzina()) {
                duzinaLabel.setFont(new Font(18));
                duzinaTextField.setFont(new Font(18));
                vboxLabel.getChildren().add(duzinaLabel);
                vboxTextField.getChildren().add(duzinaTextField);

            }
            if (dtoProizvodGrupa.isSirina()) {
                sirinaLabel.setFont(new Font(18));
                sirinaTextField.setFont(new Font(18));
                vboxLabel.getChildren().add(sirinaLabel);
                vboxTextField.getChildren().add(sirinaTextField);
            }
            if (dtoProizvodGrupa.isVisina()) {
                visinaLabel.setFont(new Font(18));
                visinaTextField.setFont(new Font(18));
                vboxLabel.getChildren().add(visinaLabel);
                vboxTextField.getChildren().add(visinaTextField);
            }
            if (dtoProizvodGrupa.isGodisnjeDoba()) {
                godisnjeDobaLabel.setFont(new Font(18));
                //  godisnjeDobaTextField.setFont(new Font(18));
                godisnjeDobaComboBox.getItems().addAll("Proljeće", "Ljeto", "Jesen", "Zima");
                vboxLabel.getChildren().add(godisnjeDobaLabel);
                vboxTextField.getChildren().add(godisnjeDobaComboBox);
            }
            if (dtoProizvodGrupa.isPol()) {
                polLabel.setFont(new Font(18));
                //  polTextField.setFont(new Font(18));
                polComboBox.getItems().addAll("M", "Ž");
                vboxLabel.getChildren().add(polLabel);
                vboxTextField.getChildren().add(polComboBox);
            }
            if (dtoProizvodGrupa.isUzrast()) {
                uzrastLabel.setFont(new Font(18));
                uzrastTextField.setFont(new Font(18));
                vboxLabel.getChildren().add(uzrastLabel);
                vboxTextField.getChildren().add(uzrastTextField);
            }
            if (dtoProizvodGrupa.isVelicina()) {
                velicinaLabel.setFont(new Font(18));
                velicinaTextField.setFont(new Font(18));
                vboxLabel.getChildren().add(velicinaLabel);
                vboxTextField.getChildren().add(velicinaTextField);
            }
        }
    }

    public void sacuvajStisak(ActionEvent event) throws IOException {
        if ("".equals(tipProizvodaComboBox.getSelectionModel().getSelectedItem())
                || "".equals(nazivProizvodaTextField.getText())
                || "".equals(barkodTextField.getText()) || "".equals(sifraTextField.getText())
                || "".equals(kolicinaTextField.getText()) || "".equals(cijenaTextField.getText())
                || "".equals(JIBProizvodjacaComboBox.getSelectionModel().getSelectedItem())
                || "".equals(materijaliComboBox.getSelectionModel().getSelectedItem())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli osnovne podatke.");
        } else {
            DAOGrupaProizvod daoGrupa = new DAOGrupaProizvod();
            DTOProizvodGrupa dtoProizvodGrupa = null;
            dtoProizvodGrupa = daoGrupa.getNazivProizvoda(tipProizvodaComboBox.getSelectionModel().getSelectedItem());
            if (dtoProizvodGrupa.isBoja() && "".equals(bojaComboBox.getSelectionModel().getSelectedItem())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali boju.");
            } else if (dtoProizvodGrupa.isDuzina() && "".equals(duzinaTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli dužinu.");
            } else if (dtoProizvodGrupa.isSirina() && "".equals(sirinaTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli širinu.");
            } else if (dtoProizvodGrupa.isVisina() && "".equals(visinaTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli visinu.");
            } else if (dtoProizvodGrupa.isGodisnjeDoba() && "".equals(godisnjeDobaComboBox.getSelectionModel().getSelectedItem())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste iabrali godišnje doba.");
            } else if (dtoProizvodGrupa.isPol() && "".equals(polComboBox.getSelectionModel().getSelectedItem())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali pol.");
            } else if (dtoProizvodGrupa.isUzrast() && "".equals(uzrastTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli uzrast.");
            } else if (dtoProizvodGrupa.isVelicina() && "".equals(velicinaTextField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli veličinu.");
            } else {
                DAOProizvod daoProizvod = new DAOProizvod();
             //   try {
                    String jib = JIBProizvodjacaComboBox.getSelectionModel().getSelectedItem().split(", ")[0];
                   /* if (!daoProizvod.upisUBazuProizvod(barkodTextField.getText(), sifraTextField.getText(),
                            nazivProizvodaTextField.getText().toUpperCase(),
                            Integer.parseInt(kolicinaTextField.getText()),
                            Double.parseDouble(cijenaTextField.getText()),
                            jib, idGrupe, Double.parseDouble(duzinaTextField.getText()),
                            Double.parseDouble(sirinaTextField.getText()),
                            Double.parseDouble(visinaTextField.getText()),
                            Integer.parseInt(velicinaTextField.getText()),
                            Integer.parseInt(uzrastTextField.getText()),
                            polComboBox.getSelectionModel().getSelectedItem(),
                            bojaComboBox.getSelectionModel().getSelectedItem(),
                            godisnjeDobaComboBox.getSelectionModel().getSelectedItem())) {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa proizvoda u bazu.");
                    } else {
                        System.exit(0);
                    }*/
                     if (!daoProizvod.upisUBazuProizvod(barkodTextField.getText(), sifraTextField.getText(),
                            nazivProizvodaTextField.getText().toUpperCase(),
                            Integer.parseInt(kolicinaTextField.getText()),
                            Double.parseDouble(cijenaTextField.getText()),
                            jib, idGrupe,null,
                            Double.parseDouble(sirinaTextField.getText()),
                            Double.parseDouble(visinaTextField.getText()),
                            Integer.parseInt(velicinaTextField.getText()),
                            Integer.parseInt(uzrastTextField.getText()),
                            polComboBox.getSelectionModel().getSelectedItem(),
                            bojaComboBox.getSelectionModel().getSelectedItem(),
                            godisnjeDobaComboBox.getSelectionModel().getSelectedItem())) {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greška prilikom upisa proizvoda u bazu.");
                    } else {
                        System.exit(0);
                    }
           /*     } catch (NumberFormatException e) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Unesite brojeve !");
                }
*/
            }
        }
    }

    public void otkaziStisak() throws IOException {
        System.exit(0);
    }

}
