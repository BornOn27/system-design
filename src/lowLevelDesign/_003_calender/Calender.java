package lowLevelDesign._003_calender;

import lowLevelDesign._003_calender.models.EventDetails;
import lowLevelDesign._003_calender.models.Users;
import lowLevelDesign._003_calender.services.*;

import java.util.Arrays;
import java.util.Date;

public class Calender {
    FrequencyService frequencyService = new FrequencyService();
    EventDetailsService eventDetailsService = new EventDetailsService();
    UsersService usersService = new UsersService();
    EventsModelService eventsModelService = new EventsModelService();
    EventsService eventsService = new EventsService(frequencyService, eventDetailsService, eventsModelService);
    EventManagerService eventManagerService = new EventManagerService(eventsService, eventDetailsService);

    public static void main(String[] args) {
        Calender calender = new Calender();
        calender.run();
    }

    public void run(){
        Users manoj = usersService.createUser("MJ", "mj@l.com");
        Users ashish = usersService.createUser("APS", "aps@z.com");

        Users rahul = usersService.createUser("RA", "rs@l.com");
        Users ravi = usersService.createUser("RV", "ra@s.com");


        eventsService.createEvent("Let's Meet !!!", "Come on !!!", new Date(), manoj, new Date(),
                new Date(), false, new Date(), new Date(), "",
                Arrays.asList(manoj, ashish, rahul, ravi), false);

        eventsService.createEvent("Let's Go !!!", "Come on !!!", new Date(), manoj, new Date(),
                new Date(), false, new Date(), new Date(), "",
                Arrays.asList(manoj, ashish, rahul, ravi), true);

        eventsService.createEvent("Let's Meet Again !!!", "Come on !!!", new Date(), manoj, new Date(),
                new Date(), false, new Date(), new Date(), "",
                Arrays.asList(manoj, ashish, rahul, ravi), false);



        eventManagerService.getEventsForUsersForDuration(manoj, new Date(), new Date());
        eventManagerService.getEventsForUsersForDuration(ashish, new Date(), new Date());
        eventManagerService.getEventsForUsersForDuration(rahul, new Date(), new Date());
        eventManagerService.getEventsForUsersForDuration(ravi, new Date(), new Date());
    }

}
