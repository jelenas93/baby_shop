package gui;

import com.jfoenix.controls.JFXDatePicker;
import com.sun.javafx.charts.Legend;
import com.sun.prism.paint.Gradient;
import dao.DAOIzvjestaj;
import dto.DTOPazarIzvjestaj;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PoslovodjaController implements Initializable {

    public static int idZaposlenog;
   
    @FXML
    private BarChart<Integer, Double> bar;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private ComboBox<String> mjeseci;
    @FXML
    private JFXDatePicker datumOd;
    @FXML
    private JFXDatePicker datumDo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void prviGraf() {
        LocalDate lokalOd = datumOd.getValue();
        Date dateOd = Date.from(lokalOd.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate lokalDo = datumDo.getValue();
        Date dateDo = Date.from(lokalDo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        DAOIzvjestaj izvjestaj = new DAOIzvjestaj();
        bar.setTitle("Promet pazara");

        bar.getXAxis().setAutoRanging(true);
        bar.getYAxis().setAutoRanging(true);
        bar.setAnimated(false);
        y.setLabel("KM");
        x.setLabel("Dani");

        XYChart.Series series = new XYChart.Series<>();
        ArrayList<DTOPazarIzvjestaj> dtoPazari = new DAOIzvjestaj().pazariZaMjesec(dateOd, dateDo);
        for (int i = 0; i < dtoPazari.size(); i++) {
            System.out.println(dtoPazari.get(i).getMjesec() + "  " + dtoPazari.get(i).getIznosZaMjesec());
            series.getData().add(new XYChart.Data("" + dtoPazari.get(i).getMjesec(), dtoPazari.get(i).getIznosZaMjesec()));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        series.setName("Pazar od " + sdf.format(dateOd) + " do " + sdf.format(dateDo));
        bar.setLegendSide(Side.RIGHT);
        bar.setLegendVisible(true);
        bar.getData().add(series);

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
