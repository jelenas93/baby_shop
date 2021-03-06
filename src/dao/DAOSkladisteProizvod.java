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

public class DAOSkladisteProizvod {
    
    public boolean dodajProizvodUSkladiste(int idSkladista,int idProizvoda,int stanje){
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("insert into skladiste_proizvod values(?,?,?)");
            myStatement.setInt(1,idSkladista);
            myStatement.setInt(2, idProizvoda);
            myStatement.setInt(3, stanje);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSkladisteProizvod.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOSkladisteProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOSkladisteProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
    public ArrayList<DTOSkladisteProizvod> pregledSkladista(){
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOSkladisteProizvod> skladisteProizvod = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from skladiste_proizvod");
            rs = ps.executeQuery();

            while (rs.next()) {
                int idSkladista=rs.getInt("IdSkladista");
                int idProizvoda = rs.getInt("IdProizvoda");
                int stanje=rs.getInt("Stanje");
                skladisteProizvod.add(new DTOSkladisteProizvod(idSkladista, idProizvoda, stanje));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSkladisteProizvod.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOSkladisteProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOSkladisteProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return skladisteProizvod;
    }
    
    public boolean azurirajProizvodUSkladistu(int kolicina, int id) {

        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("UPDATE `baby_shop`.`skladiste_proizvod` "
                    + "SET Stanje=? WHERE IdProizvoda="+id);
            myStatement.setInt(1, kolicina);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSkladisteProizvod.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOSkladisteProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOSkladisteProizvod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
}
