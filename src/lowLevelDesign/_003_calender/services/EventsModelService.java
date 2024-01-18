package lowLevelDesign._003_calender.services;

import lowLevelDesign._003_calender.models.EventDetails;
import lowLevelDesign._003_calender.models.EventsModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsModelService {
    FrequencyService frequencyService;

    public EventsModelService(FrequencyService frequencyService) {
        this.frequencyService = frequencyService;
    }

    public List<EventsModel> getEventDetails(List<EventDetails> eventDetailsList, Date calenderStartDate, Date calenderEndDate){
        List<EventsModel> modelList = new ArrayList<>();

        /** TODO Find whether Events are recurring, and generate the instance for required duration
         *
         */
        for (EventDetails thisDetail : eventDetailsList) {
            if(thisDetail.event.isRepeating()){
                //Generate Frequency and all
               modelList.addAll(generateEventsAccordingToFrequency(thisDetail, calenderStartDate, calenderEndDate));
            } else {
                EventsModel model = new EventsModel(thisDetail);
                modelList.add(model);
            }
        }

        return modelList;
    }

    private List<EventsModel> generateEventsAccordingToFrequency(EventDetails thisDetail,  Date calenderStartDate, Date calenderEndDate) {
        List<Date> allSchedules = frequencyService.getAllSchedulesForEventDetails(thisDetail, calenderStartDate, calenderEndDate);
        List<EventsModel> eventsModels = new ArrayList<>();

        int sequenceNumber = 0;
        for (Date thisSchedule : allSchedules) {
            sequenceNumber++;
            EventsModel thisModel = new EventsModel(thisDetail, sequenceNumber, thisSchedule);
            eventsModels.add(thisModel);
        }

        return eventsModels;
    }


}
