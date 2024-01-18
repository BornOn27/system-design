package lowLevelDesign._003_calender.services;

import lowLevelDesign._003_calender.models.EventDetails;
import lowLevelDesign._003_calender.models.Events;
import lowLevelDesign._003_calender.models.Frequency;
import lowLevelDesign._003_calender.models.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDetailsService {
    List<EventDetails> eventDetails = new ArrayList<>();

    public List<EventDetails> createEventsDetails(Events thisEvent, Frequency frequency, List<Users> guestList) {
        List<EventDetails> thisDetails = new ArrayList<>();
        for (Users user : guestList) {
            EventDetails eventDetail = new EventDetails(eventDetails.size() + 1, thisEvent,  user, frequency);
            thisDetails.add(eventDetail);

            eventDetails.add(eventDetail);
        }
        return thisDetails;
    }

    public List<EventDetails> getEventsDetailsForUserBetween(Users user, Date startDate, Date endDate) {
        List<EventDetails> userEventDetails = new ArrayList<>();
        for (EventDetails thisDetail: eventDetails) {
            if(thisDetail.invitedTo.equals(user) && endDate.compareTo(thisDetail.frequency.startDate) > 0){
                userEventDetails.add(thisDetail);
            }
        }
        return userEventDetails;
    }
}
