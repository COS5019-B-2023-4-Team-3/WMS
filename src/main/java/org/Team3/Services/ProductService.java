package org.Team3.Services;
import org.Team3.DTO.ProductDto;
import org.Team3.Entities.Product;
import org.Team3.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *  Responsible for encapsulating business logic related to its corresponding entity and coordinating interactions between controllers and repositories.
 *  Service methods interact with repository classes to perform database operations.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(ProductDto productDto) {
        Product product = new Product(productDto.getName(), productDto.getSkuCode(),
                productDto.getPrice(), productDto.getShelfLife(),
                productDto.getStockLevel(), productDto.getMinStockLevel());
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            return null;
        }

        existingProduct.setName(productDto.getName());
        existingProduct.setSkuCode(productDto.getSkuCode());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setShelfLife(productDto.getShelfLife());
        existingProduct.setCurrentStockLevel(productDto.getStockLevel());
        existingProduct.setMinStockLevel(productDto.getMinStockLevel());

        return productRepository.save(existingProduct);
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    public List<Double> getAllProductPrices() {
        List<Double> prices = new ArrayList<>();
        for(Product product: getAllProducts()){
            prices.add(product.getPrice());
        }
        return prices;
    }

    @Transactional
    public List<Product> getExpiredProducts(LocalDate currentDate) {
        return productRepository.findExpiredProducts(currentDate);
    }

    @Transactional
    public List<Product> getLowStockProducts() {
        return productRepository.findLowStockProducts();
    }
}
