package lowLevelDesign._003_calender.services;

import lowLevelDesign._003_calender.models.EventDetails;
import lowLevelDesign._003_calender.models.Events;
import lowLevelDesign._003_calender.models.Frequency;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FrequencyService {
    List<Frequency> frequencyList = new ArrayList<>();

    public Frequency createFrequency(Date startDate, Date endDate, String frequency) {
        Frequency thisFrequency = new Frequency(frequencyList.size() + 1, startDate, endDate, frequency);
        frequencyList.add(thisFrequency);
        return thisFrequency;
    }

    public List<Date> getAllSchedulesForEventDetails(EventDetails thisDetail, Date calenderStartDate, Date calenderEndDate) {
        List<Date> schedules = new ArrayList<>();
        long diff = calenderEndDate.getTime() - calenderStartDate.getTime();

        switch (thisDetail.frequency.frequency.toUpperCase()){
            case "EVERY_DAY":
                long numberOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                schedules.add(thisDetail.frequency.startDate);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(thisDetail.frequency.startDate);

                for (int i = 1; i <= numberOfDays; i++) {
                    calendar.add(Calendar.DATE, i);
                    schedules.add(calendar.getTime());
                }
                schedules.add(thisDetail.frequency.endDate);
                break;
            case "EVERY_MONTH":
                break;
            case "EVERY_YEAR":
                break;
            default:
                System.out.println("ERROR ::: No Frequency Provided");
        }

        return schedules;
    }
}
