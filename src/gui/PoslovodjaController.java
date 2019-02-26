package gui;

import com.jfoenix.controls.JFXDatePicker;
import dao.DAOIzvjestaj;
import dto.DTOPazarIzvjestaj;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PoslovodjaController implements Initializable {

    public static int idZaposlenog;
    @FXML
    public LineChart<Integer, Double> graf;
    @FXML
    public ComboBox<String> mjeseci;
    @FXML
    public JFXDatePicker datumOd;
    @FXML
    public JFXDatePicker datumDo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  puniMjesece();
    }

 /*   private void puniMjesece() {
        mjeseci.getItems().add("Januar");
        mjeseci.getItems().add("Februar");
        mjeseci.getItems().add("Mart");
        mjeseci.getItems().add("April");
        mjeseci.getItems().add("Maj");
        mjeseci.getItems().add("Jun");
        mjeseci.getItems().add("Jul");
        mjeseci.getItems().add("Avgust");
        mjeseci.getItems().add("Septembar");
        mjeseci.getItems().add("Oktobar");
        mjeseci.getItems().add("Novembar");
        mjeseci.getItems().add("Decembar");

    }*/

    @FXML
    private void prviGraf() {
        LocalDate lokalOd = datumOd.getValue();
        Date dateOd = Date.from(lokalOd.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate lokalDo = datumDo.getValue();
        Date dateDo = Date.from(lokalDo.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        DAOIzvjestaj izvjestaj = new DAOIzvjestaj();
        
        graf.getXAxis().setAutoRanging(true);
        graf.getYAxis().setAutoRanging(true);
       
        XYChart.Series series = new XYChart.Series<>();
        ArrayList<DTOPazarIzvjestaj> dtoPazari = new DAOIzvjestaj().pazariZaMjesec(dateOd, dateDo);
         for(int i=0;i<dtoPazari.size();i++){
            System.out.println(dtoPazari.get(i).getMjesec() + "  " + dtoPazari.get(i).getIznosZaMjesec());
            series.getData().add(new XYChart.Data(""+dtoPazari.get(i).getMjesec(),dtoPazari.get(i).getIznosZaMjesec()));
        }
       
        graf.getData().add(series);

    }

    @FXML
    private void odjava(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/prijavaNaSistem.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

}
