package lowLevelDesign._004_bookMyShow.services;

import lowLevelDesign._004_bookMyShow.models.Auditoriums;
import lowLevelDesign._004_bookMyShow.models.SeatMappings;
import lowLevelDesign._004_bookMyShow.models.Seats;
import lowLevelDesign._004_bookMyShow.models.Shows;

import java.util.ArrayList;
import java.util.List;

public class SeatMappingsService {
    SeatsService seatsService;
    List<SeatMappings> seatMappingsList = new ArrayList<>();

    public SeatMappingsService(SeatsService seatsService) {
        this.seatsService = seatsService;
    }

    public void createSeatMapping(Auditoriums auditorium){
        List<Seats> seatsList = seatsService.createSeats(auditorium.totalCapacity);
        SeatMappings seatMapping = new SeatMappings(seatMappingsList.size() + 1, auditorium, seatsList);

        seatMappingsList.add(seatMapping);
    }

    public List<SeatMappings> getAllSeatsForShow(Shows show) {
        List<SeatMappings> thisShowSeatMappings = new ArrayList<>();
        for (SeatMappings thisMapping : seatMappingsList) {
            if(thisMapping.auditorium.equals(show.auditorium)){
                thisShowSeatMappings.add(thisMapping);
            }
        }
        return thisShowSeatMappings;
    }
}
