package lowLevelDesign._005_twitter.services;

import lowLevelDesign._005_twitter.models.Tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagsService {
    Map<String, Tags> tagsStore = new HashMap<>();

    public List<Tags> getOrCreateTags(List<String> tags){
        List<Tags> thisList = new ArrayList<>();
        for (String tag : tags) {
            if(!tagsStore.containsKey(tag)){
                Tags tagInstance = new Tags(tagsStore.size() + 1, tag);
                tagsStore.put(tag, tagInstance);
            }

            thisList.add(tagsStore.get(tag));
        }
        return thisList;
    }
}
