package org.Team3.Controllers;


import org.Team3.Entities.Product;
import org.Team3.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/products/sort-by-name-az")
    public String showProductsPageSortedAZ(Model model) {
        List<Product> products = productService.getAllProductsByNameAZ();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/sort-by-name-za")
    public String showProductsPageSortedZA(Model model) {
        List<Product> products = productService.getAllProductsByNameZA();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/sort-by-date")
    public String showProductsPageSortedByDate(Model model) {
        List<Product> products = productService.getAllProductsByExpiryDate();
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
        if(productService.getProductById(id) == null){
            model.addAttribute("error", "Product not found");
            return "redirect:/products";
        }

        productService.updateProduct(id, product);
        return "redirect:/products";
    }


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

}