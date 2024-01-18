package lowLevelDesign._003_calender.models;

import java.util.Date;

public class EventsModel {
    public Integer emId;
    public String title;
    public String description;
    public String meetingLink;
    public String type;
    public Users createdBy;
    public Date startTime;
    public Date endTime;
    public boolean allDay;
    public boolean isRepeating;
    public Integer sequenceNumber;
    public boolean isParentEvent;
    public Integer parentEventId;
    public Date scheduledAt;

    private EventsModel() {
    }

    public EventsModel(EventDetails eventDetails) {
        this.emId = eventDetails.event.getEId();
        this.title = eventDetails.event.getTitle();
        this.description = eventDetails.event.getDescription();
        this.meetingLink = eventDetails.event.getMeetingLink();
        this.type = eventDetails.event.getType();
        this.createdBy = eventDetails.event.getCreatedBy();
        this.startTime = eventDetails.event.getStartTime();
        this.endTime = eventDetails.event.getEndTime();
        this.allDay = eventDetails.event.isAllDay();
        this.isRepeating = eventDetails.event.isRepeating();
        this.isParentEvent = true;
        this.scheduledAt = startTime;
    }

    public EventsModel(EventDetails eventDetails, int sequenceNumber, Date scheduledAt) {
        this.emId = eventDetails.event.getEId();
        this.title = eventDetails.event.getTitle();
        this.description = eventDetails.event.getDescription();
        this.meetingLink = eventDetails.event.getMeetingLink();
        this.type = eventDetails.event.getType();
        this.createdBy = eventDetails.event.getCreatedBy();
        this.startTime = eventDetails.event.getStartTime();
        this.endTime = eventDetails.event.getEndTime();
        this.allDay = eventDetails.event.isAllDay();
        this.isRepeating = eventDetails.event.isRepeating();
        this.parentEventId = eventDetails.event.getEId();
        this.isParentEvent = false;
        this.sequenceNumber = sequenceNumber;
        this.scheduledAt = scheduledAt;
    }
}
