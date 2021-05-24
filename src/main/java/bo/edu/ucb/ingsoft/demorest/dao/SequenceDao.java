package bo.edu.ucb.ingsoft.demorest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class SequenceDao {
    @Autowired
    private DataSource dataSource;
    public int getPrimayKeyForTable(String nombreTable){
        String nombreSecuencia = nombreTable.toLowerCase() + "_id_" + nombreTable.toLowerCase() + "_seq";

        int result =0;
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("Select nextval (?)")){
            pstmt.setString(1, nombreSecuencia);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
