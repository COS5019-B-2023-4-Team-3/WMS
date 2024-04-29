package org.Team3.Services;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.Team3.Entities.Sale;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class PDFGeneratorService {
    private final List<Sale> getAllSales;

    public PDFGeneratorService(List<Sale> getAllSales) {
        this.getAllSales = getAllSales;
    }

    private void tableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Sale ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Transaction Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Revenue", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Order Volume", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Order ID", font));
        table.addCell(cell);

    }

    private void tableData(PdfPTable table) {
        for (Sale sale : getAllSales) {
            table.addCell(String.valueOf(sale.getId()));
            table.addCell(String.valueOf(sale.getDate()));
            table.addCell(String.valueOf(sale.getIncome()));
            table.addCell(String.valueOf(sale.getVolume()));
            table.addCell(String.valueOf(sale.getOrderId()));
        }
    }
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Sales Data", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph1 = new Paragraph("List of Sales", fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph1);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        tableHeader(table);
        tableData(table);

        document.add(table);

        document.close();
    }
}
