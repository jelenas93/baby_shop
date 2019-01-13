/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connectionpool.ConnectionPool;
import dto.DTOKorisnickiNalog;
import dto.DTOKorisnikWrapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tijana Lakic
 */
public class DAOKorisnikWrapper {
    private static final String SQL_GET_KORISNICI_WRAPPER=" select baby_shop.korisnicki_nalog.IdNaloga,"
            + "baby_shop.korisnicki_nalog.IdZaposlenog from baby_shop.korisnicki_nalog WHERE Aktivan=1";
    
        public static ObservableList<DTOKorisnikWrapper> getKorisnickiNaloziWrappers() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOKorisnikWrapper> korisnici = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_KORISNICI_WRAPPER);
            rs = ps.executeQuery();

            while (rs.next()) {
         
                
                korisnici.add(new DTOKorisnikWrapper(DAOKorisnickiNalog.getNalogById(rs.getInt(1)),
                        DAOZaposleni.getZaposleniById(rs.getInt(2))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOKorisnikWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnikWrapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnikWrapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return FXCollections.observableArrayList(korisnici);
    }
    
            
       
        
        
}
