package lowLevelDesign._003_calender.services;

import lowLevelDesign._003_calender.models.EventDetails;
import lowLevelDesign._003_calender.models.Events;
import lowLevelDesign._003_calender.models.Frequency;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
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

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = formatter.format(calenderStartDate);
        String endDateString = formatter.format(calenderEndDate);

        schedules.add(calenderStartDate);

        switch (thisDetail.frequency.frequency.toUpperCase()){
            case "EVERY_DAY":
                long numberOfDays = ChronoUnit.DAYS.between(LocalDate.parse(startDateString),
                        LocalDate.parse(endDateString));

                Calendar everyDayCalender = null;
                for (int i = 1; i <= numberOfDays; i++) {
                    everyDayCalender = Calendar.getInstance();
                    everyDayCalender.add(Calendar.DATE, i);
                    schedules.add(everyDayCalender.getTime());
                }
                break;
            case "EVERY_MONTH":
                long numberOfMonths = ChronoUnit.MONTHS.between(LocalDate.parse(startDateString),
                        LocalDate.parse(endDateString));

                Calendar everyMonthCalender = null;
                for (int i = 1; i <= numberOfMonths; i++) {
                    everyMonthCalender = Calendar.getInstance();
                    everyMonthCalender.add(Calendar.MONTH, i);
                    schedules.add(everyMonthCalender.getTime());
                }

                break;
            case "EVERY_YEAR":
                long numberOfYears = ChronoUnit.YEARS.between(LocalDate.parse(startDateString),
                        LocalDate.parse(endDateString));

                Calendar everyYearCalender = null;
                for (int i = 1; i <= numberOfYears; i++) {
                    everyYearCalender = Calendar.getInstance();
                    everyYearCalender.add(Calendar.YEAR, i);
                    schedules.add(everyYearCalender.getTime());
                }

                break;
            default:
                System.out.println("ERROR ::: No Frequency Provided");
        }

        //End End-Date
        schedules.add(thisDetail.frequency.endDate);

        return schedules;
    }
}
