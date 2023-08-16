package designPatterns.creationalDesignPatterns.singleton.doubleLock;

public class Client {
    public static void main(String[] args) {
        //Getting Singleton Object
        DbConnection connection = DbConnection.getInstance();
        connection.isConnectionAvailable();
    }
}