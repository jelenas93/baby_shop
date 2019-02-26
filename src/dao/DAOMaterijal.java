package dao;

import connectionpool.ConnectionPool;
import dto.DTOMaterijal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOMaterijal{
    
    public static String SQL_GET_MATERIJAL="select * from baby_shop.materijal";
   
    public ObservableList<DTOMaterijal> getMaterijal() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOMaterijal> materijali = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_MATERIJAL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idMaterijala=rs.getInt(1);
                String nazivMaterijala=rs.getString(2);
                materijali.add(new DTOMaterijal(idMaterijala,nazivMaterijala));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return FXCollections.observableArrayList(materijali);
    }
    
    public int idMaterijalaOdIdProizvoda(int idProizvoda){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idMaterijala=0;
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select IdMaterijala from baby_shop.proizvod_materijal where IdProizvoda like '" + idProizvoda + "%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                    idMaterijala = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return idMaterijala;
    }
    
    public String nazivMaterijalaOdId(int idMaterijala){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String nazivMaterijala="";
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select Naziv from baby_shop.materijal where IdMaterijala like '" + idMaterijala + "%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                    nazivMaterijala = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return nazivMaterijala;
    }
    
    public boolean dodajUbazuProizvodMaterijal(int idMaterijala,int idProizvoda){
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("insert into proizvod_materijal values(?,?)");
            myStatement.setInt(1,idMaterijala);
            myStatement.setInt(2, idProizvoda);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
    public int idMaterijalaOdNaziva(String naziv){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id=0;
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select id from baby_shop.materijal where Naziv like '" + naziv + "%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                    id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return id;
    }
}
