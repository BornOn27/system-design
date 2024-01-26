package lowLevelDesign._004_bookMyShow.models;

import java.util.Date;

public class Payments {
    enum PaymentModes{
        UPI, CASH, CARDS
    }

    public Integer id;
    public PaymentModes mode;
    public Integer amount;
    public Date madeOn;
    public String details;
    public String status;
}
