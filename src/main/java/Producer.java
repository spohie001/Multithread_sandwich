import java.util.Random;

public class Producer extends Thread{
    private String name;
    private Product product;
    private Storage storage;
    private OutputHandler outputHandler;

    Producer(Product product, Storage storage, String name) {
        this.product = product;
        this.storage = storage;
        this.outputHandler = new OutputHandler(name);
    }
    @Override
    public void run(){
        this.outputHandler.ConsolePrint("started");
        while (true) {
            Random random = new Random();
            try {
                sleep((random.nextInt(10) + 1) * 1000);
                Integer amount = (random.nextInt(3) + 1);
                this.outputHandler.ConsolePrint("Produced " + amount + " " + product);
                storage.HandleProductAddition(this.product, amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
