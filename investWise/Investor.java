package investWise;
import java.util.Optional;
import java.util.List;

public class Investor extends User {
    private Portfolio portfolio;
    private DatabaseManager db = new DatabaseManager();

    public Investor(String userId, String username, String email, String password) {
        super(userId, username, email, password);
        this.portfolio = new Portfolio(userId + "-portfolio");
        List<Asset> loadedAssets = db.loadAssets();
        this.portfolio.setAssets(loadedAssets);
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
        db.saveAllAssets(portfolio.getAssets());
    }

    public boolean editAsset(String assetId, String name, double quantity, String purchaseDate, double purchasePrice) {
        Optional<Asset> existingAssetOpt = portfolio.getAssetById(assetId);

        if (existingAssetOpt.isPresent()) {
            Asset existingAsset = existingAssetOpt.get();
            Asset updatedAsset = new Asset(
                    assetId,
                    name != null ? name : existingAsset.getName(),
                    quantity > 0 ? quantity : existingAsset.getQuantity(),
                    purchaseDate != null ? purchaseDate : existingAsset.getPurchaseDate(),
                    purchasePrice > 0 ? purchasePrice : existingAsset.getPurchasePrice(),
                    existingAsset.getAssetType()
            );
            boolean updated = portfolio.updateAsset(assetId, updatedAsset);
            if (updated) db.saveAllAssets(portfolio.getAssets());
            return updated;
        }

        return false;
    }

    public boolean removeAsset(String assetId) {
        boolean removed = portfolio.removeAsset(assetId);
        if (removed) db.saveAllAssets(portfolio.getAssets());
        return removed;
    }

    public void addAsset(Asset asset) {
        portfolio.addAsset(asset);
        db.saveAllAssets(portfolio.getAssets());
    }
}
