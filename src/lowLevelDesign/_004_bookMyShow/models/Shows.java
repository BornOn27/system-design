package lowLevelDesign._004_bookMyShow.models;

import java.util.Date;

public class Shows {
    public Integer id;
    public Movies movie;
    public Auditoriums auditorium;
    public Date startsFrom;

    public Shows(Integer id, Movies movie, Auditoriums auditorium, Date startsFrom) {
        this.id = id;
        this.movie = movie;
        this.auditorium = auditorium;
        this.startsFrom = startsFrom;
    }

    @Override
    public String toString() {
        return "Shows{" +
                "movie=" + movie +
                ", auditorium=" + auditorium +
                '}';
    }
}
