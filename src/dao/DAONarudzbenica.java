package dao;

import connectionpool.ConnectionPool;
import dto.DTONarudzbenica;
import java.sql.CallableStatement;
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

public class DAONarudzbenica {

    public ObservableList<DTONarudzbenica> getNarudzbenice() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTONarudzbenica> narudzbenice = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from narudzbenica");
            rs = ps.executeQuery();

            while (rs.next()) {
                int idNarudzbenice = rs.getInt("IdNarudzbenice");
                Date datum = rs.getDate("DatumNarudzbenice");
                boolean isporucena = rs.getBoolean("Isporucena");
                int idZaposlenog = rs.getInt("IdZaposlenog");
                double ukupnaCijena = rs.getDouble("UkupnaCijena");
                boolean kalkulisana = rs.getBoolean("Kalkulisana");
                int idDobavljaca = rs.getInt("IdDobavljaca");
                narudzbenice.add(new DTONarudzbenica(idNarudzbenice, datum,
                        isporucena, idZaposlenog, ukupnaCijena, kalkulisana, idDobavljaca));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(narudzbenice);
    }

    public boolean upisiNarudzbenicuUBazu(Date datumRacuna, boolean isporucena, int idZaposlenog, double ukupnaCijena, boolean kalkulisana, int idDobavljaca) {
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("INSERT INTO `baby_shop`.`narudzbenica` "
                    + " VALUES (default, ?, ?, ?, ?, ?, ?)");

            myStatement.setDate(1, (java.sql.Date) datumRacuna);
            myStatement.setBoolean(2, isporucena);
            myStatement.setInt(3, idZaposlenog);
            myStatement.setDouble(4, ukupnaCijena);
            myStatement.setBoolean(5, kalkulisana);
            myStatement.setInt(6, idDobavljaca);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

    public DTONarudzbenica vratiNarudzbenicuPoId(int id) {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        DTONarudzbenica retValue = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from narudzbenica where IdNarudzbenice=" + id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idNarudzbenice = rs.getInt("IdNarudzbenice");
                Date datumRacuna = rs.getDate("DatumNarudzbenice");
                boolean isporucena = rs.getBoolean("Isporuena");
                int idZaposlenog = rs.getInt("IdZaposlenog");
                double ukupnaCijena = rs.getDouble("UkupnaCijena");
                boolean kalkulisana = rs.getBoolean("Kalkulisana");
                int idDobavljaca = rs.getInt("IdDobavljaca");
                retValue = new DTONarudzbenica(idNarudzbenice, datumRacuna, isporucena, idZaposlenog, ukupnaCijena, kalkulisana, idDobavljaca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue;
    }

    public int idZadnjeNarudzbenice() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        int retValue = 0;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select max(IdNarudzbenice) as max_id_narudzbenice from narudzbenica");
            rs = ps.executeQuery();

            while (rs.next()) {
                retValue = rs.getInt("max_id_narudzbenice");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue;
    }

    public boolean azurirajNarudzbenicu(int id, double ukupnaCijena) {
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("UPDATE `baby_shop`.`narudzbenica` "
                    + "SET UkupnaCijena=? WHERE IdNarudzbenice=" + id);
            myStatement.setDouble(1, ukupnaCijena);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

    public ObservableList<DTONarudzbenica> getNarudzbenicePoDobavljacu(int idDobavljaca) {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTONarudzbenica> narudzbenice = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from narudzbenica where IdDobavljaca=" + idDobavljaca);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idNarudzbenice = rs.getInt("IdNarudzbenice");
                Date datum = rs.getDate("DatumNarudzbenice");
                boolean isporucena = rs.getBoolean("Isporucena");
                int idZaposlenog = rs.getInt("IdZaposlenog");
                double ukupnaCijena = rs.getDouble("UkupnaCijena");
                boolean kalkulisana = rs.getBoolean("Kalkulisana");
                narudzbenice.add(new DTONarudzbenica(idNarudzbenice, datum,
                        isporucena, idZaposlenog, ukupnaCijena, kalkulisana, idDobavljaca));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(narudzbenice);
    }
    
     public ObservableList<DTONarudzbenica> getNekalkulisaneNarudzbenicePoDobavljacu(int idDobavljaca) {
        Connection con = null;
     
        CallableStatement ps=null;
        ResultSet rs = null;
        ArrayList<DTONarudzbenica> narudzbenice = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareCall("{call nekalkulisana_narudzba(?)}");
            ps.setInt(1, idDobavljaca);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idNarudzbenice = rs.getInt("IdNarudzbenice");
                Date datum = rs.getDate("DatumNarudzbenice");
                boolean isporucena = rs.getBoolean("Isporucena");
                int idZaposlenog = rs.getInt("IdZaposlenog");
                double ukupnaCijena = rs.getDouble("UkupnaCijena");
                boolean kalkulisana = rs.getBoolean("Kalkulisana");
                narudzbenice.add(new DTONarudzbenica(idNarudzbenice, datum,
                        isporucena, idZaposlenog, ukupnaCijena, kalkulisana, idDobavljaca));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(narudzbenice);
    }

    public boolean setujNaruzbuKalkulisana(int id) {
        Connection con = null;
        PreparedStatement myStatement = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement = con.prepareStatement("UPDATE `baby_shop`.`narudzbenica` "
                    + "SET Kalkulisana=? WHERE IdNarudzbenice=" + id);
            myStatement.setBoolean(1, true);
            myStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAONarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

}
