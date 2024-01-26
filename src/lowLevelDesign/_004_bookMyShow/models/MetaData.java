package lowLevelDesign._004_bookMyShow.models;

public class MetaData {
    public Integer basePrice;
    public Integer discount;
    public Integer durationInMinutes;

    public MetaData(Integer basePrice, Integer discount, Integer durationInMinutes) {
        this.basePrice = basePrice;
        this.discount = discount;
        this.durationInMinutes = durationInMinutes;
    }
}
