package designPatterns.creationalDesignPatterns.singleton.synch;


public class Client {
    public static void main(String[] args) {
        //Getting Singleton Object

        //Thread-1
        Thread t1 = new Thread(){
            @Override
            public void run() {
                DbConnection connection = DbConnection.getInstance();
                connection.isConnectionAvailable();
            }
        };

        //Thread-2
        Thread t2 = new Thread(){
            @Override
            public void run() {
                DbConnection connection = DbConnection.getInstance();
                connection.isConnectionAvailable();
            }
        };

        /*
            In multiple thread case, since there is Method Level Locking -

            Important := Locking is only required for avoiding Duplicate Object Creation

            For 1000 threads, it will take un-necessary lock, since object was already created

         */
        t1.start();
        t2.start();
    }
}
