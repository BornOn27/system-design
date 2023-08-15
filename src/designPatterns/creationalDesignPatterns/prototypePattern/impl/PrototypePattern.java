package designPatterns.creationalDesignPatterns.prototypePattern.impl;

public class PrototypePattern {
    public static void main(String[] args) {
        HeavyObject originalObject = new HeavyObject(1, "Args");
        //All operation of setting value in Original Object

        for (int i = 0; i < 1000; i++) {
            HeavyObject newHeavyObject = originalObject.clone();

            //Use it freely
        }
    }
}
