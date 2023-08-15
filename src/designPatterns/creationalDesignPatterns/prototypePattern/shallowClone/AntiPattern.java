package designPatterns.creationalDesignPatterns.prototypePattern.shallowClone;

public class AntiPattern {
    public static void main(String[] args) {
        /*
                Here I want to create the duplicateHeavyObject of class.
                For a use-case I need thousands of Objects, but with different field-value
         */

        HeavyObject heavyObject = new HeavyObject(1, "Args");


        //Now I want to create a (technically clone) similar duplicateHeavyObject

        HeavyObject duplicateHeavyObject = new HeavyObject(1, "Args");
        /*
            I want to change the private field
            duplicateHeavyObject.someNetworkFetchedObject = someNetworkFetchedObject;

         */

        /*
            Also I need to know all the fields which needs to be  reset
            so that the same duplicateHeavyObject-clone can be used for different use-case
         */
        duplicateHeavyObject.fieldWhichWillBeChangedFrequently = (Object)"Changing Value...";
        duplicateHeavyObject.someDbFetchedObject = (Object)"Fetching from DB...";
    }
}
