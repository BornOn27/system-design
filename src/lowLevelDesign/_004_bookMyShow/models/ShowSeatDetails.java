package lowLevelDesign._004_bookMyShow.models;

public class ShowSeatDetails {
    public Integer id;
    public Shows show;
    public Seats seat;
    public String status;

    public ShowSeatDetails(Integer id, Shows show, Seats seat, String status) {
        this.id = id;
        this.show = show;
        this.seat = seat;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShowSeatDetails{" +
                "show=" + show +
                ", seat=" + seat +
                ", status='" + status + '\'' +
                '}';
    }
}
