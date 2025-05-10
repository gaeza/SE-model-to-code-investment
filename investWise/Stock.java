package investWise;

public class Stock extends Asset {
    public Stock(int id, String name, float qty, float price, String date) {
        super(id, name, qty, price, date);
    }

    public String getType() { return "Stock"; }

    public void display() {
        System.out.println("Stock [" + assetID + "] " + assetName + " | Qty: " + quantity +
                " | Price: " + purchasePrice + " | Date: " + purchaseDate);
    }
}
