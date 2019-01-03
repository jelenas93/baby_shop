package dao;

import connectionpool.ConnectionPool;
import dto.DTOProizvod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOProizvod {
    
    public final String SQL_GET_PROIZVOD="select * from baby_shop.proizvod";
    
     public ObservableList<DTOProizvod> getProizvode() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DTOProizvod> proizvodi = new ArrayList<>();
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_PROIZVOD);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idPorizvoda=rs.getInt(1);
                String barkod=rs.getString(2);
                String sifra=rs.getString(3);
                String naziv=rs.getString(4);
                int kolicina=rs.getInt(5);
                double cijena=rs.getDouble(6);
                String JIBProizvodjaca=rs.getString(7);
                int idGrupe=rs.getInt(8);
                double duzina=rs.getDouble(9);
                double sirina=rs.getDouble(10);
                double visina=rs.getDouble(11);
                int velicina=rs.getInt(12);
                int uzrast=rs.getInt(13);
                String pol=rs.getString(14);
                String boja=rs.getString(15);
                String godisnjeDoba=rs.getString(16);
                proizvodi.add(new DTOProizvod(idPorizvoda, barkod, sifra, naziv,
                        kolicina, cijena, JIBProizvodjaca, idGrupe, duzina, 
                        sirina, visina, velicina, uzrast, pol, boja, godisnjeDoba));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProizvod.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return FXCollections.observableArrayList(proizvodi);
    }
     
      public boolean upisUBazuProizvod(String barkod, String sifra,String naziv,
            int kolicina, double cijena, String JIBProizvodjaca, int idGrupe, 
            double duzina, double sirina,double visina, int velicina, int uzrast,
            String pol, String boja, String godisnjeDoba){
       
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`proizvod` "
                    + "(`IdProizvoda`, `Barkod`, `Sifra`, `Naziv`, `Kolicina`, "
                    + "`Cijena`, `JIBProizvodjaca`, `IdGrupe`, `Duzina`, `Sirina`,"
                    + " `Visina`, `Velicina`, `Uzrast`, `Pol`, `Boja`, `GodisnjeDoba`)"
                    + " VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            myStatement.setString(1, barkod);
            myStatement.setString(2, sifra);
            myStatement.setString(3, naziv);
            myStatement.setInt(4, kolicina);
            myStatement.setDouble(5, cijena);
            myStatement.setString(6, JIBProizvodjaca);
            myStatement.setDouble(8, duzina);
            myStatement.setDouble(9, sirina);
            myStatement.setDouble(10, visina);
            myStatement.setInt(11, velicina);
            myStatement.setInt(12,uzrast);
            myStatement.setString(13,pol);
            myStatement.setString(14,boja);
            myStatement.setString(15,godisnjeDoba);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProizvod.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
   
}
