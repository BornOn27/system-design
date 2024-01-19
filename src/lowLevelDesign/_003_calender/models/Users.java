package lowLevelDesign._003_calender.models;

public class Users {
    public Integer uId;
    public String name;
    public String email;

    public Users(Integer uId, String name, String email) {
        this.uId = uId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return name;
    }
}
