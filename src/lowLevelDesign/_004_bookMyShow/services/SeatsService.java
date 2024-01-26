package lowLevelDesign._004_bookMyShow.services;

import lowLevelDesign._004_bookMyShow.models.SeatTypes;
import lowLevelDesign._004_bookMyShow.models.Seats;

import java.util.ArrayList;
import java.util.List;

public class SeatsService {


    public List<Seats> createSeats(int count){
        List<Seats> seatsList = new ArrayList<>();

        seatsList.addAll(createSeats((int)(count * 0.2), SeatTypes.RECLINER));
        seatsList.addAll(createSeats((int)(count * 0.5), SeatTypes.GOLD));
        seatsList.addAll(createSeats((int)(count * 0.3), SeatTypes.REGULAR));

        return seatsList;
    }

    private List<Seats> createSeats(int count, SeatTypes seatType){
        List<Seats> seatsList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Seats seat = new Seats(i+1, seatType, 20, seatType.getShortCode()+(i+1));
            seatsList.add(seat);
        }
        return seatsList;
    }
}
