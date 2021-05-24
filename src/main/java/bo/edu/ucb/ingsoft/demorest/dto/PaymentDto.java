package bo.edu.ucb.ingsoft.demorest.dto;

public class PaymentDto {
    private Integer id_payment;
    private String payment_name;
    private String cost;


    public PaymentDto(){
    }

    public Integer getId_payment() {
        return id_payment;
    }

    public void setId_payment(Integer id_payment) {
        this.id_payment = id_payment;
    }


    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }


}
