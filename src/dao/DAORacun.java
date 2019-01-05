/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connectionpool.ConnectionPool;
import dto.DTORacun;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DAORacun {

    public ObservableList<DTORacun> racuni() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTORacun> skladiste = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from racun");
            rs = ps.executeQuery();

            while (rs.next()) {
                int idRacuna = rs.getInt("IdRacuna");
                Date datumRacuna = rs.getDate("DatumRacuna");
                double ukupnaCijena = rs.getDouble("Ukupna_cijena");
                int idZaposlenog = rs.getInt("IdZaposlenog");
                skladiste.add(new DTORacun(idRacuna, idZaposlenog, datumRacuna, ukupnaCijena));
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
        return FXCollections.observableArrayList(skladiste);
    }
    
    public boolean dodajRacun(int idZaposlenog,Date datumRacuna,double ukupnaCijena){
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`racun` "
                    + " VALUES (default, ?, ?, ?)");
            myStatement.setInt(1, idZaposlenog);
            myStatement.setDate(2, (java.sql.Date) datumRacuna);
            myStatement.setDouble(3, ukupnaCijena);
            
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
    
    public DTORacun vratiRacunPoId(int idRacuna){
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        DTORacun retValue=null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from racun where IdRacuna="+idRacuna);
            rs = ps.executeQuery();

            while (rs.next()) {
                int IdRacuna = rs.getInt("IdRacuna");
                Date datumRacuna=rs.getDate("DatumRacuna");
                double UkupnaCijena=rs.getDouble("Ukupna_Cijena");
                int IdZaposlenog=rs.getInt("IdZaposlenog");
                retValue=new DTORacun(IdRacuna, IdZaposlenog, datumRacuna, UkupnaCijena);
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
        return retValue;
    }
    
}
