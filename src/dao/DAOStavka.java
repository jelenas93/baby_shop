package dao;

import connectionpool.ConnectionPool;
import dto.DTOStavka;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOStavka {
     
    public static String SQL_GET_STAVKA="select * from baby_shop.stavka where idRacuna=0";
   
    public ObservableList<DTOStavka> getStavke() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DTOStavka> stavke = new ArrayList<>();
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_STAVKA);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idStavke=rs.getInt(1);
                int idRacuna=rs.getInt(2);
                int kolicina=rs.getInt(3);
                double cijena=rs.getDouble(4);
                int idProizvoda=rs.getInt(5);
                stavke.add(new DTOStavka(idStavke, idRacuna, kolicina, cijena, idProizvoda));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStavka.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStavka.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStavka.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(stavke);
    }
    
    public boolean upisUBazuStavku(int idRacuna, int kolicina, double cijena,int idProizvoda){
        
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`stavka` "
                    + "(`IdStavke`,`IdRacuna`, `Kolicina`, `Cijena`,`IdProizvoda`)"
                    + " VALUES (default, ?, ?, ?, ?)");
            myStatement.setInt(1, idRacuna);
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
                    Logger.getLogger(DAOProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
}
