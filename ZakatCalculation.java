import java.io.*;
import java.util.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.*;
import org.apache.pdfbox.pdmodel.font.*;

abstract class Asset {
    protected String name;
    protected int investmentDuration;
    protected float allocationPercentage;
    protected double assetValue;
    protected double zakatAmountDue;

    public Asset(String name, int duration, float percentage, double value) {
        this.name = name;
        this.investmentDuration = duration;
        this.allocationPercentage = percentage;
        this.assetValue = value;
    }

    public abstract void calculateZakat();

    public double getZakatAmountDue() {
        return zakatAmountDue;
    }

    public String getName() {
        return name;
    }
}

class RealEstateAsset extends Asset {
    public RealEstateAsset(String name, int duration, float percentage, double value) {
        super(name, duration, percentage, value);
    }

    
    public void calculateZakat() {
        zakatAmountDue = assetValue * (allocationPercentage / 100.0) * 0.025;
    }
}

class StockAsset extends Asset {
    public StockAsset(String name, int duration, float percentage, double value) {
        super(name, duration, percentage, value);
    }

   
    public void calculateZakat() {
        zakatAmountDue = assetValue * (allocationPercentage / 100.0) * 0.0275;
    }
}

class CryptoAsset extends Asset {
    public CryptoAsset(String name, int duration, float percentage, double value) {
        super(name, duration, percentage, value);
    }

    
    public void calculateZakat() {
        zakatAmountDue = assetValue * (allocationPercentage / 100.0) * 0.03;
    }
}

class GoldAsset extends Asset {
    public GoldAsset(String name, int duration, float percentage, double value) {
        super(name, duration, percentage, value);
    }

    
    public void calculateZakat() {
        zakatAmountDue = assetValue * (allocationPercentage / 100.0) * 0.025;
    }
}

class ReportGenerator {
    public void saveReportToPdf(List<Asset> assets) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);

            double total = 0;
            for (Asset asset : assets) {
                contentStream.showText("Asset: " + asset.getName());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Zakat Due: " + String.format("%.2f", asset.getZakatAmountDue()));
                contentStream.newLineAtOffset(0, -25);
                total += asset.getZakatAmountDue();
            }

            contentStream.showText("Total Zakat: " + String.format("%.2f", total));
            contentStream.endText();
            contentStream.close();

            document.save("ZakatReport.pdf");
            System.out.println("PDF downloaded successfully.");
        } catch (IOException e) {
            System.out.println("Error saving PDF: " + e.getMessage());
        }
    }
}

public class ZakatCalculation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Asset> assets = new ArrayList<>();

        while (true) {
            System.out.println("\nSelect Asset Type:");
            System.out.println("1. Real Estate");
            System.out.println("2. Stock");
            System.out.println("3. Crypto");
            System.out.println("4. Gold");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            if (choice == 5) break;

            System.out.print("Enter asset name: ");
            String name = input.nextLine();

            System.out.print("Enter investment duration (years): ");
            int duration = input.nextInt();

            System.out.print("Enter allocation percentage (%): ");
            float percentage = input.nextFloat();

            System.out.print("Enter asset value: ");
            double value = input.nextDouble();
            input.nextLine();

            Asset asset = null;

            switch (choice) {
                case 1:
                    asset = new RealEstateAsset(name, duration, percentage, value);
                    break;
                case 2:
                    asset = new StockAsset(name, duration, percentage, value);
                    break;
                case 3:
                    asset = new CryptoAsset(name, duration, percentage, value);
                    break;
                case 4:
                    asset = new GoldAsset(name, duration, percentage, value);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }

            asset.calculateZakat();
            assets.add(asset);

            System.out.println("Zakat for " + asset.getName() + ": " + asset.getZakatAmountDue());
        }

        double total = assets.stream().mapToDouble(Asset::getZakatAmountDue).sum();
        System.out.println("\nTotal zakat due: " + total);

        System.out.print("\nDo you want to download the zakat report as PDF? (Y/N): ");
        String download = input.nextLine();

        if (download.equalsIgnoreCase("Y")) {
            ReportGenerator generator = new ReportGenerator();
            generator.saveReportToPdf(assets);
        } else {
            System.out.println("Download skipped.");
        }
    }
}
