package lowLevelDesign._004_bookMyShow;


import lowLevelDesign._004_bookMyShow.models.*;
import lowLevelDesign._004_bookMyShow.services.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BookMyShow {
    UsersService usersService = new UsersService();
    CityService cityService = new CityService();
    MoviesService moviesService = new MoviesService();
    TheatersService theatersService = new TheatersService();
    SeatsService seatsService = new SeatsService();
    SeatMappingsService seatMappingsService = new SeatMappingsService(seatsService);
    AuditoriumsService auditoriumsService = new AuditoriumsService(seatMappingsService);
    ShowsService showsService = new ShowsService();
    ShowSeatDetailsService showSeatDetailsService = new ShowSeatDetailsService();
    BookingsService bookingsService = new BookingsService(seatMappingsService, showSeatDetailsService);

    public static void main(String[] args) {
        new BookMyShow().run();
    }

    public void run(){
        Users manoj = usersService.createUser("MJ", "mj@l.com");
        Users ashish = usersService.createUser("APS", "aps@z.com");
        Users rahul = usersService.createUser("RA", "rs@l.com");
        Users ravi = usersService.createUser("RV", "ra@s.com");


        City bangalore = cityService.createCity("Bangalore");
        City varanasi = cityService.createCity("Varanasi");

        Movies atal = moviesService.createMovie("Atal", 250, 120);
        Movies fighter = moviesService.createMovie("Fighter", 300, 135);
        Movies hanuman = moviesService.createMovie("Hanuman", 250, 125);


        Theaters inox = theatersService.createTheater("Inox", bangalore, "Bellandur");
        Theaters pvr = theatersService.createTheater("PVR", bangalore, "Sarjapur");
        Theaters ipVijaya = theatersService.createTheater("IP Vijaya", varanasi, "Bhelupur");
        Theaters jhvCinema = theatersService.createTheater("JHV Cinema", varanasi, "Cantt");

        List<Auditoriums> inoxAuditoriums = auditoriumsService.createAuditoriums(inox, 6);
        List<Auditoriums> pvrAuditoriums = auditoriumsService.createAuditoriums(pvr, 6);
        List<Auditoriums> ipVijayaAuditoriums = auditoriumsService.createAuditoriums(ipVijaya, 4);
        List<Auditoriums> jhvAuditoriums = auditoriumsService.createAuditoriums(jhvCinema, 5);

        List<Shows> atalShows = showsService.createShows(atal,
                Arrays.asList(inoxAuditoriums.get(0), inoxAuditoriums.get(1), inoxAuditoriums.get(2),
                                pvrAuditoriums.get(0), pvrAuditoriums.get(1), pvrAuditoriums.get(2),
                                ipVijayaAuditoriums.get(0), ipVijayaAuditoriums.get(1),
                                jhvAuditoriums.get(0), jhvAuditoriums.get(1)), new Date());

        List<Shows> fighterShows = showsService.createShows(fighter,
                Arrays.asList(inoxAuditoriums.get(3), inoxAuditoriums.get(4),
                        pvrAuditoriums.get(3), pvrAuditoriums.get(4), ipVijayaAuditoriums.get(2),
                        jhvAuditoriums.get(2), jhvAuditoriums.get(3)), new Date());

        List<Shows> hanumanShows = showsService.createShows(hanuman,
                Arrays.asList(inoxAuditoriums.get(5), pvrAuditoriums.get(5),
                        ipVijayaAuditoriums.get(3), jhvAuditoriums.get(4
                        )), new Date());


        List<Seats> seatDetails = new ArrayList<>();
        seatDetails.add(new Seats(SeatTypes.RECLINER, SeatTypes.RECLINER.getShortCode()+"1"));
        seatDetails.add(new Seats(SeatTypes.RECLINER, SeatTypes.RECLINER.getShortCode()+"2"));

        try{
            Bookings atalBookingByManojInInox = bookingsService.createBooking(manoj, atalShows.get(0), seatDetails);

            Bookings atalBookingByAshishInInox = bookingsService.createBooking(ashish, atalShows.get(0), seatDetails);
        }
        catch (Exception e){
            System.out.println("ERROR :: "+e.getMessage());
        }

        System.out.println("All Available Seats :: "+atalShows.get(0).auditorium);
//        for (SeatMappings seatMapping: seatMappingsService.getAllSeatsForShow(atalShows.get(0))) {
//            for (Seats seat: seatMapping.seatsLists) {
//                System.out.println(seat);
//            }
//        }
        System.out.println();
        System.out.println("All Booked Seats :: "+showSeatDetailsService.getAllBookedSeatsForShow(atalShows.get(0)));

    }
}
