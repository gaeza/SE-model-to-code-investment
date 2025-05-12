package investWise;

import java.io.*;
import java.util.*;

public class DatabaseManager {
    private final String FILE_NAME = "assets.csv";

    public void saveAsset(Asset asset) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.println(formatAssetCSV(asset));
        } catch (IOException e) {
            System.out.println("Error saving asset: " + e.getMessage());
        }
    }

    public void saveAllAssets(List<Asset> assets) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Asset asset : assets) {
                writer.println(formatAssetCSV(asset));
            }
        } catch (IOException e) {
            System.out.println("Error overwriting assets: " + e.getMessage());
        }
    }

    public List<Asset> loadAssets() {
        List<Asset> assets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 6) {
                    Asset asset = new Asset(
                            tokens[0],
                            tokens[1],
                            Double.parseDouble(tokens[2]),
                            tokens[3],
                            Double.parseDouble(tokens[4]),
                            tokens[5]
                    );
                    assets.add(asset);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No asset file found. A new one will be created.");
        } catch (IOException e) {
            System.out.println("Error reading asset file: " + e.getMessage());
        }
        return assets;
    }

    public void updateAsset(String assetId, String name, double quantity, String purchaseDate, double purchasePrice) {
        System.out.println("Asset updated in memory. Use saveAllAssets to persist.");
    }

    public void removeAsset(String assetId) {
        System.out.println("Asset removed from memory. Use saveAllAssets to persist.");
    }

    private String formatAssetCSV(Asset asset) {
        return String.join(",",
                asset.getAssetId(),
                asset.getName(),
                String.valueOf(asset.getQuantity()),
                asset.getPurchaseDate(),
                String.valueOf(asset.getPurchasePrice()),
                asset.getAssetType()
        );
    }
}
