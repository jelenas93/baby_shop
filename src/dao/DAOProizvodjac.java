package dao;

import connectionpool.ConnectionPool;
import dto.DTOProizvodjac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOProizvodjac {

    public final String SQL_GET_PROIZVODJAC = "select * from baby_shop.proizvodjac";

    public ObservableList<DTOProizvodjac> getProizvodjace() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOProizvodjac> proizvodjaci = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_PROIZVODJAC);
            rs = ps.executeQuery();

            while (rs.next()) {
                String JIBProizvodjaca = rs.getString(1);
                String nazivProizvodjaca = rs.getString(2);
                int postanskiBroj = rs.getInt(3);
                proizvodjaci.add(new DTOProizvodjac(JIBProizvodjaca, nazivProizvodjaca, postanskiBroj));
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

        return FXCollections.observableArrayList(proizvodjaci);
    }

    public boolean upisUBazuProizvodjaca(String JIBProizvodjaca, String naziv, int postanskiBroj) {

        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`proizvodjac`"
                    + " (`JIBProizvodjaca`, `Naziv`, `PostanskiBroj`)"
                    + " VALUES (?, ?, ?)");
            myStatement.setString(1, JIBProizvodjaca);
            myStatement.setString(2, naziv);
            myStatement.setInt(3, postanskiBroj);
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
    
    public DTOProizvodjac proizvodjacNaOsnovuJIB(String jib) {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        DTOProizvodjac proizvodjac = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from proizvodjac where JIBProizvodjaca like '" + jib + "%'");
            rs = ps.executeQuery();

            while (rs.next()) {
                String JIBProizvodjaca = rs.getString("JIBProizvodjaca");
                String nazivProizvodjaca = rs.getString(2);
                int postanskiBroj = rs.getInt(3);
                proizvodjac=new DTOProizvodjac(JIBProizvodjaca, nazivProizvodjaca, postanskiBroj);
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
        return proizvodjac;
    }
}
