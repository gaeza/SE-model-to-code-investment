package investWise;

public class Gold extends Asset {
    public Gold(int id, String name, float qty, float price, String date) {
        super(id, name, qty, price, date);
    }

    public String getType() { return "Gold"; }

    public void display() {
        System.out.println("Gold  +( "+assetID + ") " + assetName + " | Quantity: " + quantity +
                " | Price: " + purchasePrice + " | Date: " + purchaseDate);
    }
}

