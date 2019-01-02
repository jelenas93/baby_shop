package dao;

import connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import dto.DTOProizvodGrupa;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;

public class DAOProizvodGrupa {

    public final String SQL_GET_PROIZVOD_GRUPA="select * from baby_shop.proizvod_grupa";
    
    public ObservableList<DTOProizvodGrupa> getGrupeProizvoda() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DTOProizvodGrupa> grupaProizvoda = new ArrayList<>();
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_PROIZVOD_GRUPA);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idTpaPorizvoda=rs.getInt(1);
                String nazivTipaProizvoda=rs.getString(2);
                boolean duzina=rs.getBoolean(3);
                boolean sirina=rs.getBoolean(4);
                boolean visina=rs.getBoolean(5);
                boolean velicina=rs.getBoolean(6);
                boolean uzrast=rs.getBoolean(7);
                boolean pol=rs.getBoolean(8);
                boolean boja=rs.getBoolean(9);
                boolean godisnjeDoba=rs.getBoolean(10);
                grupaProizvoda.add(new DTOProizvodGrupa(idTpaPorizvoda, nazivTipaProizvoda,
                duzina,sirina,visina, velicina, uzrast,pol,boja,godisnjeDoba));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProizvodGrupa.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvodGrupa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvodGrupa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return FXCollections.observableArrayList(grupaProizvoda);
    }
    
  /*   public int getNazivProizvod(String ime){
        int idGrupe=0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select IdGrupe from baby_shop.grupa_proizvod where NazivTipaProizvoda="+ime);
            rs = ps.executeQuery();
            idGrupe=rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProizvodGrupa.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvodGrupa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvodGrupa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return idGrupe;
        
    } 
    */
    public boolean upisUBazuGrupu(String naziv, boolean duzina, boolean sirina,
            boolean visina,boolean velicina, boolean uzrast, boolean pol,
            boolean boja, boolean godisnjeDoba){
       
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`proizvod_grupa`"
                    + " (`IdGrupe`, `NazivTipaProizvoda`, `Duzina`, `Sirina`, "
                    + "`Visina`, `Velicina`, `Uzrast`, `Pol`, `Boja`, `GodisnjeDoba`)"
                    + " VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            myStatement.setString(1, naziv);
            myStatement.setBoolean(2, duzina);
            myStatement.setBoolean(3, sirina);
            myStatement.setBoolean(4, visina);
            myStatement.setBoolean(5, velicina);
            myStatement.setBoolean(6,uzrast);
            myStatement.setBoolean(7,pol);
            myStatement.setBoolean(8,boja);
            myStatement.setBoolean(9,godisnjeDoba);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProizvodGrupa.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvodGrupa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvodGrupa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
   
}
