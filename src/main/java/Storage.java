import java.util.Hashtable;
import java.util.Map;

public class Storage extends Thread {
    private Map<Product, Integer> storage;
    private Integer max_capacity;
    private OutputHandler oputputHandler;

    Storage(Integer max_capacity) {
        this.storage = new Hashtable<Product, Integer>();
        for(Product p: Product.values()){
            this.storage.put(p, 0);
        }
        this.max_capacity = max_capacity;
        this.oputputHandler = new OutputHandler("STORAGE");
    }

    public Map<Product, Integer> getStorage() {
        return storage;
    }

    @Override
    public void run() {
        this.oputputHandler.ConsolePrint("started");
    }
    synchronized void HandleProductAddition(Product product, Integer amount){
        Integer current_capacity = storage.get(product);
        if (current_capacity != this.max_capacity){
            if (current_capacity + amount < this.max_capacity) {
                AddProducts(product, amount);
                current_capacity += amount;
                this.oputputHandler.ConsolePrint(
                        amount + " " + product + " added to storage. " +
                                "Current capacity: " + current_capacity);
            }
            else {
                Integer right_amount = this.max_capacity - current_capacity;
                AddProducts(product, right_amount);
                this.oputputHandler.ConsolePrint(
                        right_amount + " " + product + " added to storage. " +
                        " Storage FULL!");
            }
        }
        else{
            this.oputputHandler.ConsolePrint("FULL! Products cannot be added.");
        }
        this.oputputHandler.PrintAllProducts(this);
    }
    synchronized void AddProducts(Product product, Integer amount){
            Integer new_amount = storage.get(product) + amount;
            this.storage.remove(product);
            this.storage.put(product, new_amount);
    }
    synchronized String HandleProductGiving(Product product){
        if(storage.get(product) != 0) {
            Integer new_amount = storage.get(product) - 1;
            this.storage.remove(product);
            this.storage.put(product, new_amount);
            this.oputputHandler.ConsolePrint("Giving " + product + " to a client.");
            this.oputputHandler.PrintAllProducts(this);
            return "Got " + product;
        }
        else{
            this.oputputHandler.ConsolePrint("There is no " + product + " in the storage");
            return "Couldn't get "  + product;
        }
    }

}
