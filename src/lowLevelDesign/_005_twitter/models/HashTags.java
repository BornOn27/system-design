package lowLevelDesign._005_twitter.models;

import java.util.List;

public class HashTags {
    public Integer id;
    public Tweets tweet;
    public List<Tags> tags;

    public HashTags(Integer id, Tweets tweet, List<Tags> tags) {
        this.id = id;
        this.tweet = tweet;
        this.tags = tags;
    }
}
