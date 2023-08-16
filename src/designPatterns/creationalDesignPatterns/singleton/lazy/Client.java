package designPatterns.creationalDesignPatterns.singleton.lazy;

public class Client {
    public static void main(String[] args) {
        //Getting Singleton Object

        //Thread-1
        Thread t1 = new Thread(){
            @Override
            public void run() {
                AntiPatternDbConnection connection = AntiPatternDbConnection.getInstance();
                connection.isConnectionAvailable();
            }
        };

        //Thread-2
        Thread t2 = new Thread(){
            @Override
            public void run() {
                AntiPatternDbConnection connection = AntiPatternDbConnection.getInstance();
                connection.isConnectionAvailable();
            }
        };

        //If both thread started at the same time, they both will have separate objects
        t1.start();
        t2.start();
    }
}
