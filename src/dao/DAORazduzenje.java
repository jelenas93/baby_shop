package dao;

import connectionpool.ConnectionPool;
import dto.DTOPazarIzvjestaj;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAORazduzenje {
    
     public int idZadnjegRazduzenja() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        int retValue = 0;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select max(BrojPazara) as max_id from razduzenje");
            rs = ps.executeQuery();

            while (rs.next()) {
                retValue = rs.getInt("max_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORazduzenje.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORazduzenje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORazduzenje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue;
    }
     
      public boolean upisiRazduzenjeUBazu(Date datumRacuna, double ukupnaCijena, String tipRacuna) {
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`razduzenje` "
                    + " VALUES (default, ?, ?, ?)");
            myStatement.setDate(1, (java.sql.Date) datumRacuna);
            myStatement.setDouble(2, ukupnaCijena);
            myStatement.setString(3, tipRacuna);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAORazduzenje.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORazduzenje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORazduzenje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
      
    public boolean provjeraRazduzenjaPoDatumu(Date datum){
        Connection con = null;
        CallableStatement prep=null;
        ResultSet rs = null;
        boolean result=false;
        try {
            con = ConnectionPool.getInstance().checkOut();
            prep=con.prepareCall("{call razduzenje_provjera_datum(?)}");
            prep.setDate(1, (java.sql.Date) datum);
            rs = prep.executeQuery();

            while (rs.next()) {
               result=true;
            }
        } catch (SQLException ex) {
             Logger.getLogger(DAORazduzenje.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (prep != null) {
                try {
                    prep.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORazduzenje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAORazduzenje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

}
