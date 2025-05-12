package investWise;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.*;
import org.apache.pdfbox.pdmodel.font.*;
public class Zakat {
    static double calculate(Asset asset) {
        LocalDate purchase = LocalDate.parse(asset.getPurchaseDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate zakatDueDate = purchase.plusYears(1);
        LocalDate today = LocalDate.now();

        if (!today.isBefore(zakatDueDate)) {
            switch (asset.getName().toLowerCase())
            {
                case "gold":
                    if(asset.getQuantity() >= 85){
                        return 0.025 * asset.getCurrentValue();
                    }
                    break;
                case "silver":
                    if(asset.getQuantity() >= 595){
                        return 0.025 * asset.getCurrentValue();
                    }
                    break;
                case "real estate":
                    if(asset.getQuantity() >= 500_000){
                        return 0.025 * asset.getCurrentValue();
                    }
                    break;
                default:
                    return 0.0;

            }
        }
        return 0;
    }

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
                contentStream.showText("Zakat Due: " + String.format("%.2f", Zakat.calculate(asset)));
                contentStream.newLineAtOffset(0, -25);
                total += Zakat.calculate(asset);
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
