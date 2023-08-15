package designPatterns.creationalDesignPatterns.prototypePattern.deepClone;

import java.util.ArrayList;
import java.util.List;

public class HeavyObject {
    public int field_1;
    public String field_2;

    private Object somePrivateNetworkFetchedObject;
    public Object someDbFetchedObject;

    private Object somePrivateNotImportantField;
    public Object fieldWhichWillBeChangedFrequently;

    public List<String> listToDemoShallowCloning;

    public HeavyObject() {
    }


    public HeavyObject(int field_1, String field_2) {
        this.field_1 = field_1;
        this.field_2 = field_2;

        listToDemoShallowCloning = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            listToDemoShallowCloning.add("String-"+i);
        }
    }

    //Getters & Setters


    /*
            This is implementation of Prototype Pattern
            This is DeepCopy of Original Object
     */
    @Override
    protected HeavyObject clone() throws CloneNotSupportedException {
        HeavyObject newObject = new HeavyObject(1, "Args");
        return newObject;
    }

    @Override
    public String toString() {
        return "HeavyObject{" +
                "listToDemoShallowCloning=" + listToDemoShallowCloning +
                '}';
    }
}
