package designPatterns.creationalDesignPatterns.singleton.lazy;

public class AntiPatternDbConnection {
    /*
            Steps to Create Singleton Object

            1. Wait until there is call to create Object
            2. Protect others from creating Objects
            3. Expose function to get Instance
     */


    //Step-1
    private static AntiPatternDbConnection dbInstance;

    /*
        But the problem in this approach is -
            If 2 different thread will execute this method at the same time, 2 instance will be created
     */
    public static AntiPatternDbConnection getInstance(){
        if (dbInstance == null){
            dbInstance = new AntiPatternDbConnection();
        }
        return dbInstance;
    }


    //Step-2
    private AntiPatternDbConnection(){}

    public void isConnectionAvailable(){
        System.out.println("Connection Available !!!");
    }
}
