package investWise;

public class RealEstate extends Asset {
    public RealEstate(int id, String name, float qty, float price, String date) {
        super(id, name, qty, price, date);
    }

    public String getType() { return "RealEstate"; }

    public void display() {
        System.out.println("RealEstate [" + assetID + "] " + assetName + " | Qty: " + quantity +
                " | Price: " + purchasePrice + " | Date: " + purchaseDate);
    }
}

