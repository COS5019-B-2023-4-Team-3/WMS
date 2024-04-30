package org.Team3.Services;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import org.Team3.Entities.Sale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PDFGeneratorService {

    @Autowired
    SaleService saleService;

    public void export(HttpServletResponse response, List<Map<String, Object>> data) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        //Title
        document.add(setTitle());

        //Create Line Chart
        DefaultCategoryDataset dataset = createDataset(data);
        Image chart = createLineChartFrom(dataset);
        document.add(chart);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph1 = new Paragraph("List of Sales", fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
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

    private Paragraph setTitle(){
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String currentDateTime = dateFormatter.format(date);

        //Title
        Paragraph title = new Paragraph("Sales Report : " + currentDateTime, fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        return title;
    }

   private DefaultCategoryDataset createDataset(List<Map<String, Object>> data) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (Map<String, Object> entry : data) {
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM");
        Date date = null;
        try {
            date = dateFormatter.parse((String) entry.get("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dataset.addValue((Number) entry.get("income"), "Income", dateFormatter.format(date));
    }
    return dataset;
}

    private Image createLineChartFrom(DefaultCategoryDataset dataset) throws BadElementException, IOException {
        //Create Line Chart
        JFreeChart lineChart = ChartFactory.createLineChart("Sales", "Date", "Income", dataset);

        //Render chart as image
        BufferedImage bufferedImage = lineChart.createBufferedImage(500, 400);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

        //Add image to PDF
        return Image.getInstance(byteArrayOutputStream.toByteArray());
    }

    private void tableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.BLACK);

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
        for (Sale sale : saleService.getAllSales()) {
            table.addCell(String.valueOf(sale.getId()));
            table.addCell(sale.getDate().toString());
            table.addCell(sale.getIncome().toString());
            table.addCell(String.valueOf(sale.getVolume()));
            table.addCell(String.valueOf(sale.getOrderId()));
        }
    }
}
