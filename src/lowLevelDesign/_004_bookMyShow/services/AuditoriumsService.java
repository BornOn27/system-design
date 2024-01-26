package lowLevelDesign._004_bookMyShow.services;

import lowLevelDesign._004_bookMyShow.models.Auditoriums;
import lowLevelDesign._004_bookMyShow.models.Theaters;

import java.util.ArrayList;
import java.util.List;

public class AuditoriumsService {
    SeatMappingsService seatMappingsService;
    List<Auditoriums> auditoriumsList = new ArrayList<>();

    public AuditoriumsService(SeatMappingsService seatMappingsService) {
        this.seatMappingsService = seatMappingsService;
    }

    public List<Auditoriums> createAuditoriums(Theaters theater, int count){
        List<Auditoriums> thisList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Auditoriums auditorium = new Auditoriums(auditoriumsList.size() + 1, theater, 100, "");
            thisList.add(auditorium);

            seatMappingsService.createSeatMapping(auditorium);
        }

        auditoriumsList.addAll(thisList);

        return thisList;
    }
}
