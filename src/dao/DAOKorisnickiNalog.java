package dao;

import connectionpool.ConnectionPool;
import dto.DTOKorisnickiNalog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOKorisnickiNalog {

    private static final String SQL_GET_KORISNICKI_NALOZI = "select * "
            + "from baby_shop.korisnicki_nalog";

    public static ObservableList<DTOKorisnickiNalog> getKorisnickiNalozi() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOKorisnickiNalog> korisnici = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_KORISNICKI_NALOZI);
            rs = ps.executeQuery();

            while (rs.next()) {
                String korisnickoIme = rs.getString(2);
                byte[] lozinka = rs.getBytes(3);
                boolean aktivan = rs.getBoolean(4);
                String tipKorisnika = rs.getString(5);
                int idZaposlenog = rs.getInt(6);

                korisnici.add(new DTOKorisnickiNalog(korisnickoIme, lozinka, aktivan, tipKorisnika, idZaposlenog));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return FXCollections.observableArrayList(korisnici);
    }

    public static DTOKorisnickiNalog getNalogById(int idNaloga) {

        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        DTOKorisnickiNalog korisnik = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("SELECT * FROM baby_shop.korisnicki_nalog"
                    + " WHERE IdNaloga = ?");
            ps.setInt(1, idNaloga);
            rs = ps.executeQuery();

            while (rs.next()) {

                int IDNaloga = rs.getInt(1);
                String korisnickoIme = rs.getString(2);
                byte[] lozinka = rs.getBytes(3);
                boolean aktivan = rs.getBoolean(4);
                String tipKorisnika = rs.getString(5);
                int idZaposlenog = rs.getInt(6);

                korisnik = new DTOKorisnickiNalog(IDNaloga, korisnickoIme, lozinka, aktivan, tipKorisnika, idZaposlenog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return korisnik;
    }

    public static boolean deaktivirajNalog(int idNaloga, int idZaposlenog) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("UPDATE baby_shop.korisnicki_nalog SET "
                    + "Aktivan = ? WHERE (IdNaloga = ?) and (IdZaposlenog = ?)");

            ps.setBoolean(1, Boolean.FALSE); //ne radiii ovo a dobra sql komanda provjeriti brojanje ps?!
            ps.setInt(2, idNaloga);
            ps.setInt(3, idZaposlenog);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

    public static boolean izmijeniNalog(DTOKorisnickiNalog nalog) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("UPDATE baby_shop.korisnicki_nalog SET "
                    + "KorisnickoIme = ?,Lozinka = ?,TipKorisnika = ? WHERE (IdNaloga = ?) and (IdZaposlenog = ?)");

            ps.setString(1, nalog.getKorisnickoIme());
            ps.setBytes(2, nalog.getLozinka());
            ps.setString(3, nalog.getTipKorisnika());
            ps.setInt(4, nalog.getIdNaloga());
            ps.setInt(5, nalog.getIdZaposlenog());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

    public boolean dodajKorisnickiNalog(String korisnickoIme, String lozinka, boolean aktivan,
            String tipKorisnika, int idZaposlenog) throws NoSuchAlgorithmException, IOException {
        Connection con = null;
        PreparedStatement myStatement = null;
        byte[] hes = hesiranjeLozinke(lozinka);

        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO baby_shop.korisnicki_nalog ("
                    + " KorisnickoIme, Lozinka, Aktivan, TipKorisnika, IdZaposlenog)"
                    + " VALUES (?, ?, ?, ?, ?)");
            myStatement.setString(1, korisnickoIme);
            myStatement.setBytes(2, hes);
            myStatement.setBoolean(3, aktivan);
            myStatement.setString(4, tipKorisnika);
            myStatement.setInt(5, idZaposlenog);
            myStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOKorisnickiNalog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

    public byte[] hesiranjeLozinke(String unesenaLozinka) throws NoSuchAlgorithmException, IOException {

        final Random rn = new SecureRandom();
        byte[] salt = new byte[32];
        rn.nextBytes(salt);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        byte[] hesiranaLozinka = digest.digest(unesenaLozinka.getBytes());
        System.out.println(hesiranaLozinka);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(hesiranaLozinka);
        outputStream.write(salt);
        byte []hesISalt = outputStream.toByteArray();

        return hesISalt;

    }
}
