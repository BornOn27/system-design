package designPatterns.creationalDesignPatterns.singleton.eager;

public class Client {
    public static void main(String[] args) {
        //Getting Singleton Object
        DbConnection connection = DbConnection.getInstance();
        connection.isConnectionAvailable();
    }
}
