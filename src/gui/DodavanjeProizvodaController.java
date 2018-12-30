package gui;

import com.jfoenix.controls.JFXComboBox;
import dao.DAOMaterijal;
import dao.DAOTipProizvoda;
import dto.DTOMaterijal;
import dto.DTOTipProizvoda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class DodavanjeProizvodaController implements Initializable {

    @FXML
    private JFXComboBox<String> materijaliComboBox;
    
    @FXML
    private JFXComboBox<String> tipProizvodaComboBox;

    private void popuniMaterijale() {
        DAOMaterijal daoMaterijal = new DAOMaterijal();
        ObservableList<DTOMaterijal> listaMaterijala;
        listaMaterijala = daoMaterijal.getMaterijal();
        for (int i = 0; i < listaMaterijala.size(); i++) {
            materijaliComboBox.getItems().add(listaMaterijala.get(i).getNaziv());
        }
    }
    
    private void popuniProizvode() {
        DAOTipProizvoda daoTip=new DAOTipProizvoda();
        ObservableList<DTOTipProizvoda> listaProizvoda;
        listaProizvoda = daoTip.getTipoveProizvoda();  
        System.out.println(listaProizvoda.size());
        for (int i = 0; i < listaProizvoda.size(); i++) {
            tipProizvodaComboBox.getItems().add(listaProizvoda.get(i).getNazivTipaProizvoda());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popuniMaterijale();
        popuniProizvode();

    }

}
