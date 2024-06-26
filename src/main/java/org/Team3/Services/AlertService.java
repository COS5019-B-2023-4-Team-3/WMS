package org.Team3.Services;
import org.Team3.Entities.Alert;
import org.Team3.Entities.Product;
import org.Team3.Repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    private final ProductService productService;

    public AlertService(AlertRepository alertRepository, ProductService productService) {
        this.alertRepository = alertRepository;
        this.productService = productService;
    }



public List<Alert> getAllAlerts(){
    return alertRepository.findAll();

}
    /**
     *  Run daily at midnight
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void checkInventoryExpiration() {
        List<Product> expiredProducts = productService.getExpiredProducts(LocalDate.now());
        for (Product product : expiredProducts) {
            sendAlert("Inventory expiration alert: Product " + product.getName() + " has expired.");
        }
    }

    /**
     * Run every 24 hours (in milliseconds)
     */
    @Scheduled(fixedRate = 86400000)
    public void checkLowStockLevels() {
        List<Product> lowStockProducts = productService.getLowStockProducts();
        for (Product product : lowStockProducts) {
            sendAlert("Low stock alert: Product " + product.getName() + " has low stock level.");
        }
    }

    private void sendAlert(String message) {
        //TODO: Implement alert mechanism (e.g., send email, push notification, etc.)
        System.out.println("Alert: " + message);
    }
}
