import java.util.Map;

public class OutputHandler {
    private String tag;
    OutputHandler(String tag){
        this.tag = tag;
    }
    public void ConsolePrint(String message){
        System.out.println(this.tag + "\t\t" + message);
    }
    public void PrintAllProducts(Storage storage){
        ConsolePrint("Products in storage:");
        for (Map.Entry<Product, Integer> entry : storage.getStorage().entrySet()) {
            System.out.println("\t\t\t\t" + entry.getKey() +
                    " amount: \t" + entry.getValue());
        }
    }
}
