package lowLevelDesign._004_bookMyShow.services;

import lowLevelDesign._004_bookMyShow.models.SeatMappings;
import lowLevelDesign._004_bookMyShow.models.Seats;
import lowLevelDesign._004_bookMyShow.models.ShowSeatDetails;
import lowLevelDesign._004_bookMyShow.models.Shows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowSeatDetailsService {
    List<ShowSeatDetails> showSeatDetails = new ArrayList<>();

    public List<ShowSeatDetails> bookSeats(Shows show, List<Seats> seats){
        List<ShowSeatDetails> thisDetails = new ArrayList<>();
        for (Seats seat : seats){
            ShowSeatDetails thisDetail = new ShowSeatDetails(showSeatDetails.size() + 1, show, seat, "BOOKED");
            thisDetails.add(thisDetail);
        }

        showSeatDetails.addAll(thisDetails);
        return thisDetails;
    }

    public boolean isSeatsAvailableForBooking(Shows show, List<Seats> seats){
        for (ShowSeatDetails thisSeatDetail: showSeatDetails) {
            for(Seats thisSeat : seats){
                if(thisSeatDetail.show.equals(show) && thisSeatDetail.seat.equals(thisSeat)){
                    return false;
                }
            }
        }

        return true;
    }

    public List<ShowSeatDetails> getAllBookedSeatsForShow(Shows show){
        List<ShowSeatDetails> bookedSeats = new ArrayList<>();
        for (ShowSeatDetails thisDetail : showSeatDetails) {
            if (show.equals(thisDetail.show)) {
                bookedSeats.add(thisDetail);
            }
        }
        return bookedSeats;
    }

}
