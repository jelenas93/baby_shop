package dao;

import dto.DTOTipProizvoda;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public class DAOGrupaProizvod {

    private void provjeraTipaDaLiPostoji() {

        DAOTipProizvoda daoTip = new DAOTipProizvoda();
        ObservableList<DTOTipProizvoda> listaProizvoda;
        listaProizvoda = daoTip.getTipoveProizvoda();
        List<String> tipovi = new ArrayList<>();
        for (int i = 0; i < listaProizvoda.size(); i++) {
            tipovi.add(listaProizvoda.get(i).getNazivTipaProizvoda());
        }
        System.out.println(tipovi);
    }
}
