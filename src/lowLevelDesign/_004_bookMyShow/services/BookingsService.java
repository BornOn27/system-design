package lowLevelDesign._004_bookMyShow.services;

import lowLevelDesign._004_bookMyShow.models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingsService {
    SeatMappingsService seatMappingsService;
    ShowSeatDetailsService showSeatDetailsService;

    List<Bookings> bookings = new ArrayList<>();

    public BookingsService(SeatMappingsService seatMappingsService, ShowSeatDetailsService showSeatDetailsService) {
        this.seatMappingsService = seatMappingsService;
        this.showSeatDetailsService = showSeatDetailsService;
    }

    public Bookings createBooking(Users bookedBy, Shows show, List<Seats> seats) throws Exception {

        if(showSeatDetailsService.isSeatsAvailableForBooking(show, seats)){
            List<ShowSeatDetails> showSeatDetails = showSeatDetailsService.bookSeats(show, seats);
            Bookings booking = new Bookings(bookings.size() + 1, bookedBy, new Date(), showSeatDetails, new Payments(), "BOOKED");
            bookings.add(booking);

            return booking;
        }

        throw new Exception("Seat is already booked :: "+bookedBy+ "\n for "+show+ "\n seats : "+seats);
    }
}
