package investWise;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.*;
import org.apache.pdfbox.pdmodel.font.*;

/**
 * Class responsible for generating reports for asset details.
 */
public class ReportGenerator {

    /**
     * Displays the asset details as a financial report in the console.
     * 
     * @param assets the list of assets to display
     */
    public void generateReport(List<Asset> assets) {
        System.out.println("Financial Report:");
        System.out.println("----------------------------");
        for (Asset asset : assets) {
            System.out.println("Asset Name: " + asset.getAssetName());
            System.out.println("Type: " + asset.getType());
            System.out.println("Quantity: " + asset.getQuantity());
            System.out.println("Purchase Price: " + asset.getPurchasePrice());
            System.out.println("Purchase Date: " + asset.getPurchaseDate());
            System.out.println("----------------------------");
        }
    }

    /**
     * Exports the asset list as a PDF report.
     * 
     * The file is saved to a fixed path.
     * 
     * @param assets the list of assets to export
     */
    public void exportReportToPDF(List<Asset> assets) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.setLeading(15f);
            contentStream.newLineAtOffset(50, 750);

            contentStream.showText("Financial Report:");
            contentStream.newLine();

            for (Asset asset : assets) {
                contentStream.showText("Asset: " + asset.getAssetName());
                contentStream.newLine();
                contentStream.showText("Type: " + asset.getType());
                contentStream.newLine();
                contentStream.showText("Quantity: " + asset.getQuantity());
                contentStream.newLine();
                contentStream.showText("Purchase Price: " + asset.getPurchasePrice());
                contentStream.newLine();
                contentStream.showText("Purchase Date: " + asset.getPurchaseDate());
                contentStream.newLine();
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            // Save the report to a fixed path
            String filePath = "C:\\Users\\Lenovo\\Documents\\FinantialReport.pdf";
            document.save(filePath);
            System.out.println("PDF report saved at: " + filePath);
        } catch (IOException e) {
            System.out.println("Error exporting PDF: " + e.getMessage());
        }
    }

    /**
     * Exports the asset list as an Excel report.
     * 
     * @param assets the list of assets to export
     * @param filePath the path to save the Excel file
     */
    public void exportReportToExcel(List<Asset> assets) {
        String filePath = "C:\\Users\\Lenovo\\Documents\\FinantialReport.xlsx";  

        // Create a new Workbook
        Workbook workbook = new XSSFWorkbook();
        
        // Create a Sheet inside the Workbook
        Sheet sheet = workbook.createSheet("Financial Report");

        // Add header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Asset Name");
        header.createCell(1).setCellValue("Type");
        header.createCell(2).setCellValue("Quantity");
        header.createCell(3).setCellValue("Purchase Price");
        header.createCell(4).setCellValue("Purchase Date");

        // Add data from the list to the file
        int rowIndex = 1;  // Start from the second row (the first is for header)
        for (Asset asset : assets) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(asset.getAssetName());
            row.createCell(1).setCellValue(asset.getType());
            row.createCell(2).setCellValue(asset.getQuantity());
            row.createCell(3).setCellValue(asset.getPurchasePrice());
            row.createCell(4).setCellValue(asset.getPurchaseDate());
        }

        // Save the file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Excel report saved at: " + filePath);
        } catch (IOException e) {
            System.out.println("Error exporting Excel file: " + e.getMessage());
        }
    }
}
