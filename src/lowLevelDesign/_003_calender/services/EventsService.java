package lowLevelDesign._003_calender.services;

import lowLevelDesign._003_calender.models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsService {
    List<Events> eventsList = new ArrayList<>();
    FrequencyService frequencyService;
    EventDetailsService eventDetailsService;
    EventsModelService eventsModelService;

    public EventsService(FrequencyService frequencyService, EventDetailsService eventDetailsService, EventsModelService eventsModelService) {
        this.frequencyService = frequencyService;
        this.eventDetailsService = eventDetailsService;
        this.eventsModelService = eventsModelService;
    }

    public Events createEvent(String title, String description, Date createdOn, Users createdBy,
                              Date startTime, Date endTime, boolean allDay, Date startDate, Date endDate,
                              String frequency, List<Users> guestList, boolean isRepeating) {

        Events.Builder builder = new Events.Builder(title, createdOn, createdBy, startTime, endTime, isRepeating).withAllDay(allDay);
        if(!description.isEmpty())
            builder = builder.withDescription(description);

        String meetingLink = generateMeetingLink();
        builder.withMeetingLink(meetingLink);


        Events thisEvent = builder.build();
        Frequency eventFrequency = frequencyService.createFrequency(startDate, endDate, frequency);
        eventDetailsService.createEventsDetails(thisEvent, eventFrequency, guestList);

        eventsList.add(thisEvent);
        return thisEvent;
    }

    private String generateMeetingLink() {
        return "https://www.meetings.com/link/ads4-5as-54d";
    }

    public List<EventsModel> getUserEventsBetween(Users user, Date calenderStartDate, Date calenderEndDate) {
        List<EventDetails> eventDetailsList = eventDetailsService.getEventsDetailsForUserBetween(user, calenderStartDate, calenderEndDate);
        return eventsModelService.getEventDetails(eventDetailsList, calenderStartDate, calenderEndDate);
    }
}
