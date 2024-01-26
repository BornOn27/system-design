package lowLevelDesign._004_bookMyShow.models;

public enum SeatTypes {
    REGULAR("RG-"), GOLD("G-"), RECLINER("R-");

    private String shortCode;
    private Integer surcharge;

    SeatTypes(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getShortCode() {
        return shortCode;
    }
}
