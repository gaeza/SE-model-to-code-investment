package investWise;

public abstract class Asset {
    protected int assetID;
    protected String assetName;
    protected float quantity;
    protected float purchasePrice;
    protected String purchaseDate;

    public Asset(int assetID, String assetName, float quantity, float purchasePrice, String purchaseDate) {
        this.assetID = assetID;
        this.assetName = assetName;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
    }

    public int getAssetID() { return assetID; }
    public String getAssetName() { return assetName; }
    public float getQuantity() { return quantity; }
    public float getPurchasePrice() { return purchasePrice; }
    public String getPurchaseDate() { return purchaseDate; }

    public void setAssetID(int id) { this.assetID = id; }
    public void setAssetName(String name) { this.assetName = name; }
    public void setQuantity(float quantity) { this.quantity = quantity; }
    public void setPurchasePrice(float price) { this.purchasePrice = price; }
    public void setPurchaseDate(String date) { this.purchaseDate = date; }

    public void updateAssetDetails(String newName, float newQuantity, float newPrice) {
        this.assetName = newName;
        this.quantity = newQuantity;
        this.purchasePrice = newPrice;
    }

    public abstract String getType();
    public abstract void display();
}

