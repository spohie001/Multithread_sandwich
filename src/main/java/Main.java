import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(20);
        storage.start();

        for(Product p : Product.values()) {
            Producer newProducer = new Producer(p, storage, p + "_PRODUCER");
            newProducer.start();
        }

        int numberOfClients = 5;
        for(int i =1; i<=numberOfClients; i++) {
            Client newKlient = new Client("client"+ i, storage);
            newKlient.start();
        }
        System.out.println("---MAIN THREAD STARTED---");
    }
}
