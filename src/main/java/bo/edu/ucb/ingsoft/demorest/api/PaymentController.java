package bo.edu.ucb.ingsoft.demorest.api;

import bo.edu.ucb.ingsoft.demorest.bl.PaymentBl;
import bo.edu.ucb.ingsoft.demorest.dto.PaymentDto;
import bo.edu.ucb.ingsoft.demorest.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
public class PaymentController {
    @Autowired
    public DataSource dataSource;
    @Autowired
    private PaymentBl paymentBl;

    @GetMapping(path = "/payment/{id_payment}")
    public ResponseDto findPaymentById(@PathVariable Integer id_payment) {
        PaymentDto payment = paymentBl.findPaymentById(id_payment);
        if (payment != null) {
            return new ResponseDto( true, payment, null);
        } else {
            return new ResponseDto( false, null, "No existe el pago con este codigo:");
        }
    }
    @GetMapping(path = "/payment")
    public ResponseDto findAllPayment() {
        return new ResponseDto( true, paymentBl.findAllPayment(), null);
    }

    @PostMapping(path = "/payment")
    public PaymentDto crearPayment(@RequestBody PaymentDto payment) {

        return paymentBl.crearPayment(payment);
    }



}
