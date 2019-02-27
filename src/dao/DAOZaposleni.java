package dao;

import connectionpool.ConnectionPool;
import dto.DTOMjesto;
import dto.DTOZaposleni;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOZaposleni {

    // public static final String SQL_GET_ZAPOSLENI = "select * from baby_shop.zaposleni";
    public static final String SQL_GET_ZAPOSLENI = "SELECT baby_shop.zaposleni.* FROM baby_shop.zaposleni "
            + "INNER JOIN baby_shop.korisnicki_nalog ON  "
            + "baby_shop.korisnicki_nalog.IdZaposlenog=baby_shop.zaposleni.IdZaposlenog and"
            + " baby_shop.korisnicki_nalog.aktivan=1;";

    public static ObservableList<DTOZaposleni> getZaposleni() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DTOZaposleni> zaposleni = new ArrayList<>();
        try {

            con = connectionpool.ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_ZAPOSLENI);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idZaposlenog = rs.getInt(1);
                String jmbg = rs.getString(2);
                String ime = rs.getString(3);
                String prezime = rs.getString(4);
                double iznosPlate = rs.getDouble(5);
                String mejl = rs.getString(6);
                int postanskiBroj = rs.getInt(7);
                int idTipaZaposlenog = rs.getInt(8);
                zaposleni.add(new DTOZaposleni(idZaposlenog, ime, prezime, jmbg, iznosPlate, mejl,
                        postanskiBroj, idTipaZaposlenog));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (con != null) {

                connectionpool.ConnectionPool.getInstance().checkIn(con);

            }
            if (ps != null) {
                try {

                    ps.close();
                } catch (SQLException ex) {

                    Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(zaposleni);
    }

    public static DTOZaposleni getZaposleniById(int IDZaposlenog) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTOZaposleni zaposleni = null;
        try {

            con = connectionpool.ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("SELECT * FROM baby_shop.zaposleni"
                    + " WHERE IdZaposlenog =? ");
            ps.setInt(1, IDZaposlenog);
            rs = ps.executeQuery();
            while (rs.next()) {

                int idZaposlenog = rs.getInt(1);
                String jmbg = rs.getString(2);
                String ime = rs.getString(3);
                String prezime = rs.getString(4);
                double iznosPlate = rs.getDouble(5);
                String mejl = rs.getString(6);
                int postanskiBroj = rs.getInt(7);
                int idTipZaposlenog = rs.getInt(8);

                zaposleni = new DTOZaposleni(idZaposlenog, ime, prezime, jmbg, iznosPlate, mejl,
                        postanskiBroj, idTipZaposlenog);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (con != null) {

                connectionpool.ConnectionPool.getInstance().checkIn(con);

            }
            if (ps != null) {
                try {

                    ps.close();
                } catch (SQLException ex) {

                    Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return zaposleni;
    }

    public int dodajZaposlenog(String ime, String prezime, String jmbg, double iznosPlate,
            String mejl, int postanskiBroj, int idTipZaposlenog) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int last_inserted_id = -1;
        try {
            con = connectionpool.ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("INSERT INTO baby_shop.zaposleni"
                    + "( JMBG,Ime,Prezime,IznosPlate,Email,PostanskiBroj,IdTipa)"
                    + " VALUES( ?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, jmbg);
            ps.setString(2, ime);
            ps.setString(3, prezime);
            ps.setDouble(4, iznosPlate);
            ps.setString(5, mejl);
            ps.setInt(6, postanskiBroj);
            ps.setInt(7, idTipZaposlenog);
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                
                last_inserted_id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
        return last_inserted_id;
    }

    public static boolean izmjeniZaposlenog(DTOZaposleni zaposleni) {
        zaposleni.toString();
        Connection con = null;
        CallableStatement ps = null;

        try {
            con = connectionpool.ConnectionPool.getInstance().checkOut();
             ps = con.prepareCall("call azuriraj_zaposlenog(?,?,?,?,?,?,?,?)");

            //ps = con.prepareStatement("UPDATE baby_shop.zaposleni SET JMBG=?,Ime=?,Prezime=?,IznosPlate=?,Email=?,PostanskiBroj=? WHERE  IdZaposlenog = ?");
            ps.setString(1, zaposleni.getJMBG());
            ps.setString(2, zaposleni.getIme());
            ps.setString(3, zaposleni.getPrezime());
            ps.setDouble(4, zaposleni.getIznosPlate());
            ps.setString(5, zaposleni.getMejl());
            ps.setInt(6, zaposleni.getPostanskiBroj());
            ps.setInt(7, zaposleni.getIdTipZaposlenog());
            ps.setInt(8, zaposleni.getIdZaposlenog());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

    public static boolean izmjeniTipZaposlenog(int tip,int idZaposlenog){
         Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionpool.ConnectionPool.getInstance().checkOut();
            /* ps = con.prepareStatement("UPDATE baby_shop.zaposleni SET"
                    + "JMBG='?',Ime='?',Prezime='?',IznosPlate='?',Email='?',PostanskiBroj='?',IdTipa=?"
                    + " WHERE  IdZaposlenog = '?' ");*/

            ps = con.prepareStatement("UPDATE baby_shop.zaposleni SET IdTipa=? WHERE  IdZaposlenog = ?");
            ps.setInt(1, tip);
            ps.setInt(2,idZaposlenog );
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

     public static int getIdZaposleniByJMBG(String JMBG) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idZaposlenog=0;
        try {

            con = connectionpool.ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("SELECT IdZaposlenog FROM baby_shop.zaposleni"
                    + " WHERE JMBG =? ");
            ps.setString(1, JMBG);
            rs = ps.executeQuery();
            while (rs.next()) {
                idZaposlenog = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (con != null) {

                connectionpool.ConnectionPool.getInstance().checkIn(con);

            }
            if (ps != null) {
                try {

                    ps.close();
                } catch (SQLException ex) {

                    Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOZaposleni.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return idZaposlenog;
     }
}
