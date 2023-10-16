package designPatterns.creationalDesignPatterns.singletonPattern.synch;

public class DbConnection {
    /*
            Steps to Create Singleton Object

            1. Wait until there is call to create Object
            2. Protect others from creating Objects
            3. Expose function to get Instance
     */


    //Step-1
    private static DbConnection dbInstance;

    /*
        But the problem in this approach is -
            Every time lock is taken, which is not required once Object is created
     */
    public static synchronized DbConnection getInstance(){
        if (dbInstance == null){
            dbInstance = new DbConnection();
        }
        return dbInstance;
    }


    //Step-2
    private DbConnection(){}

    public void isConnectionAvailable(){
        System.out.println("Connection Available !!!");
    }
}
