package lowLevelDesign._004_bookMyShow.models;

public class Theaters {
    public Integer id;
    public String name;
    public City city;
    public String address;

    public Theaters(Integer id, String name, City city, String address) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Theaters{" + name +
                "-" + city +
                '}';
    }
}
