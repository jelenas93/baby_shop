package gui;

import com.jfoenix.controls.JFXComboBox;
import dao.DAOMaterijal;
import dto.DTOMaterijal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class DodavanjeProizvodaController implements Initializable {

    @FXML
    private JFXComboBox<String> materijaliComboBox;

    private void popuniMaterijale() {
        DAOMaterijal daoMaterijal = new DAOMaterijal();
        ObservableList<DTOMaterijal> listaMaterijala;
        listaMaterijala = new DAOMaterijal().getMaterijal();
        for (int i = 0; i < listaMaterijala.size(); i++) {
            materijaliComboBox.getItems().add(listaMaterijala.get(i).getNaziv());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popuniMaterijale();

    }

}
