package org.Team3.Controllers;

import org.Team3.Services.AnalyticsService;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * contains an endpoint /analytics/product-price-histogram
 * that triggers the generation of the product price histogram chart when accessed.
 * chart is generated in PNG format and returned as a byte array in the HTTP response.
 */
@Controller
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/product-price-histogram")
    public ResponseEntity<byte[]> generateProductPriceHistogram() {
        JFreeChart chart = analyticsService.generateProductPriceHistogram();
        try {
            byte[] chartBytes = ChartUtils.encodeAsPNG(chart.createBufferedImage(800, 600));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(chartBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
