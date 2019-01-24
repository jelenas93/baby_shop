package dao;

import connectionpool.ConnectionPool;
import dto.DTORacun;
import java.sql.CallableStatement;
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
        ArrayList<DTORacun> racuni = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from racun");
            rs = ps.executeQuery();

            while (rs.next()) {
                int idRacuna = rs.getInt("IdRacuna");
                Date datumRacuna = rs.getDate("DatumRacuna");
                double ukupnaCijena = rs.getDouble("UkupnaCijena");
                int idZaposlenog = rs.getInt("IdZaposlenog");
                boolean storniran = rs.getBoolean("Storniran");
                racuni.add(new DTORacun(idRacuna, idZaposlenog, datumRacuna, ukupnaCijena, storniran));
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
        return FXCollections.observableArrayList(racuni);
    }

    public boolean dodajRacun(int idZaposlenog, Date datumRacuna, double ukupnaCijena, boolean storniran) {
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`racun` "
                    + " VALUES (default, ?, ?, ?, ?)");

            myStatement.setDate(1, (java.sql.Date) datumRacuna);
            myStatement.setDouble(2, ukupnaCijena);
            myStatement.setInt(3, idZaposlenog);
            myStatement.setBoolean(4, storniran);
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

    public DTORacun vratiRacunPoId(int idRacuna) {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        DTORacun retValue = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from racun where IdRacuna=" + idRacuna);
            rs = ps.executeQuery();

            while (rs.next()) {
                int IdRacuna = rs.getInt("IdRacuna");
                Date datumRacuna = rs.getDate("DatumRacuna");
                double UkupnaCijena = rs.getDouble("UkupnaCijena");
                int IdZaposlenog = rs.getInt("IdZaposlenog");
                boolean storniran = rs.getBoolean("Storniran");
                retValue = new DTORacun(IdRacuna, IdZaposlenog, datumRacuna, UkupnaCijena, storniran);
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

    public boolean azurirajRacun(int id, boolean storniran) {
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("UPDATE `baby_shop`.`racun` "
                    + "SET storniran=? WHERE IdRacuna=" + id);
            myStatement.setBoolean(1, storniran);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
          // AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Nemate dovoljno proizvoda na stanju.");
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

    public ObservableList<DTORacun> getRacunePoDatumu(Date datum){
        Connection con = null;
        CallableStatement call=null;
        ResultSet rs = null;
        ArrayList<DTORacun> racuni = new ArrayList<>();
        try {
            con = ConnectionPool.getInstance().checkOut();
            call=con.prepareCall("{call suma_racuna(?)}");
            call.setDate(1, (java.sql.Date) datum);
            rs = call.executeQuery();

            while (rs.next()) {
                int idRacuna = rs.getInt("IdRacuna");
                Date datumRacuna = rs.getDate("DatumRacuna");
                double ukupnaCijena = rs.getDouble("UkupnaCijena");
                int idZaposlenog = rs.getInt("IdZaposlenog");
                boolean storniran = rs.getBoolean("Storniran");
                racuni.add(new DTORacun(idRacuna, idZaposlenog, datumRacuna, ukupnaCijena, storniran));
            }
        } catch (SQLException ex) {
             Logger.getLogger(DAORacun.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (call != null) {
                try {
                    call.close();
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
        return FXCollections.observableArrayList(racuni);
    }
}
