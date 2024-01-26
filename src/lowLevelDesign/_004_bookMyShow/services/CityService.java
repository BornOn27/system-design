package lowLevelDesign._004_bookMyShow.services;

import lowLevelDesign._004_bookMyShow.models.City;

import java.util.ArrayList;
import java.util.List;

public class CityService {
    List<City> cityList = new ArrayList<>();


    public City createCity(String name){
        City city = new City(cityList.size() + 1, name);
        cityList.add(city);
        return city;
    }
}
