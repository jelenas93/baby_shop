package dao;

import connectionpool.ConnectionPool;
import dto.DTOStavkaKalkulacije;
import dto.DTOStavkaNarudzbe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStavkaKalkulacije {
     public boolean upisUBazuStavkuKalkulacije(int idKalkulacije, int idProizvoda,
             double cijena, int kolicina, String jedinicaMjere, double rabat, double marza, int pdv , double cijenaProizvoda){
        
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`kalkulacija_proizvod` "
                    + "(`RedniBrojStavkeKalkulacije`, `IdKalkulacije`,`IdProizvoda`, `FakturnaCijena`, `Kolicina`, `JedinicaMjere`,`Rabat`,`Marza`, `PDV`, `Cijena`)"
                    + " VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            myStatement.setInt(1, idKalkulacije);
            myStatement.setInt(2, idProizvoda);
            myStatement.setDouble(3, cijena);
            myStatement.setInt(4, kolicina);
            myStatement.setString(5, jedinicaMjere);
            myStatement.setDouble(6, rabat);
            myStatement.setDouble(7, marza);
            myStatement.setInt(8, pdv);
            myStatement.setDouble(9, cijenaProizvoda);
            myStatement.execute();
           // new DAOProizvod().dodajCijenuProizvodu(idProizvoda, cijenaProizvoda, kolicina);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStavkaKalkulacije.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStavkaKalkulacije.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStavkaKalkulacije.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    
    public ArrayList<DTOStavkaKalkulacije> stavkeNaKalkulaciji(int idKalkulacije) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DTOStavkaKalkulacije> stavke = new ArrayList<>();
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.kalkulacija_proizvid where IdKalkulacije="+idKalkulacije);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idStavke=rs.getInt("RedniBrojStavkeKalkulacije");
                int idProizvoda=rs.getInt("IdProizvoda");
                int kolicina=rs.getInt("Kolicina");
                double cijena=rs.getDouble("JedinicnaCijena");
                int rabat=rs.getInt("Rabat");
                int marza=rs.getInt("Marza");
                int pdv=rs.getInt("PDV");
                String jedinicaMjere=rs.getString("JedinicaMjere");
                stavke.add(new DTOStavkaKalkulacije(idKalkulacije, idProizvoda, cijena, kolicina, jedinicaMjere, rabat, marza, pdv));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStavkaKalkulacije.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStavkaKalkulacije.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOStavkaKalkulacije.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return stavke;
    }
    
    
}
