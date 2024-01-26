package lowLevelDesign._004_bookMyShow.models;

public class Movies {
    public Integer id;
    public String name;
    public MetaData metaData;

    public Movies(Integer id, String name, MetaData metaData) {
        this.id = id;
        this.name = name;
        this.metaData = metaData;
    }

    @Override
    public String toString() {
        return name;
    }
}
