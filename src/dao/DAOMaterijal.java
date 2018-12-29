package dao;

import connectionpool.ConnectionPool;
import dto.DTOMaterijal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOMaterijal{
    
    public static String SQL_GET_MATERIJAL="select * from baby_shop.materijal";
   
    public ObservableList<DTOMaterijal> getMaterijal() {
        Connection con = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        ArrayList<DTOMaterijal> materijali = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_MATERIJAL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idMaterijala=rs.getInt(1);
                String nazivMaterijala=rs.getString(2);
                materijali.add(new DTOMaterijal(idMaterijala,nazivMaterijala));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOMaterijal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return FXCollections.observableArrayList(materijali);
    }
    
}
