package investWise;

import java.util.List;

public class AssetScreen {
    private AssetManager assetManager;

    public AssetScreen() {
        this.assetManager = new AssetManager();
    }

    public void displayAssetList(Investor investor) {
        System.out.println("===== Your Assets =====");
        List<Asset> assets = investor.getPortfolio().getAssets();
        if (assets.isEmpty()) {
            System.out.println("No assets found in your portfolio.");
        } else {
            for (int i = 0; i < assets.size(); i++) {
                System.out.println((i + 1) + ". " + assets.get(i).toString());
            }
        }
    }

    public void handleAddAsset(Investor investor, String assetId, String name, double quantity,
                               String purchaseDate, double purchasePrice, String assetType) {
        Asset newAsset = new Asset(assetId, name, quantity, purchaseDate, purchasePrice, assetType);
        assetManager.addAsset(investor, newAsset);
        System.out.println("Asset added successfully!");
    }

    public void handleEditAsset(Investor investor, String assetId, String name, double quantity,
                                String purchaseDate, double purchasePrice) {
        boolean result = assetManager.editAsset(investor, assetId, name, quantity, purchaseDate, purchasePrice);
        System.out.println(result ? "Asset updated successfully!" : "Asset not found.");
    }

    public void handleRemoveAsset(Investor investor, String assetId) {
        boolean result = assetManager.removeAsset(investor, assetId);
        System.out.println(result ? "Asset removed successfully!" : "Asset not found.");
    }
}
