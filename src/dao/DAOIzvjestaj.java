package dao;

import connectionpool.ConnectionPool;
import dto.DTOPazarIzvjestaj;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOIzvjestaj {
    
    public ArrayList<DTOPazarIzvjestaj> pazariZaMjesec(Date datumOd,Date datumDo){
        Connection con = null;
        CallableStatement myStatement=null;
        ArrayList<DTOPazarIzvjestaj> pazari=new ArrayList<>();
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().checkOut();
            myStatement=con.prepareCall("{call pazar_dnevno(?, ?)}");
            myStatement.setDate(1, new java.sql.Date(datumOd.getTime()));
            myStatement.setDate(2, new java.sql.Date(datumDo.getTime()));
            rs=myStatement.executeQuery();
            while(rs.next()){
                int dan=rs.getInt(1);
                double ukupno=rs.getDouble(2);
                pazari.add(new DTOPazarIzvjestaj(dan, ukupno));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOIzvjestaj.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOIzvjestaj.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOIzvjestaj.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return pazari;
    }
    
}
