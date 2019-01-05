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

    public final String SQL_GET_PROIZVOD = "select * from baby_shop.proizvod";

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
                int idPorizvoda = rs.getInt(1);
                String barkod = rs.getString(2);
                String sifra = rs.getString(3);
                String naziv = rs.getString(4);
                int kolicina = rs.getInt(5);
                double cijena = rs.getDouble(6);
                String JIBProizvodjaca = rs.getString(7);
                int idGrupe = rs.getInt(8);
                double duzina = rs.getDouble(9);
                double sirina = rs.getDouble(10);
                double visina = rs.getDouble(11);
                int velicina = rs.getInt(12);
                int uzrast = rs.getInt(13);
                String pol = rs.getString(14);
                String boja = rs.getString(15);
                String godisnjeDoba = rs.getString(16);
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

    public boolean upisUBazuProizvod(String barkod, String sifra, String naziv,
            int kolicina, Double cijena, String JIBProizvodjaca, int idGrupe,
            double duzina, double sirina, double visina, int velicina, int uzrast,
            String pol, String boja, String godisnjeDoba) {

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
            myStatement.setInt(7, idGrupe);
            if (duzina != 0) {
                myStatement.setDouble(8, duzina);
            } else {
                myStatement.setNull(8, java.sql.Types.NULL);
            }
            if (sirina != 0) {
                myStatement.setDouble(9, sirina);
            } else {
                myStatement.setNull(9, java.sql.Types.NULL);
            }
            if (visina != 0) {
                myStatement.setDouble(10, visina);
            } else {
                myStatement.setNull(10, java.sql.Types.NULL);
            }
            if (velicina != 0) {
                myStatement.setInt(11, velicina);
            } else {
                myStatement.setNull(11, java.sql.Types.NULL);
            }
            if (uzrast != 0) {
                myStatement.setInt(12, uzrast);
            } else {
                myStatement.setNull(12, java.sql.Types.NULL);
            }
            if (!("".equals(pol))) {
                myStatement.setString(13, pol);
            } else {
                myStatement.setNull(13, java.sql.Types.NULL);
            }
            if (!("".equals(boja))) {
                myStatement.setString(14, boja);
            } else {
                myStatement.setNull(14, java.sql.Types.NULL);
            }
            if (!("".equals(godisnjeDoba))) {
                myStatement.setString(15, godisnjeDoba);
            } else {
                myStatement.setNull(15, java.sql.Types.NULL);
            }
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

    public int idProizvoda() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        int retValue = 0;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select max(IdProizvoda) as max_id_proizvoda from proizvod");
            rs = ps.executeQuery();

            while (rs.next()) {
                retValue = rs.getInt("max_id_proizvoda");
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

    public DTOProizvod getProizvodPoBarkodu(String barkod) {
        
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        DTOProizvod dTOProizvod = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.proizvod where Barkod like '" + barkod + "%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                int idPorizvoda = rs.getInt(1);
                String barkodProizvoda = rs.getString(2);
                String sifra = rs.getString(3);
                String naziv = rs.getString(4);
                int kolicina = rs.getInt(5);
                double cijena = rs.getDouble(6);
                String JIBProizvodjaca = rs.getString(7);
                int idGrupe = rs.getInt(8);
                double duzina = rs.getDouble(9);
                double sirina = rs.getDouble(10);
                double visina = rs.getDouble(11);
                int velicina = rs.getInt(12);
                int uzrast = rs.getInt(13);
                String pol = rs.getString(14);
                String boja = rs.getString(15);
                String godisnjeDoba = rs.getString(16);
                dTOProizvod =new DTOProizvod(idPorizvoda, barkodProizvoda, sifra, naziv,
                        kolicina, cijena, JIBProizvodjaca, idGrupe, duzina,
                        sirina, visina, velicina, uzrast, pol, boja, godisnjeDoba);
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
        return dTOProizvod;
    }

    public DTOProizvod getProizvodPoSifri(String sifra) {
        
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        DTOProizvod dTOProizvod = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.proizvod where Sifra like '" + sifra + "%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                int idPorizvoda = rs.getInt(1);
                String barkod = rs.getString(2);
                String sifraProizvoda = rs.getString(3);
                String naziv = rs.getString(4);
                int kolicina = rs.getInt(5);
                double cijena = rs.getDouble(6);
                String JIBProizvodjaca = rs.getString(7);
                int idGrupe = rs.getInt(8);
                double duzina = rs.getDouble(9);
                double sirina = rs.getDouble(10);
                double visina = rs.getDouble(11);
                int velicina = rs.getInt(12);
                int uzrast = rs.getInt(13);
                String pol = rs.getString(14);
                String boja = rs.getString(15);
                String godisnjeDoba = rs.getString(16);
                dTOProizvod =new DTOProizvod(idPorizvoda, barkod, sifraProizvoda, naziv,
                        kolicina, cijena, JIBProizvodjaca, idGrupe, duzina,
                        sirina, visina, velicina, uzrast, pol, boja, godisnjeDoba);
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
        return dTOProizvod;
    }

    public ObservableList<DTOProizvod> getProizvodPoNazivu(String Naziv) {
        
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOProizvod> dTOProizvodi = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.proizvod where Naziv like '" + Naziv + "%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                int idPorizvoda = rs.getInt(1);
                String barkodProizvoda = rs.getString(2);
                String sifra = rs.getString(3);
                String naziv = rs.getString(4);
                int kolicina = rs.getInt(5);
                double cijena = rs.getDouble(6);
                String JIBProizvodjaca = rs.getString(7);
                int idGrupe = rs.getInt(8);
                double duzina = rs.getDouble(9);
                double sirina = rs.getDouble(10);
                double visina = rs.getDouble(11);
                int velicina = rs.getInt(12);
                int uzrast = rs.getInt(13);
                String pol = rs.getString(14);
                String boja = rs.getString(15);
                String godisnjeDoba = rs.getString(16);
                dTOProizvodi.add(new DTOProizvod(idPorizvoda, barkodProizvoda, sifra, naziv,
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
        return FXCollections.observableArrayList(dTOProizvodi);
    }
}
