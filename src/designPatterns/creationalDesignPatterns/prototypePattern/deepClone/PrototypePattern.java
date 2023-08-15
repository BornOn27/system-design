package designPatterns.creationalDesignPatterns.prototypePattern.deepClone;

import java.util.ArrayList;
import java.util.List;

public class PrototypePattern {
    public static void main(String[] args) throws CloneNotSupportedException {
        HeavyObject originalObject = new HeavyObject(1, "Args");
        //All operation of setting value in Original Object

        List<HeavyObject> objectList = new ArrayList<>();
        //This is example of Shallow Clone which is provided via Object class
        for (int i = 0; i < 5; i++) {
            HeavyObject newHeavyObject = originalObject.clone();

            //Use it freely
            objectList.add(newHeavyObject);
        }

        /*

                This is Deep Cloning, All other object are different copy of Original Object
         */
        originalObject.listToDemoShallowCloning.remove(3);
        System.out.println(originalObject);
        for (int i = 0; i < objectList.size(); i++) {
            System.out.println(objectList.get(i));
        }
    }
}
