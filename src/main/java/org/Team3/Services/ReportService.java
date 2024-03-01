package org.Team3.Services;

import org.Team3.Entities.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * generates an Excel report of product inventory using Apache POI
 */
@Service
public class ReportService {
    @Autowired
    private ProductService productService;

    @Transactional
    public void generateProductInventoryReport(String filePath) throws IOException{
        List<Product> products = productService.getAllProducts();

        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Product Inventory");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("SKU");
            headerRow.createCell(2).setCellValue("Name");
            headerRow.createCell(3).setCellValue("Price");
            headerRow.createCell(4).setCellValue("ShelfLife");

            int rowNum = 1;
            for(Product product: products){
                Row row = sheet.createRow(rowNum++);
                headerRow.createCell(0).setCellValue(product.getId());
                headerRow.createCell(1).setCellValue(product.getSkuCode());
                headerRow.createCell(2).setCellValue(product.getName());
                headerRow.createCell(3).setCellValue(product.getSellingPrice());
                headerRow.createCell(4).setCellValue(product.getShelfLife());
            }

            try(FileOutputStream fileOut = new FileOutputStream(filePath)){
                workbook.write(fileOut);
            }
        }
    }
}
