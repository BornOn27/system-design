package lowLevelDesign._004_bookMyShow.models;

import java.util.List;

public class SeatMappings {
    public Integer id;
    public Auditoriums auditorium;
    public List<Seats> seatsLists;

    public SeatMappings(Integer id, Auditoriums auditorium, List<Seats> seatsLists) {
        this.id = id;
        this.auditorium = auditorium;
        this.seatsLists = seatsLists;
    }

    @Override
    public String toString() {
        return "SeatMappings{" +
                "auditorium=" + auditorium +
                ", seatsLists=" + seatsLists +
                '}';
    }
}
