package lowLevelDesign._003_calender.services;

import lowLevelDesign._003_calender.models.EventDetails;
import lowLevelDesign._003_calender.models.Events;
import lowLevelDesign._003_calender.models.EventsModel;
import lowLevelDesign._003_calender.models.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventManagerService {
    EventsService eventsService;
    EventDetailsService eventDetailsService;

    public EventManagerService(EventsService eventsService, EventDetailsService eventDetailsService) {
        this.eventsService = eventsService;
        this.eventDetailsService = eventDetailsService;
    }

    public List<EventsModel> getEventsForUsersForDuration(Users user, Date startDate, Date endDate){
        List<EventsModel> userEventsInDuration = eventsService.getUserEventsBetween(user, startDate, endDate);
        return userEventsInDuration;
    }
}
