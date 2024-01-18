package lowLevelDesign._003_calender.models;

import java.util.Date;
import java.util.Random;

public class Events {
    private Integer eId;
    private String title;
    private String description;
    private String meetingLink;
    private String type;
    private Date createdOn;
    private Users createdBy;
    private Date startTime;
    private Date endTime;
    private boolean allDay;
    private boolean isRepeating;

    private Events(String title, Date createdOn, Users createdBy, Date startTime, Date endTime, boolean isRepeating) {
        this.eId = new Random().nextInt();
        this.title = title;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isRepeating = isRepeating;
    }

    public Integer getEId() {
        return eId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public String getType() {
        return type;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public boolean isRepeating() {
        return isRepeating;
    }

    //Builder Pattern
    public static class Builder{
        private Events event;

        //All Mandatory Fields should be part of this, rest will be created using Builder Pattern
        public Builder(String title, Date createdOn, Users createdBy, Date startTime, Date endTime, boolean isRepeating) {
            this.event = new Events(title, createdOn, createdBy, startTime, endTime, isRepeating);
        }

        public Builder withDescription(String description){
            event.description = description;
            return this;
        }

        public Builder withMeetingLink(String meetingLink){
            event.meetingLink = meetingLink;
            return this;
        }

        public Builder withAllDay(boolean allDay){
            event.allDay = allDay;
            return this;
        }

        public Events build(){
            return event;
        }

    }
}
