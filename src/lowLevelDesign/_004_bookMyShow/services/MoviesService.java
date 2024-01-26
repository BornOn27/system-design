package lowLevelDesign._004_bookMyShow.services;

import lowLevelDesign._004_bookMyShow.models.MetaData;
import lowLevelDesign._004_bookMyShow.models.Movies;

import java.util.ArrayList;
import java.util.List;

public class MoviesService {
    List<Movies> movies = new ArrayList<>();


    public Movies createMovie(String name, Integer basePrice, Integer durationInMinutes){
        MetaData metaData = createMetaData(basePrice, 0, 135);
        Movies movie = new Movies(movies.size() + 1, name, metaData);
        movies.add(movie);

        return movie;
    }

    private MetaData createMetaData(Integer basePrice, Integer initialDiscount, Integer durationInMinutes){
        return new MetaData(basePrice, initialDiscount, durationInMinutes);
    }
}
