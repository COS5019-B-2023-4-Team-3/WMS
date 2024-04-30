package org.Team3.Services;

import org.Team3.Entities.Sale;
import org.Team3.Repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    //TODO: reverse sorted sales it is showing backwards on the graph
    public List<Map<String, Object>> getSalesInRange(LocalDate startDate, LocalDate endDate) {
        List<Sale> sales = saleRepository.getSalesInRange(startDate, endDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
        Map<String, Double> groupSales = sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.getDate().format(formatter),
                        Collectors.summingDouble(Sale::getIncome)));

        Map<String, Double> sortedSales = groupSales.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, HashMap::new));

        return sortedSales.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("date", entry.getKey());
                    map.put("income", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }

}
