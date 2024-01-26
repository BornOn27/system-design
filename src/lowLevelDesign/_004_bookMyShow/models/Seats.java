package lowLevelDesign._004_bookMyShow.models;


public class Seats {
    public Integer id;
    public SeatTypes type;
    public Integer surcharge;
    public String seatNumber;

    public Seats(Integer id, SeatTypes type, Integer surcharge, String seatNumber) {
        this.id = id;
        this.type = type;
        this.surcharge = surcharge;
        this.seatNumber = seatNumber;
    }

    public Seats(SeatTypes type, String seatNumber) {
        this.type = type;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "type=" + type +
                ", seatNumber='" + seatNumber;
    }
}
