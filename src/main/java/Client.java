import java.util.LinkedList;
import java.util.Random;

public class Client extends Thread {
    private String name;
    private Storage storage;
    private OutputHandler outputHandler;
    private LinkedList<Product> order;

    Client(String name, Storage storage){
        this.name = name;
        this.storage = storage;
        this.outputHandler = new OutputHandler(name);
        this.order = new LinkedList<Product>();
    }
    @Override
    public void run() {
        this.outputHandler.ConsolePrint("started");
        Random rand = new Random();
        while (true) {
            try {
                sleep((rand.nextInt(50)+1) * 1000);
                Integer amount = rand.nextInt(2)+3;
                for (int i = 0; i<amount; i++){
                    this.order.add(Product.GetRandomProduct());
                }
                this.outputHandler.ConsolePrint("Wants sandwitch made of: " + order);

                for (Product p :this.order) {
                    this.outputHandler.ConsolePrint("Wants " + p);
                    this.outputHandler.ConsolePrint(
                            this.storage.HandleProductGiving(p));
                }
                this.order.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
