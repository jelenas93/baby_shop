package gui;

import babyshop.AlertHelper;
import com.jfoenix.controls.JFXDatePicker;
import dao.DAODobavljac;
import dao.DAOIzvjestaj;
import dto.DTODobavljac;
import dto.DTOPazarIzvjestaj;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pdf.PDF;

public class PoslovodjaController implements Initializable {

    public static int idZaposlenog;

    @FXML
    private BarChart<Integer, Double> bar, bar1;
    @FXML
    private NumberAxis y, y1;
    @FXML
    private CategoryAxis x, x1;
    @FXML
    private ComboBox<String> dobavljac;
    @FXML
    private JFXDatePicker datumOd;
    @FXML
    private JFXDatePicker datumDo;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    XYChart.Series series = new XYChart.Series<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popuniComboBox();
    }

    private void popuniComboBox() {
        dobavljac.getItems().clear();
        DAODobavljac daod = new DAODobavljac();
        ObservableList<DTODobavljac> listaDobavljaca;
        listaDobavljaca = daod.getDobavljace();
        for (int i = 0; i < listaDobavljaca.size(); i++) {
            dobavljac.getItems().add(listaDobavljaca.get(i).getNaziv());
        }
    }

    private void cistiPolja() {
        datumOd.getEditor().clear();
        datumOd.setValue(null);
        datumDo.getEditor().clear();
        datumDo.setValue(null);
    }

    private void cistiGraf(){
         bar.getData().clear();
         bar.layout();
         bar.setTitle("");
         bar.setLegendVisible(false);
         x.setLabel("");
         y.setLabel("");
    }
    
    private void cistiGraf1(){
         bar1.getData().clear();
         bar1.layout();
         bar1.setTitle("");
         bar1.setLegendVisible(false);
         x1.setLabel("");
         y1.setLabel("");
    }
    
    private String izBrojaUMjesec(int i){
        switch(i){
            case 1: return "Januar";
             case 2: return "Februar";
             case 3: return "Mart"; 
             case 4: return "April";
             case 5: return "Maj";
             case 6: return "Jun";
             case 7: return "Jul";
             case 8: return "Avgust";
             case 9: return "Septembar";
             case 10: return "Oktobar"; 
             case 11: return "Novembar"; 
             default : return "Decembar";
        }
    }
    
    @FXML
    private void prviGraf() {
        bar1.setVisible(false);
        bar.setVisible(true);
        cistiGraf1();
        if (datumOd.getValue() == null || datumDo.getValue() == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali vrijeme za koje tražite izvještaj.");
        } else {
            LocalDate lokalOd = datumOd.getValue();
            Date dateOd = Date.from(lokalOd.atStartOfDay(ZoneId.systemDefault()).toInstant());
            LocalDate lokalDo = datumDo.getValue();
            Date dateDo = Date.from(lokalDo.atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (dateOd.after(dateDo)) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Ne možete generisati izvještaj od " + sdf.format(dateOd) + " do " + sdf.format(dateDo));
            } else {
                DAOIzvjestaj izvjestaj = new DAOIzvjestaj();
                bar.setTitle("Promet pazara");
                bar.getXAxis().setAutoRanging(true);
                bar.getYAxis().setAutoRanging(true);
                bar.setAnimated(false);
                y.setLabel("KM");
                x.setLabel("Dani");

                series = new XYChart.Series<>();
                ArrayList<DTOPazarIzvjestaj> dtoPazari = new DAOIzvjestaj().pazariZaMjesec(dateOd, dateDo);
                for (int i = 0; i < dtoPazari.size(); i++) {
                    System.out.println(dtoPazari.get(i).getMjesec() + "  " + dtoPazari.get(i).getIznosZaMjesec());
                    series.getData().add(new XYChart.Data("" + dtoPazari.get(i).getMjesec(), dtoPazari.get(i).getIznosZaMjesec()));
                }
                series.setName("Pazar od " + sdf.format(dateOd) + " do " + sdf.format(dateDo));
                bar.setLegendSide(Side.RIGHT);
                bar.setLegendVisible(true);
                bar.getData().add(series);
                cistiPolja();
            }
        }
    }

    @FXML
    private void prviGrafMjesec() {
        bar.setVisible(false);
        bar1.setVisible(true);
        cistiGraf();
        if (datumOd.getValue() == null || datumDo.getValue() == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali vrijeme za koje tražite izvještaj.");
        } else {
            LocalDate lokalOd = datumOd.getValue();
            Date dateOd = Date.from(lokalOd.atStartOfDay(ZoneId.systemDefault()).toInstant());
            LocalDate lokalDo = datumDo.getValue();
            Date dateDo = Date.from(lokalDo.atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (dateOd.after(dateDo)) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Ne možete generisati izvještaj od " + sdf.format(dateOd) + " do " + sdf.format(dateDo));
            } else {
                DAOIzvjestaj izvjestaj = new DAOIzvjestaj();
                bar1.setTitle("Promet pazara po mjesecima");
                bar1.getXAxis().setAutoRanging(true);
                bar1.getYAxis().setAutoRanging(true);
                bar1.setAnimated(false);
                y1.setLabel("KM");
                x1.setLabel("Mjeseci");

                series = new XYChart.Series<>();
                ArrayList<DTOPazarIzvjestaj> dtoPazari = new DAOIzvjestaj().pazariZaGodinu(dateOd, dateDo);
                for (int i = 0; i < dtoPazari.size(); i++) {
                    System.out.println(dtoPazari.get(i).getMjesec() + "  " + dtoPazari.get(i).getIznosZaMjesec());
                    series.getData().add(new XYChart.Data(izBrojaUMjesec(dtoPazari.get(i).getMjesec()), dtoPazari.get(i).getIznosZaMjesec()));
                }
                series.setName("Pazar od " + sdf.format(dateOd) + " do " + sdf.format(dateDo));
                bar1.setLegendSide(Side.RIGHT);
                bar1.setLegendVisible(true);
                bar1.getData().add(series);
                cistiPolja();
            }
        }
    }

    @FXML
    private void ukupnoProdano(ActionEvent event) {
        cistiGraf();
        cistiGraf1();
        if (datumOd.getValue() == null || datumDo.getValue() == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali vrijeme za koje tražite izvještaj.");
        } else {
            LocalDate lokalOd = datumOd.getValue();
            Date dateOd = Date.from(lokalOd.atStartOfDay(ZoneId.systemDefault()).toInstant());
            LocalDate lokalDo = datumDo.getValue();
            Date dateDo = Date.from(lokalDo.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (dateOd.after(dateDo)) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Ne možete generisati izvještaj od " + sdf.format(dateOd) + " do " + sdf.format(dateDo));
            } else {
                PDF.kreirajIzvjestajSvihProdanihProizvoda(dateOd, dateDo);
                popuniComboBox();
                cistiPolja();
            }
        }
    }

    @FXML
    private void narucenoOdDobavljaca(ActionEvent event) {
        cistiGraf();
        cistiGraf1();
        if (datumOd.getValue() == null || datumDo.getValue() == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali vrijeme za koje tražite izvještaj.");
        } else {
            LocalDate lokalOd = datumOd.getValue();
            Date dateOd = Date.from(lokalOd.atStartOfDay(ZoneId.systemDefault()).toInstant());
            LocalDate lokalDo = datumDo.getValue();
            Date dateDo = Date.from(lokalDo.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (dateOd.after(dateDo)) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Ne možete generisati izvještaj od " + sdf.format(dateOd) + " do " + sdf.format(dateDo));
            } else if (dobavljac.getSelectionModel().getSelectedIndex() == -1) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali dobavljača.");
            } else {
                DTODobavljac dtoDobavljac = new DAODobavljac().getDobavljacPoNazivu(dobavljac.getSelectionModel().getSelectedItem());
                int id = dtoDobavljac.getIdDobavljaca();
                PDF.kreirajIzvjestajPoDobavljacuZaMjesec(dateOd, dateDo, id);
                popuniComboBox();
                cistiPolja();
            }
        }
    }

    @FXML
    private void prodanoOdDobavljaca(ActionEvent event) {
        cistiGraf();
        cistiGraf1();
        if (datumOd.getValue() == null || datumDo.getValue() == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali vrijeme za koje tražite izvještaj.");
        } else {
            LocalDate lokalOd = datumOd.getValue();
            Date dateOd = Date.from(lokalOd.atStartOfDay(ZoneId.systemDefault()).toInstant());
            LocalDate lokalDo = datumDo.getValue();
            Date dateDo = Date.from(lokalDo.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (dateOd.after(dateDo)) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Ne možete generisati izvještaj od " + sdf.format(dateOd) + " do " + sdf.format(dateDo));
            } else if (dobavljac.getSelectionModel().getSelectedIndex() == -1) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali dobavljača.");
            } else {
                DTODobavljac dtoDobavljac = new DAODobavljac().getDobavljacPoNazivu(dobavljac.getSelectionModel().getSelectedItem());
                int id = dtoDobavljac.getIdDobavljaca();
                PDF.kreirajIzvjestajPoDobavljacuZaProdaneProizvode(dateOd, dateDo, id);
                popuniComboBox();
                cistiPolja();
            }
        }
    }

    @FXML
    private void zaliheSkladista(ActionEvent event) {
        cistiGraf();
        cistiGraf1();
        PDF.kreirajIzvjestajZaSkladiste();
    }

    @FXML
    private void odjava(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/prijavaNaSistem.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Prijava na sistem");
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

}
