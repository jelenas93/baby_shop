package dao;

import connectionpool.ConnectionPool;
import dto.DTOTipProizvoda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOTipProizvoda {
   
    public static String SQL_GET_TIP_PROIZVODA="select * from baby_shop.tip_proizvoda";
      
    public ObservableList<DTOTipProizvoda> getTipoveProizvoda() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DTOTipProizvoda> tipoviProizvoda = new ArrayList<>();
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_TIP_PROIZVODA);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idTpaPorizvoda=rs.getInt(1);
                String nazivTipaProizvoda=rs.getString(2);
                tipoviProizvoda.add(new DTOTipProizvoda(idTpaPorizvoda, nazivTipaProizvoda));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipProizvoda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTipProizvoda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTipProizvoda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return FXCollections.observableArrayList(tipoviProizvoda);
    }
    
    public boolean upisUBazu(String proizvod){
       
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`tip_proizvoda` (`IdTip`, `NazivTipaProizvoda`) VALUES (default, ?)");
            myStatement.setString(1, proizvod);
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
    }
    
}
