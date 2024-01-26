package lowLevelDesign._004_bookMyShow.services;

import lowLevelDesign._004_bookMyShow.models.Auditoriums;
import lowLevelDesign._004_bookMyShow.models.Movies;
import lowLevelDesign._004_bookMyShow.models.Shows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowsService {
    List<Shows> shows = new ArrayList<>();

    public List<Shows> createShows(Movies movie, List<Auditoriums> auditoriums, Date showOn){
        List<Shows> thisShows = new ArrayList<>();

        for (Auditoriums audi : auditoriums) {
            Shows show = new Shows(shows.size() + 1, movie, audi, showOn);
            thisShows.add(show);
        }

        shows.addAll(thisShows);

        return thisShows;
    }

}
