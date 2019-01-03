/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DTOZaposleni;
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
public class DAOZaposleni {
    
    public final String SQL_GET_ZAPOSLENI="select * from baby_shop.zaposleni";
    
    public ObservableList<DTOZaposleni> getZaposleni(){
    
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<DTOZaposleni> zaposleni=new ArrayList<>();
        try{
        
            con=connectionpool.ConnectionPool.getInstance().checkOut();
            ps=con.prepareStatement(SQL_GET_ZAPOSLENI);
            rs=ps.executeQuery();
            while(rs.next()){
                int idZaposlenog=rs.getInt(1);
                String jmbg=rs.getString(2);
                String ime=rs.getString(3);
                String prezime=rs.getString(4);
                double iznosPlate=rs.getDouble(5);
                String mejl=rs.getString(6);
                int postanskiBroj=rs.getInt(7);
                int idTipa=rs.getInt(8);
                zaposleni.add(new DTOZaposleni(ime, prezime, jmbg, iznosPlate, mejl,idZaposlenog, postanskiBroj, idZaposlenog));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
        }   finally{
        
            if(con!=null){
            
                connectionpool.ConnectionPool.getInstance().checkIn(con);
                
            }
            if(ps!=null){
                try{
                
                    ps.close();
                }catch(SQLException ex){
                
                    Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null,ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(zaposleni);     
    }
    public boolean dodajZaposlenog(String ime,String prezime,String jmbg,double iznosPlate,
            String mejl,int idZaposlenog,int postanskiBroj,int idTipZaposlenog){
        
        Connection con=null;
        PreparedStatement ps=null;
        
        try {
            con=connectionpool.ConnectionPool.getInstance().checkOut();
            ps=con.prepareStatement("INSERT INTO baby_shop.zaposleni"
            +"( IdZaposlenog,JMBG,Ime,Prezime,IznosPlate,Email,PostanskiBroj,IdTipa )"
            + " VALUES( default,?,?,?,?,?,?)");
            ps.setString(1, jmbg);
            ps.setString(2, ime);
            ps.setString(3, prezime);
            ps.setDouble(4, iznosPlate);
            ps.setString(5, mejl);
            ps.setInt(6, postanskiBroj);
            


        } catch (SQLException ex) {
            Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
   
}

        
    

