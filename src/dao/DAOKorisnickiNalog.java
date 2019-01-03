package dao;

import connectionpool.ConnectionPool;
import dto.DTOKorisnickiNalog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOKorisnickiNalog {
     private static final String SQL_GET_KORISNICKI_NALOZI = "select * "
            + "from baby_shop.korisnicki_nalog";

    public ObservableList<DTOKorisnickiNalog> getKorisnickiNalozi() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOKorisnickiNalog> korisnici = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_KORISNICKI_NALOZI);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idNaloga=rs.getInt(1);
                String korisnickoIme = rs.getString(2);
                String lozinka = rs.getString(3);
                boolean aktivan = rs.getBoolean(4);
                String tipKorisnika = rs.getString(5);        
                int idZaposlenog = rs.getInt(6);

                korisnici.add(new DTOKorisnickiNalog(korisnickoIme, lozinka, tipKorisnika, idZaposlenog, idNaloga, aktivan));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return FXCollections.observableArrayList(korisnici);
    }
    /*public static boolean deaktivirajKorisnika(DTOKorisnickiNalog korisnik){
        Connection con = null;
        CallableStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareCall("{call deaktiviraj_korisnika(?)}");

            myStatement.setString(1, korisnik.getJmb());
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }*/
    public boolean dodajKorisnickiNalog(String korisnickoIme, String lozinka, String tipKorisnika, 
            int idZaposlenog, int idNaloga, boolean aktivan){
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO baby_shop.korisnicki_nalog (IdNaloga,"
                    + " KorisnickoIme, Lozinka, Aktivan, TipKorisnika, IdZaposlenog)"
                    + " VALUES (?, ?, ?, ?, ?, ?)");
            myStatement.setInt(1, idNaloga);
            myStatement.setString(2, korisnickoIme);
            myStatement.setString(3, lozinka);
            myStatement.setBoolean(4, aktivan);
            myStatement.setString(5, tipKorisnika);
            myStatement.setInt(6, idZaposlenog);
            
            myStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
}
