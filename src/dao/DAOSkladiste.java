package dao;

import connectionpool.ConnectionPool;
import dto.DTODobavljac;
import dto.DTOSkladiste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOSkladiste {
    public ObservableList<DTOSkladiste> skladista() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOSkladiste> skladiste = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from skladiste");
            rs = ps.executeQuery();

            while (rs.next()) {
                int id=rs.getInt("IdSkladista");
                int postanskiBroj = rs.getInt("PostanskiBroj");
                String adresa=rs.getString("Adresa");
                skladiste.add(new DTOSkladiste(id, postanskiBroj, adresa));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProizvodjac.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvodjac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvodjac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(skladiste);
    }
    
    public boolean dodajSkladiste(String adresa,int postanskiBroj){
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("insert into skladiste values(default,?,?)");
            myStatement.setString(1,adresa);
            myStatement.setInt(2, postanskiBroj);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProizvodjac.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvodjac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvodjac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
}
