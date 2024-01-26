package lowLevelDesign._004_bookMyShow.models;

public class Users {
    public Integer id;
    public String name;
    public String email;

    public Users(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users=" + name;
    }
}
