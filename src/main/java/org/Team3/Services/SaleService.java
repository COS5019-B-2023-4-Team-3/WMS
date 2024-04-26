package org.Team3.Services;

import org.Team3.Entities.Sale;
import org.Team3.Entities.User;
import org.Team3.Repositories.SaleRepository;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
    public List<Sale> getWeekSales() {
        return saleRepository.findSalesAmount(LocalDate.now());
    }
    }