package lowLevelDesign._003_calender.models;

public class EventDetails {
    public Integer edId;
    public Events event;
    public Users invitedTo;
    public Frequency frequency;

    public EventDetails(Integer edId, Events event, Users invitedTo, Frequency frequency) {
        this.edId = edId;
        this.event = event;
        this.invitedTo = invitedTo;
        this.frequency = frequency;
    }
}
