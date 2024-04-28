package org.Team3.Services;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Calculates statistics and generates a histogram chart of product prices using JFreeChart.
 */
@Service
public class AnalyticsService {

    private final ProductService productService;

    public AnalyticsService(ProductService productService) {
        this.productService = productService;
    }

    @Transactional
    public JFreeChart generateProductPriceHistogram(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<Double> prices = productService.getAllProductPrices();

        for(Double price: prices){
            dataset.addValue(1, "Price", price);  // Assuming each price has a count of 1
        }

        JFreeChart chart = ChartFactory.createHistogram(
                "Product Price Histogram",
                "Price",
                "Frequency",
                (IntervalXYDataset) dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        return chart;
    }

    public void saveChartAsPNG(JFreeChart chart, String filePath, int width, int height) throws IOException {
        ChartUtils.saveChartAsPNG(new File(filePath), chart, width, height);
    }
}
