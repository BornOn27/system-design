package lowLevelDesign._005_twitter.models;

public enum Reactions {
    LIKE(0, "like"), DIS_LIKE(1, "dislike"), HEART(2, "heart"), ANGRY(3, "angry");

    private Integer id;
    private String imageUrl;

    Reactions(Integer id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
