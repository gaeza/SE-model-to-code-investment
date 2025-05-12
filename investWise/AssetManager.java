package investWise;
public class AssetManager {
    public boolean addAsset(Investor investor, Asset asset) {
        investor.addAsset(asset);
        return true;
    }

    public boolean editAsset(Investor investor, String assetId, String name, double quantity,
                             String purchaseDate, double purchasePrice) {
        return investor.editAsset(assetId, name, quantity, purchaseDate, purchasePrice);
    }

    public boolean removeAsset(Investor investor, String assetId) {
        return investor.removeAsset(assetId);
    }
}
