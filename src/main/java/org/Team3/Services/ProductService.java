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
 * ProductService encapsulates business logic related to products, coordinating interactions between controllers and repositories.
 * It provides methods to perform CRUD (Create, Read, Update, Delete) operations on products and retrieve specific information related to products.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieves all products from the repository.
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
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
     * @param productDto The DTO containing product details
     * @return The newly created product
     */
//    public Product createProduct(ProductDto productDto) {
//        Product product = new Product();
//        // Set product details from DTO
//        product.setName(productDto.getName());
//        product.setSkuCode(productDto.getSkuCode());
//        product.setDescription(productDto.getDescription());
//        product.setShelfLife(productDto.getShelfLife());
//        product.setExpiryDate(productDto.getExpiryDate());
//        product.setCurrentStockLevel(productDto.getCurrentStockLevel());
//        product.setMinStockLevel(productDto.getMinStockLevel());
//        product.setSellingPrice(productDto.getUnitSellPrice());
//        product.setUnitCost(productDto.getUnitCost());
////        product.setImageURL(productDto.getImageURL());
//
//        return productRepository.save(product);
//    }

    public Product createProduct(Product product) {
        // may want to check if the product already exists before saving it to the database
        return productRepository.save(product);
    }
    /**
     * Updates an existing product with the provided ID using the details from the ProductDto.
     * @param id The ID of the product to update
     * @param productDto The DTO containing updated product details
     * @return The updated product if successful, otherwise null
     */
//    public Product updateProduct(Long id, ProductDto productDto) {
//        Product existingProduct = productRepository.findById(id).orElse(null);
//        if (existingProduct == null) {
//            return null; // Product with given id does not exist
//        }
//        // Update product details
//        existingProduct.setName(productDto.getName());
//        existingProduct.setSkuCode(productDto.getSkuCode());
//        existingProduct.setSellingPrice(productDto.getUnitCost());
//        existingProduct.setShelfLife(productDto.getShelfLife());
//        existingProduct.setCurrentStockLevel(productDto.getCurrentStockLevel());
//        existingProduct.setMinStockLevel(productDto.getMinStockLevel());
//        return productRepository.save(existingProduct);
//    }

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

    public Product updateProduct (Product product) {
        return productRepository.save(product);
    }

    public boolean productExists(Long id) {
        // Check if a product with the id exists in the database
        return productRepository.existsById(id);
    }


}
