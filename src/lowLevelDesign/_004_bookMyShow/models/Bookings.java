package lowLevelDesign._004_bookMyShow.models;

import java.util.Date;
import java.util.List;

public class Bookings {
    public Integer id;
    public Users bookedBy;
    public Date bookingDate;
    public List<ShowSeatDetails> showSeatDetail;
    public Payments payment;
    public String bookingStatus;

    public Bookings(Integer id, Users bookedBy, Date bookingDate, List<ShowSeatDetails> showSeatDetail, Payments payment, String bookingStatus) {
        this.id = id;
        this.bookedBy = bookedBy;
        this.bookingDate = bookingDate;
        this.showSeatDetail = showSeatDetail;
        this.payment = payment;
        this.bookingStatus = bookingStatus;
    }
}
