package lowLevelDesign._002_splitwise.models;

import java.util.Date;

public class Expenses {
    public Integer tId;
    public String name;
    public Groups group;
    public Users paidBy;
    public Double amount;
    public Date date;

    public Expenses(Integer tId, String name, Groups group, Users paidBy, Double amount, Date date) {
        this.tId = tId;
        this.name = name;
        this.group = group;
        this.paidBy = paidBy;
        this.amount = amount;
        this.date = date;
    }
}
