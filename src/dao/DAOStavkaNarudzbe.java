package dao;

import babyshop.AlertHelper;
import connectionpool.ConnectionPool;
import dto.DTOProizvod;
import dto.DTOStavkaNarudzbe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import tabele.TabelaNarudzbenica;

public class DAOStavkaNarudzbe {
   
    public boolean upisUBazuStavkuNarudzbe(int idNarudzbenice, int kolicina, double cijena, int idProizvoda){
        
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`stavka_narudzbe` "
                    + "(`RedniBrojStavkeNarudzbe`,`IdNarudzbenice`, `Kolicina`, `Cijena`,`IdProizvoda`)"
                    + " VALUES (default, ?, ?, ?, ?)");
            myStatement.setInt(1, idNarudzbenice);
            myStatement.setInt(2, kolicina);
            myStatement.setDouble(3, cijena);
            myStatement.setInt(4, idProizvoda);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProizvod.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStavkaNarudzbe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStavkaNarudzbe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
    public ArrayList<DTOStavkaNarudzbe> stavkeNaNaruzbenici(int idNarudzbenice) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DTOStavkaNarudzbe> stavke = new ArrayList<>();
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.stavka_narudzbe where IdNarudzbenice="+idNarudzbenice);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idStavke=rs.getInt("RedniBrojStavkeNarudzbe");
                //int idRacun=rs.getInt("IdRacuna");
                int kolicina=rs.getInt("Kolicina");
                double cijena=rs.getDouble("Cijena");
                int idProizvoda=rs.getInt("IdProizvoda");
                stavke.add(new DTOStavkaNarudzbe(idStavke, idNarudzbenice, kolicina, cijena, idProizvoda));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DTOStavkaNarudzbe.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStavkaNarudzbe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStavkaNarudzbe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return stavke;
    }
    
    
    
    //metoda za vadjene iz liste
   
    
}
