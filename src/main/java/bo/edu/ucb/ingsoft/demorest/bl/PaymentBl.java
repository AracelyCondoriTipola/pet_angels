package bo.edu.ucb.ingsoft.demorest.bl;

import bo.edu.ucb.ingsoft.demorest.dao.PaymentDao;
import bo.edu.ucb.ingsoft.demorest.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentBl {
    @Autowired
    PaymentDao paymentDao;

    public PaymentDto crearPayment(PaymentDto payment){

        return paymentDao.crearPayment(payment);
    }

    public PaymentDto findPaymentById(Integer id_payment) {
        return paymentDao.findPaymentById(id_payment);
    }

    public List<PaymentDto> findAllPayment() {
        return paymentDao.findAllPayment();
    }

}
