import java.util.Random;

public enum Product {
    BREAD, HAM, CHEESE;
    private static final Random rand = new Random();

    public static Product GetRandomProduct()  {
        Product[] products = values();
        return products[rand.nextInt(products.length)];
    }
}
