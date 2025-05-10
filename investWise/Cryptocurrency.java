package investWise;

public class Cryptocurrency extends Asset {
    public Cryptocurrency(int id, String name, float qty, float price, String date) {
        super(id, name, qty, price, date);
    }

    public String getType() { return "Crypto"; }

    public void display() {
        System.out.println("Crypto (" + assetID + ") " + assetName + " | Quantity: " + quantity +
                " | Price: " + purchasePrice + " | Date: " + purchaseDate);
    }
}

