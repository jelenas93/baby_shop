package dao;

import connectionpool.ConnectionPool;
import dto.DTODobavljac;
import dto.DTODobavljacIzvjestaj;
import dto.DTODobavljacProizvod;
import dto.DTOIzvjestajNaruceneRobeDobavljac;
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

public class DAODobavljac {
    
    public ObservableList<DTODobavljac> getDobavljace() {
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
    
    public boolean dodajDobavljaca(int postanskiBroj,String nazivDobavljaca,String email,String adresa,String telefon,String JIBDobavljaca) 
    {

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
    
    public DTODobavljac getDobavljacPoNazivu(String ime){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DTODobavljac dobavljac=null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.dobavljac where Naziv like'"+ime+"%'");
            rs = ps.executeQuery();

            while (rs.next()) {
                int id=rs.getInt("IdDobavljaca");
                int postanskiBroj=rs.getInt("PostanskiBroj");
                String email=rs.getString("Email");
                String adresa=rs.getString("Adresa");
                String telefon=rs.getString("Telefon");
                String jib=rs.getString("JIBDobavljaca");
                dobavljac=new DTODobavljac(id, postanskiBroj, ime, email, adresa, telefon, jib);
               }
        } catch (SQLException ex) {
            Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return dobavljac;
    }
    
    public int getIdPoJibu(String jib){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id=0;
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.dobavljac where JIBDobavljaca like'"+jib+"%'");
            rs = ps.executeQuery();

            while (rs.next()) {
                id=rs.getInt("IdDobavljaca");
               }
        } catch (SQLException ex) {
            Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return id;
    }
    
    public ArrayList<DTODobavljacProizvod> getIdSvihProizvoda(int idDobavljaca){
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTODobavljacProizvod> lista=new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.dobavljac_proizvod where IdDobavljaca="+idDobavljaca);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id=rs.getInt("IdDobavljaca");
                int idProizvoda=rs.getInt("IdProizvoda");
                lista.add(new DTODobavljacProizvod(idDobavljaca, idProizvoda));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return lista;
        
    }
    
    public ArrayList<DTODobavljacIzvjestaj> proizvodiPoDobavljacuZaMjesec(Date datumOd,Date datumDo, int idDobavljaca){
        Connection con = null;
        CallableStatement myStatement=null;
        ArrayList<DTODobavljacIzvjestaj> proizvodi=new ArrayList<>();
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement=con.prepareCall("{call proizvodi_po_dobavljacu_za_mjesec(?, ?, ?)}");
            myStatement.setDate(1, new java.sql.Date(datumOd.getTime()));
            myStatement.setDate(2, new java.sql.Date(datumDo.getTime()));
            myStatement.setInt(3, idDobavljaca);
            rs=myStatement.executeQuery();
            while(rs.next()){
                String JIBDobavljaca=rs.getString(1);
                String nazivDobavaljaca=rs.getString(2);
                String sifraProizvoda=rs.getString(3);
                String barkodProizvoda=rs.getString(4);
                String nazivProizvoda=rs.getString(5);
                int kolicinaProizvoda=rs.getInt(6);
                String jedinicaMjere=rs.getString(7);
                double fakturnaCijena=rs.getDouble(8);
                int pdv=rs.getInt(9);
                double cijenaProizvoda=rs.getDouble(10);
                proizvodi.add(new DTODobavljacIzvjestaj(JIBDobavljaca, nazivDobavaljaca, sifraProizvoda, barkodProizvoda, nazivProizvoda, kolicinaProizvoda, jedinicaMjere, fakturnaCijena, pdv, cijenaProizvoda));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
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
        return proizvodi;
    }
    
    public ArrayList<DTODobavljacIzvjestaj> proizvodiPoDobavljacuZaGodinu(Date datumOd,Date datumDo, int idDobavljaca){
         Connection con = null;
        CallableStatement myStatement=null;
        ArrayList<DTODobavljacIzvjestaj> proizvodi=new ArrayList<>();
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement=con.prepareCall("{call proizvodi_po_dobavljacu_za_godinu(?, ?, ?)}");
            myStatement.setDate(1, new java.sql.Date(datumOd.getTime()));
            myStatement.setDate(2, new java.sql.Date(datumDo.getTime()));
            myStatement.setInt(3, idDobavljaca);
            rs=myStatement.executeQuery();
            while(rs.next()){
                String JIBDobavljaca=rs.getString(1);
                String nazivDobavaljaca=rs.getString(2);
                String sifraProizvoda=rs.getString(3);
                String barkodProizvoda=rs.getString(4);
                String nazivProizvoda=rs.getString(5);
                int kolicinaProizvoda=rs.getInt(6);
                String jedinicaMjere=rs.getString(7);
                double fakturnaCijena=rs.getDouble(8);
                int pdv=rs.getInt(9);
                double cijenaProizvoda=rs.getDouble(10);
                proizvodi.add(new DTODobavljacIzvjestaj(JIBDobavljaca, nazivDobavaljaca, sifraProizvoda, barkodProizvoda, nazivProizvoda, kolicinaProizvoda, jedinicaMjere, fakturnaCijena, pdv, cijenaProizvoda));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
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
        return proizvodi;
    }
    
    public String getJIBPoId(int id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String rezultat="";
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.dobavljac where IdDobavljaca like'"+id+"%'");
            rs = ps.executeQuery();

            while (rs.next()) {
                rezultat=rs.getString("JIBDobavljaca");
               }
        } catch (SQLException ex) {
            Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rezultat;
    }
    
     public String getNazivPoId(int id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String rezultat="";
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select * from baby_shop.dobavljac where IdDobavljaca like'"+id+"%'");
            rs = ps.executeQuery();

            while (rs.next()) {
                rezultat=rs.getString("Naziv");
               }
        } catch (SQLException ex) {
            Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rezultat;
    }
     
     public int IdDobavljacaOdIdProizvoda(int idProizvoda){
         Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rezultat=0;
        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("select IdDobavljaca from baby_shop.dobavljac where IdProizvoda like'"+idProizvoda+"%'");
            rs = ps.executeQuery();

            while (rs.next()) {
                rezultat=rs.getInt("IdDobavljaca");
               }
        } catch (SQLException ex) {
            Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rezultat;
     }
     
     public ArrayList<DTOIzvjestajNaruceneRobeDobavljac> proizvodiPoDobavljacuProdano(Date datumOd, Date datumDo, int idDobavljaca){
        Connection con = null;
        CallableStatement myStatement=null;
        ArrayList<DTOIzvjestajNaruceneRobeDobavljac> proizvodi=new ArrayList<>();
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement=con.prepareCall("{call proizvodi_po_dobavljacu_prodani(?, ?, ?)}");
            myStatement.setDate(1, new java.sql.Date(datumOd.getTime()));
            myStatement.setDate(2, new java.sql.Date(datumDo.getTime()));
            myStatement.setInt(3, idDobavljaca);
            rs=myStatement.executeQuery();
            while(rs.next()){
                String JIBDobavljaca=rs.getString(1);
                String nazivDobavaljaca=rs.getString(2);
                String sifraProizvoda=rs.getString(3);
                String barkodProizvoda=rs.getString(4);
                String nazivProizvoda=rs.getString(5);
                int kolicinaProizvoda=rs.getInt(6);
                double cijenaProizvoda=rs.getDouble(7);
                proizvodi.add(new DTOIzvjestajNaruceneRobeDobavljac(JIBDobavljaca, nazivDobavaljaca, sifraProizvoda, barkodProizvoda, nazivProizvoda, kolicinaProizvoda, cijenaProizvoda));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODobavljac.class.getName()).log(Level.SEVERE, null, ex);
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
        return proizvodi;
    }
}
