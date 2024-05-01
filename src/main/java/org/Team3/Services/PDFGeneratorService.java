package org.Team3.Services;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import org.Team3.Entities.Sale;
import org.jfree.chart.ChartFactory;

import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PDFGeneratorService {

    @Autowired
    SaleService saleService;


    /**
     * Exports a PDF report.
     *
     * @param response The HttpServletResponse object.
     * @param data The data to be included in the report.
     * @throws IOException If an input or output exception occurred.
     * @throws DocumentException If a document exception occurred.
     */
    public void export(HttpServletResponse response, List<Map<String, Object>> data) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        //Title
        document.add(createTitle());
        //Create Chart
        document.add(createChart(data));
        document.add(createParagraph("List of Sales"));
        document.add(createTable());
        document.close();
    }

    /**
     * Creates the title for the report.
     *
     * @return The title as a Paragraph object.
     */
    private Paragraph createTitle(){
        String currentDateTime = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Paragraph title = new Paragraph("Sales Report : " + currentDateTime, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
        title.setAlignment(Paragraph.ALIGN_CENTER);
        return title;
    }

    /**
     * Creates a chart for the report.
     *
     * @param data The data to be included in the chart.
     * @return The chart as an Image object.
     * @throws BadElementException If a bad element exception occurred.
     * @throws IOException If an input or output exception occurred.
     */
    private Image createChart(List<Map<String, Object>> data) throws BadElementException, IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach(entry -> dataset.addValue((Number) entry.get("income"), "Income", (String) entry.get("date")));
        BufferedImage bufferedImage = ChartFactory.createLineChart(
                "Sales", "Date", "Income", dataset).createBufferedImage(500, 400);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        return Image.getInstance(byteArrayOutputStream.toByteArray());
    }

    /**
     * Creates a paragraph for the report.
     *
     * @param text The text to be included in the paragraph.
     * @return The paragraph as a Paragraph object.
     */
    private Paragraph createParagraph(String text) {
        Paragraph paragraph = new Paragraph(text, FontFactory.getFont(FontFactory.HELVETICA, 12));
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        return paragraph;
    }

    /**
     * Creates a table for the report.
     *
     * @return The table as a PdfPTable object.
     * @throws DocumentException If a document exception occurred.
     */
    private PdfPTable createTable() throws DocumentException{
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
        addTableHeader(table);
        addTableData(table);
        return table;
    }

    /**
     * Adds a header to the table.
     *
     * @param table The table to which the header will be added.
     */
    private void addTableHeader(PdfPTable table) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.BLACK);
        for(String header : new String[]{"Sale ID", "Transaction Date", "Revenue", "Order Volume", "Order ID"}){
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setPhrase(new Phrase(header, font));
            table.addCell(cell);
        }
    }

    /**
     * Adds data to the table.
     *
     * @param table The table to which the data will be added.
     */
    private void addTableData(PdfPTable table){
        for(Sale sale: saleService.getAllSales()){
            table.addCell(String.valueOf(sale.getId()));
            table.addCell(sale.getDate().toString());
            table.addCell(sale.getIncome().toString());
            table.addCell(String.valueOf(sale.getVolume()));
            table.addCell(String.valueOf(sale.getOrderId()));
        }
    }
}

