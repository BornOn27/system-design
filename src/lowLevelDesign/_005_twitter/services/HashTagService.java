package lowLevelDesign._005_twitter.services;

import lowLevelDesign._005_twitter.models.HashTags;
import lowLevelDesign._005_twitter.models.Tags;
import lowLevelDesign._005_twitter.models.Tweets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashTagService {
    TagsService tagsService;

    Map<Tweets, HashTags> hashTagsMap = new HashMap<>();



    public HashTags createHashTagsForTweet(Tweets tweet, List<String> tags){
        List<Tags> tagsForTweet = tagsService.getOrCreateTags(tags);
        HashTags newHashTag = new HashTags(hashTagsMap.size()+1, tweet, tagsForTweet);
        hashTagsMap.put(tweet, newHashTag);
        return newHashTag;
    }

    public Map<Tweets, HashTags> getHashTagsForTweets(List<Tweets> tweets){
        Map<Tweets, HashTags> tweetsHashTagsMap = new HashMap<>();

        for (Tweets tweet : tweets) {
            if(hashTagsMap.containsKey(tweet)){
                tweetsHashTagsMap.put(tweet, hashTagsMap.get(tweet));
            }
        }

        return tweetsHashTagsMap;
    }

}
