package dao;

import connectionpool.ConnectionPool;
import dto.DTOMjesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOMjesto {
    
    private final String SQL_GET_MJESTO="select * from baby)shop.mjesto";
    
    public ObservableList<DTOMjesto> getMjesto() {
        Connection con = null;
         PreparedStatement ps = null;

         ResultSet rs = null;
         ArrayList<DTOMjesto> mjesta = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_MJESTO);
            rs = ps.executeQuery();

            while (rs.next()) {
                int postanskiBroj=rs.getInt(1);
                String naziv=rs.getString(2);
                String opstina=rs.getString(3);
                String drzava=rs.getString(4);
               
                mjesta.add(new DTOMjesto(postanskiBroj, naziv, opstina, drzava));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMjesto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMjesto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMjesto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return FXCollections.observableArrayList(mjesta);
    }
     
    public boolean upisUBazuMjesta(int postanskiBroj, String naziv, String opstina, String drzava){
       
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`mjesto`"
                    + "  (`PostanskiBroj`, `Naziv`, `Opstina`, `Drzava`)"
                    + " VALUES (?, ?, ?, ?)");
            myStatement.setInt(1, postanskiBroj);
            myStatement.setString(2, naziv);
            myStatement.setString(3, opstina);
            myStatement.setString(4, drzava);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMjesto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMjesto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMjesto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
}
