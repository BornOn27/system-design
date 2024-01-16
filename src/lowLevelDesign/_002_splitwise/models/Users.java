package lowLevelDesign._002_splitwise.models;

import java.util.Objects;

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
        return "'" + name + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return id.equals(users.id) &&
                Objects.equals(email, users.email);
    }

}
