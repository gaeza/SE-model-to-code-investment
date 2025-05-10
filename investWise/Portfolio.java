package investWise;

import java.util.*;

public class Portfolio {
    private List<Asset> assets = new ArrayList<>();

    public void addAsset(Asset a) {
        assets.add(a);
    }

    public void listAssets() {
        for (Asset a : assets) a.display();
    }

    public boolean removeAsset(int id) {
        return assets.removeIf(a -> a.getAssetID() == id);
    }

    public boolean updateAsset(int id, Asset updated) {
        for (int i = 0; i < assets.size(); i++) {
            if (assets.get(i).getAssetID() == id) {
                updated.setAssetID(id); // retain ID
                assets.set(i, updated);
                return true;
            }
        }
        return false;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> list) {
        this.assets = list;
    }
}

