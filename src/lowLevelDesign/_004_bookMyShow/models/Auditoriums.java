package lowLevelDesign._004_bookMyShow.models;

import java.util.List;

public class Auditoriums {
    public Integer id;
    public Theaters theater;
    public Integer totalCapacity;
    public String layoutImage;

    public Auditoriums(Integer id, Theaters theater, Integer totalCapacity, String layoutImage) {
        this.id = id;
        this.theater = theater;
        this.totalCapacity = totalCapacity;
        this.layoutImage = layoutImage;
    }

    @Override
    public String toString() {
        return theater+"";
    }
}
