package lowLevelDesign._004_bookMyShow.services;

import lowLevelDesign._004_bookMyShow.models.City;
import lowLevelDesign._004_bookMyShow.models.Theaters;

import java.util.ArrayList;
import java.util.List;

public class TheatersService {


    List<Theaters> theatersList = new ArrayList<>();

    public Theaters createTheater(String name, City city, String address){
        return new Theaters(theatersList.size() + 1, name, city, address);
    }
}
