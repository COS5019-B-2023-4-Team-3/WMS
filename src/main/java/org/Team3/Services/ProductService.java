package org.Team3.Services;
import org.Team3.Entities.Product;
import org.Team3.Repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductService encapsulates business logic related to products, coordinating interactions between controllers and repositories.
 * It provides methods to perform CRUD (Create, Read, Update, Delete) operations on products and retrieve specific information related to products.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products from the repository.
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves all products from the repository, sorted by name in ascending order.
     *
     * @return A list of all products, sorted by name in ascending order.
     */
    public List<Product> getAllProductsByNameAZ() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }

    /**
     * Retrieves all products from the repository, sorted by name in descending order.
     *
     * @return A list of all products, sorted by name in descending order.
     */
    public List<Product> getAllProductsByNameZA() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
    }

    /**
     * Retrieves all products from the repository, sorted by expiry date in ascending order.
     *
     * @return A list of all products, sorted by expiry date in ascending order.
     */
    public List<Product> getAllProductsByExpiryDate() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,"expiryDate"));
    }

    /**
     * Retrieves a product by its ID.
     * @param id The ID of the product to retrieve
     * @return The product if found, otherwise null
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new product based on the provided ProductDto.
     *
     * @param product The DTO containing product details
     */
    public void createProduct(Product product) {
        Product newProduct = new Product();

        newProduct.setName(product.getName());
        newProduct.setSkuCode(product.getSkuCode());
        newProduct.setDescription(product.getDescription());
        newProduct.setShelfLife(product.getShelfLife());
        newProduct.setExpiryDate(product.getExpiryDate());
        newProduct.setCurrentStockLevel(product.getCurrentStockLevel());
        newProduct.setMinStockLevel(product.getMinStockLevel());
        newProduct.setSellingPrice(product.getSellingPrice());
        newProduct.setUnitCost(product.getUnitCost());
        newProduct.setImageURL(product.getImageURL());
        newProduct.setExpiryDate(product.getExpiryDate());

        productRepository.save(newProduct);
    }

    /**
     * Updates an existing product with the provided ID using the details from the ProductDto.
     *
     * @param id      The ID of the product to update
     * @param product The DTO containing updated product details
     */
    public void updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            return; // Product with given id does not exist
        }
        // Update product details
        existingProduct.setName(product.getName());
        existingProduct.setSkuCode(product.getSkuCode());
        existingProduct.setSellingPrice(product.getUnitCost());
        existingProduct.setShelfLife(product.getShelfLife());
        existingProduct.setCurrentStockLevel(product.getCurrentStockLevel());
        existingProduct.setMinStockLevel(product.getMinStockLevel());
        existingProduct.setExpiryDate(product.getExpiryDate());
        productRepository.save(existingProduct);
    }

    /**
     * Deletes a product with the provided ID.
     * @param id The ID of the product to delete
     * @return True if the product was successfully deleted, otherwise false
     */
    @Transactional
    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false; // Product with given id does not exist
        }
        productRepository.deleteById(id);
        return true;
    }

    /**
     * Retrieves all product prices.
     * @return List of all product prices
     */
    public List<Double> getAllProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (Product product : getAllProducts()) {
            prices.add(product.getSellingPrice());
        }
        return prices;
    }

    /**
     * Retrieves products that have expired based on the provided current date.
     * @param currentDate The current date to compare against product expiry dates
     * @return List of expired products
     */
    @Transactional
    public List<Product> getExpiredProducts(LocalDate currentDate) {
        return productRepository.findExpiredProducts(currentDate);
    }

    /**
     * Retrieves products with low stock levels.
     * @return List of products with low stock levels
     */
    @Transactional
    public List<Product> getLowStockProducts() {
        return productRepository.findLowStockProducts();
    }

}
