package dao;

import dto.DTOTipZaposlenog;
import java.sql.Connection;
import connectionpool.ConnectionPool;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOTipZaposlenog {

    public final String SQL_GET_TIP_ZAPOSLENOG = "select * from baby_shop.tip_zaposlenog";

    public ObservableList<DTOTipZaposlenog> getTipZaposlenog() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DTOTipZaposlenog> tipzaposlenog = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_TIP_ZAPOSLENOG);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idTipaZaposlenog = rs.getInt(1);
                String nazivTipaZaposlenog = rs.getString(2);
                tipzaposlenog.add(new DTOTipZaposlenog(idTipaZaposlenog, nazivTipaZaposlenog));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(tipzaposlenog);

    }
    
    public boolean dodajTipZaposlenog(String nazivTipaZaposlenog){
    
   
            Connection con=null;
            PreparedStatement ps=null;
            try {     
            con=ConnectionPool.getInstance().checkOut();
            ps=con.prepareStatement("insert into baby_shop.tip_zaposlenog "
                    + "(NazivTipa) VALUES (?)");
            ps.setString(1, nazivTipaZaposlenog);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    
    }
  
    public static DTOTipZaposlenog getTipZaposlenogById(int id) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTOTipZaposlenog tipzaposlenog = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.tip_zaposlenog where IdTipa=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idTipaZaposlenog = rs.getInt(1);
                String nazivTipaZaposlenog = rs.getString(2);
                tipzaposlenog=new DTOTipZaposlenog(idTipaZaposlenog, nazivTipaZaposlenog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return tipzaposlenog;

    }
    
    public static int getIdTipaZaposlenog(String tipZaposlenog){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idTipaZaposlenog=0;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.tip_zaposlenog where NazivTipa=?");
            ps.setString(1, tipZaposlenog);
            rs = ps.executeQuery();
            while (rs.next()) {
                idTipaZaposlenog = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTipZaposlenog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return idTipaZaposlenog;
    }
}
