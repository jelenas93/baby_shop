package dao;

import connectionpool.ConnectionPool;
import dto.DTORacun;
import java.io.IOException;
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

public class DAORacun {

    public ObservableList<DTORacun> racuni() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTORacun> skladiste = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from racun");
            rs = ps.executeQuery();

            while (rs.next()) {
                int idRacuna = rs.getInt("IdRacuna");
                Date datumRacuna = rs.getDate("DatumRacuna");
                double ukupnaCijena = rs.getDouble("UkupnaCijena");
                int idZaposlenog = rs.getInt("IdZaposlenog");
                skladiste.add(new DTORacun(idRacuna, idZaposlenog, datumRacuna, ukupnaCijena));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(skladiste);
    }
    
    public boolean dodajRacun(int idZaposlenog, Date datumRacuna, double ukupnaCijena){
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`racun` "
                    + " VALUES (default, ?, ?, ?)");
            
            myStatement.setDate(1, (java.sql.Date) datumRacuna);
            myStatement.setDouble(2, ukupnaCijena);
            myStatement.setInt(3, idZaposlenog);
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
    
    public DTORacun vratiRacunPoId(int idRacuna){
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        DTORacun retValue=null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from racun where IdRacuna="+idRacuna);
            rs = ps.executeQuery();

            while (rs.next()) {
                int IdRacuna = rs.getInt("IdRacuna");
                Date datumRacuna=rs.getDate("DatumRacuna");
                double UkupnaCijena=rs.getDouble("Ukupna_cijena");
                int IdZaposlenog=rs.getInt("IdZaposlenog");
                retValue=new DTORacun(IdRacuna, IdZaposlenog, datumRacuna, UkupnaCijena);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue;
    }
    
     public int idZadnjegRacuna() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        int retValue = 0;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select max(IdRacuna) as max_id_racuna from racun");
            rs = ps.executeQuery();

            while (rs.next()) {
                retValue = rs.getInt("max_id_racuna");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue;
    }
}
