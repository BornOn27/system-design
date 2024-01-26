package lowLevelDesign._004_bookMyShow.models;

public class City {
    public Integer id;
    public String name;

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
