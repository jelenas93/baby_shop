package dao;

import connectionpool.ConnectionPool;
import dto.DTOStorniranRacun;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOStorniranRacun {
    public ObservableList<DTOStorniranRacun> racuni() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOStorniranRacun> storniraniRacuni = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from storniran_racun");
            rs = ps.executeQuery();

            while (rs.next()) {
                int idStorniranogRacuna = rs.getInt("IdStorniranogRacuna");
                Date datumRacuna = rs.getDate("DatumRacuna");
                double ukupnaCijena = rs.getDouble("UkupnaCijena");
                int idZaposlenog = rs.getInt("IdZaposlenog");
                int idRacuna=rs.getInt("IdRacuna");
                storniraniRacuni.add(new DTOStorniranRacun(idStorniranogRacuna, datumRacuna, ukupnaCijena, idZaposlenog, idRacuna));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStorniranRacun.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStorniranRacun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStorniranRacun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(storniraniRacuni);
    }
    
    public boolean dodajStorniraniRacun(Date datumRacuna, double ukupnaCijena, int idZaposlenog, int idRacuna){
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`storniran_racun` "
                    + " VALUES (default, ?, ?, ?, ?)");
            
            myStatement.setDate(1, (java.sql.Date) datumRacuna);
            myStatement.setDouble(2, ukupnaCijena);
            myStatement.setInt(3, idZaposlenog);
            myStatement.setInt(4, idRacuna);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
}
