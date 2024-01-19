package lowLevelDesign._003_calender;

import lowLevelDesign._003_calender.models.EventDetails;
import lowLevelDesign._003_calender.models.Users;
import lowLevelDesign._003_calender.services.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Calender {
    FrequencyService frequencyService = new FrequencyService();
    EventDetailsService eventDetailsService = new EventDetailsService();
    UsersService usersService = new UsersService();
    EventsModelService eventsModelService = new EventsModelService(frequencyService);
    EventsService eventsService = new EventsService(frequencyService, eventDetailsService, eventsModelService);
    EventManagerService eventManagerService = new EventManagerService(eventsService, eventDetailsService);

    public static void main(String[] args) throws ParseException {
        Calender calender = new Calender();
        calender.run();
    }

    public void run() throws ParseException {
        Users manoj = usersService.createUser("MJ", "mj@l.com");
        Users ashish = usersService.createUser("APS", "aps@z.com");
        Users rahul = usersService.createUser("RA", "rs@l.com");
        Users ravi = usersService.createUser("RV", "ra@s.com");

        List<String> frequencies = Arrays.asList("EVERY_DAY", "EVERY_MONTH", "EVERY_YEAR");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        String letsMeetEndDateInString = "1-02-2024";
        Date letsMeetEndDate = formatter.parse(letsMeetEndDateInString);

        eventsService.createEvent("Let's Meet !!!", "Come on !!!", new Date(), manoj, new Date(),
                new Date(), false, new Date(), letsMeetEndDate, frequencies.get(0),
                Arrays.asList(manoj, ashish, rahul, ravi), false);

        String letsGoEndDateInString = "01-2-2025";
        Date letsGoEndDate = formatter.parse(letsGoEndDateInString);
        eventsService.createEvent("Let's Go !!!", "Come on !!!", new Date(), manoj, new Date(),
                new Date(), false, new Date(), letsGoEndDate, frequencies.get(1),
                Arrays.asList(manoj, ashish, rahul), true);

        String letsMeetAgainEndDateInString = "01-2-2035";
        Date letsMeetAgainEndDate = formatter.parse(letsMeetAgainEndDateInString);
        eventsService.createEvent("Let's Meet Again !!!", "Come on !!!", new Date(), manoj, new Date(),
                new Date(), false, new Date(), letsMeetAgainEndDate, frequencies.get(2),
                Arrays.asList(manoj, ashish, ravi), false);


        String letsMeetInSecretEndDateInString = "01-5-2024";
        Date letsMeetInSecretEndDate = formatter.parse(letsMeetInSecretEndDateInString);
        eventsService.createEvent("Let's Meet In Secret !!!", "Come on !!!", new Date(), manoj, new Date(),
                new Date(), false, new Date(), letsMeetInSecretEndDate, frequencies.get(1),
                Arrays.asList(manoj, ashish), true);


        eventManagerService.getEventsForUsersForDuration(manoj, new Date(), letsMeetInSecretEndDate);
        eventManagerService.getEventsForUsersForDuration(ashish, new Date(), letsMeetInSecretEndDate);
        eventManagerService.getEventsForUsersForDuration(rahul, new Date(), letsMeetInSecretEndDate);
        eventManagerService.getEventsForUsersForDuration(ravi, new Date(), letsMeetInSecretEndDate);



    }

}
