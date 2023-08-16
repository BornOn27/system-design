package designPatterns.creationalDesignPatterns.singleton.eager;

public class DbConnection {
    /*
            Steps to Create Singleton Object

            1. Create static Object
            2. Protect others from creating Objects
            3. Expose function to get Instance
     */


    //Step-1
    private static DbConnection dbInstance = new DbConnection();

    //Step-2
    private DbConnection(){}

    public static DbConnection getInstance(){
        return dbInstance;
    }

    public void isConnectionAvailable(){
        System.out.println("Connection Available !!!");
    }
}
