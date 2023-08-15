package designPatterns.creationalDesignPatterns.prototypePattern.impl;

public class HeavyObject {
    public int field_1;
    public String field_2;

    private Object somePrivateNetworkFetchedObject;
    public Object someDbFetchedObject;

    private Object somePrivateNotImportantField;
    public Object fieldWhichWillBeChangedFrequently;

    public HeavyObject() {
    }

    public HeavyObject(int field_1, String field_2) {
        this.field_1 = field_1;
        this.field_2 = field_2;
    }

    //Getters & Setters

    /*
        This is implementation of Prototype Pattern

     */

    @Override
    protected HeavyObject clone() {
        HeavyObject newObject = new HeavyObject(this.field_1, this.field_2);
        newObject.somePrivateNetworkFetchedObject = (Object)"Setting Private Field";

        /*
            He we can maintain all the list of fields which should be
            copied/not-copied across other objects

         */

        return newObject;
    }
}
