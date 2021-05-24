package bo.edu.ucb.ingsoft.demorest.dao;

import bo.edu.ucb.ingsoft.demorest.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SequenceDao sequenceDao;

    public PaymentDto crearPayment (PaymentDto payment) {
        payment.setId_payment(sequenceDao.getPrimayKeyForTable("payment"));
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO payment VALUES (?,?,?)");
            stmt.setInt(1, payment.getId_payment());
            stmt.setString(2, payment.getPayment_name());
            stmt.setString(3, payment.getCost());
            stmt.executeUpdate();
            stmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();


        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqex) {
                    // No hacer nada intencionalemte;
                }
            }
        }

        return payment;
    }

    public PaymentDto findPaymentById(Integer id_payment) {
        PaymentDto result = new PaymentDto();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT id_payment,payment_name,cost " +
                     "FROM payment WHERE id_payment = ? ")
        ) {  // TRY WITH RESOURCES
            pstmt.setInt(1, id_payment);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.setId_payment(rs.getInt("id_payment"));
                result.setPayment_name(rs.getString("payment_name"));
                result.setCost(String.valueOf(rs.getBigDecimal("cost")));
            } else { // si no hay valores de BBDD
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<PaymentDto> findAllPayment() {
        List<PaymentDto> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT id_payment, payment_name,cost FROM payment");
            while (rs.next()) {
                PaymentDto payment = new PaymentDto();
                payment.setId_payment(rs.getInt("id_payment"));
                payment.setPayment_name(rs.getString("payment_name"));
                payment.setCost(String.valueOf(rs.getBigDecimal("cost")));
                result.add(payment);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}
