package dao;

import connectionpool.ConnectionPool;
import dto.DTOKalkulacija;
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

public class DAOKalkulacija {
    
     public ObservableList<DTOKalkulacija> getKalkulacije() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOKalkulacija> kalkulacije = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from kalkulacija");
            rs = ps.executeQuery();

            while (rs.next()) {
                int idNarudzbenice = rs.getInt("IdKalkulacije");
                Date datum = rs.getDate("DatumKalkulacije");
                int idDobavljaca=rs.getInt("IdDobavljaca");
                int idZaposlenog = rs.getInt("IdZaposlenog");
                double ukupnaCijena = rs.getDouble("UkupnaCijena");
                kalkulacije.add(new DTOKalkulacija(idZaposlenog, datum, idDobavljaca, idZaposlenog, ukupnaCijena));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(kalkulacije);
    }
    
    public boolean upisiKalkulacijuUBazu(Date datumKalkulacije, int idDobavljaca, int idZaposlenog, double ukupnaCijena){
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`kalkulacija` "
                    + " VALUES (default, ?, ?, ?, ?)");
            myStatement.setDate(1, (java.sql.Date) datumKalkulacije);
            myStatement.setInt(2, idDobavljaca);
            myStatement.setInt(3, idZaposlenog);
            myStatement.setDouble(4, ukupnaCijena);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
    public DTOKalkulacija vratiKalkulacijuPoId(int id){
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        DTOKalkulacija retValue=null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from kalkulacija where IdKalkulacije="+id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idNarudzbenice = rs.getInt("IdKalkulacije");
                Date datumRacuna=rs.getDate("DatumKalkulacije");
                int idDobavljaca=rs.getInt("IdDobavljaca");
                int idZaposlenog=rs.getInt("IdZaposlenog");
                double ukupnaCijena=rs.getDouble("UkupnaCijena");
                retValue=new DTOKalkulacija(idZaposlenog, datumRacuna, idDobavljaca, idZaposlenog, ukupnaCijena);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue;
    }
    
     public int idZadnjeKalkulacije() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        int retValue = 0;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select max(IdKalkulacije) as max_id_kalkulacije from kalkulacija");
            rs = ps.executeQuery();

            while (rs.next()) {
                retValue = rs.getInt("max_id_kalkulacije");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue;
    }
    
     public boolean azurirajKalkulaciju(int id, double ukupnaCijena){
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("UPDATE `baby_shop`.`kalkulacija` "
                    + "SET UkupnaCijena=? WHERE IdKalkulacije="+id);
            myStatement.setDouble(1, ukupnaCijena);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKalkulacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
}
