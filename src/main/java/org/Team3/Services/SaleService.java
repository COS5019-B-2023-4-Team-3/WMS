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


    private final SaleRepository saleRepository;


    /**
     * Constructor for SaleService.
     *
     * @param saleRepository The repository to be used by this service.
     */
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    /**
     * Retrieves all sales from the repository.
     *
     * @return A list of all sales.
     */
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    /**
     * Retrieves sales within a specified date range.
     * The sales are grouped by date, and the income is summed for each date.
     * The result is sorted by date in ascending order.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of maps, where each map represents a sale with the date and total income.
     */
    //TODO: return and ordered list of map<String, Object> based on date key
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
