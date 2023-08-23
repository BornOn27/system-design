package designPatterns.creationalDesignPatterns.singleton.doubleCheckedLock;

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
            Multiple Servers Multiple Instance
     */
    public static DbConnection getInstance(){
        if (dbInstance == null){
            synchronized (DbConnection.class){
                if(dbInstance == null)
                    dbInstance = new DbConnection();
            }
        }
        return dbInstance;
    }


    //Step-2
    private DbConnection(){}

    public void isConnectionAvailable(){
        System.out.println("Connection Available !!!");
    }
}
