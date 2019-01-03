/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connectionpool.ConnectionPool;
import dto.DTODobavljac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAODobavljac {
    
    public ObservableList<DTODobavljac> dobavljaci() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTODobavljac> dobavljaci = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.dobavljac");
            rs = ps.executeQuery();

            while (rs.next()) {
                int idDobavljaca=rs.getInt("IdDobavljaca");
                String nazivDobavljaca=rs.getString("Naziv");
                String JIBDobavljaca = rs.getString("JIBDobavljaca");
                int postanskiBroj = rs.getInt("PostanskiBroj");
                String email=rs.getString("Email");
                String adresa=rs.getString("Adresa");
                String telefon=rs.getString("Telefon");
                dobavljaci.add(new DTODobavljac(idDobavljaca, postanskiBroj, nazivDobavljaca, email, adresa, telefon, JIBDobavljaca));
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

        return FXCollections.observableArrayList(dobavljaci);
    }
    
    public boolean dodajDobavljaca(int postanskiBroj,String nazivDobavljaca,String email,String adresa,String telefon,String JIBDobavljaca) {

        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("insert into dobavljac values(default,?,?,?,?,?,?)");
            myStatement.setInt(1,postanskiBroj);
            myStatement.setString(2, nazivDobavljaca);
            myStatement.setString(3, email);
            myStatement.setString(4, JIBDobavljaca);
            myStatement.setString(5, adresa);
            myStatement.setString(6, telefon);
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
}