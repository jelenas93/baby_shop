package dao;

import connectionpool.ConnectionPool;
import dto.DTOSkladisteProizvod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAODobavljacProizvod {
    
    public boolean dodajProizvodUSkladiste(int idDobavljaca,int idProizvoda){
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("insert into dobavljac_proizvod values(?,?)");
            myStatement.setInt(1,idDobavljaca);
            myStatement.setInt(2, idProizvoda);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAODobavljacProizvod.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljacProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljacProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
   
}
