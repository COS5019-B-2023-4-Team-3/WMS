package org.Team3.Controllers;

import org.Team3.DTO.ProductDto;
import org.Team3.Entities.Product;
import org.Team3.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * ProductController class defines endpoints to handle CRUD (Create, Read, Update, Delete) operations related to products.
 *
 * This controller is responsible for processing HTTP requests and returning appropriate responses for operations
 * on products such as retrieving all products, retrieving a product by ID, creating a new product, updating an existing product,
 * and deleting a product.
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Displays the products page.
     *
     * @param model The model to add attributes for rendering the view.
     * @return String representing the view name for the products page.
     */
    @GetMapping("/products")
    public String showProductsPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    /**
     * Retrieves a product by its ID from the database.
     *
     * @param id Long representing the ID of the product to retrieve.
     * @return ResponseEntity containing a Product object representing the retrieved product.
     *         Returns HTTP status code OK (200) if the product is found.
     *         Returns HTTP status code NOT_FOUND (404) if the product is not found.
     */
    @GetMapping("/product-get-{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products/create")
    public String createProductsForm() {
        return "products-create";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductsForm(@PathVariable ("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "products-edit";
    }

    /**
     * Creates a new product in the database.
     *
     * @param product Product object representing the product to create.
     * @return ResponseEntity containing a Product object representing the newly created product.
     *         Returns HTTP status code CREATED (201) on success.
     */

    @PostMapping("/products-create")
    public String createProduct(@ModelAttribute("product") Product product, Model model) {
        try {
            productService.createProduct(product);
            return "redirect:/products";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create product");
            return "redirect:/products/create?error=failed_to_create_product";
        }
    }
//    @PostMapping("/products-create")
//    public String createProduct(@ModelAttribute Product product, @RequestParam("expiryDate") String expiryDate, Model model) {
//        try {
//            // Parse the expiry date string to a LocalDate object
//            LocalDate parsedExpiryDate = LocalDate.parse(expiryDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
////
////            // Set the parsed expiry date to the product
//            product.setExpiryDate(parsedExpiryDate);
//
//            // Save the product
//            productService.createProduct(product);
//
//            return "redirect:/products";
//        } catch (DateTimeParseException e) {
//            model.addAttribute("error", "Invalid expiry date format");
//            return "redirect:/products/create?error=invalid_date_format";
//        } catch (Exception e) {
//            model.addAttribute("error", "Failed to create product");
//            return "redirect:/products/create?error=product_already_exists";
//        }
//    }



//    @PostMapping("/products-create")
//    public ResponseEntity<Product> createProduct( @RequestBody ProductDto productDto){
//        Product product = productService.createProduct(productDto);
//
//        return new ResponseEntity<>(product, HttpStatus.CREATED);
//    }


//    @PostMapping("/products-create")
//    @ResponseBody
//    public ResponseEntity<String> createProduct(@ModelAttribute ProductDto productDto) {
//        try {
//            productService.createProduct(productDto);
//            return ResponseEntity.ok("Product created successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create product");
//        }
//    }

    /**
     * Updates an existing product in the database.
     *
     * @param id         Long representing the ID of the product to update.
     * @param product ProductDto object representing the updated product data.
     * @return ResponseEntity containing a Product object representing the updated product.
     *         Returns HTTP status code OK (200) if the product is updated successfully.
     *         Returns HTTP status code NOT_FOUND (404) if the product with the given ID is not found.
     */

@PostMapping("/products/{id}")
public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product, Model model) {
    Product existingProduct = productService.getProductById(id);
    existingProduct.setId(id);
    existingProduct.setName(product.getName());
    existingProduct.setSkuCode(product.getSkuCode());
    existingProduct.setDescription(product.getDescription());
    existingProduct.setShelfLife(product.getShelfLife());
//    existingProduct.setExpiryDate(product.getExpiryDate());
    existingProduct.setCurrentStockLevel(product.getCurrentStockLevel());
    existingProduct.setMinStockLevel(product.getMinStockLevel());
    existingProduct.setSellingPrice(product.getSellingPrice());
    existingProduct.setUnitCost(product.getUnitCost());
    existingProduct.setImageURL(product.getImageURL());
//    existingProduct.setDateString(product.getDateString());

//    String[] tokens = product.getDateString().split("-");
//
//    product.setExpiryDate(LocalDate.of(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[3])));

    productService.updateProduct(existingProduct);
    return "redirect:/products";
}

//    @PutMapping("/product-update-{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
//        Product updatedProduct = productService.updateProduct(id, productDto);
//        if (updatedProduct == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//    }

    /**
     * Deletes a product from the database.
     *
     * @param id Long representing the ID of the product to delete.
     * @return ResponseEntity with no content.
     *         Returns HTTP status code NO_CONTENT (204) if the product is deleted successfully.
     *         Returns HTTP status code NOT_FOUND (404) if the product with the given ID is not found.
     */
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

//    @DeleteMapping("/product-delete-{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        boolean deleted = productService.deleteProduct(id);
//        if (!deleted) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}