package dev.vimlesh.productcatalog.controller;


import dev.vimlesh.productcatalog.dto.ExceptionDto;
import dev.vimlesh.productcatalog.dto.GenericProductDto;
import dev.vimlesh.productcatalog.exception.NotFoundException;
import dev.vimlesh.productcatalog.model.Product;
import dev.vimlesh.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController( ProductService productService){
        this.productService = productService;
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
    return productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getProducts();
    }



   @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") UUID id) throws NotFoundException {
       return new ResponseEntity<>(productService.deleteProductById(id),
               HttpStatus.OK);
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("{productId}")
    public ResponseEntity<GenericProductDto> updateProduct(
            @PathVariable UUID productId,
            @RequestBody GenericProductDto request) {

        GenericProductDto updatedProduct = productService.updateProduct(productId, request);

        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("category/{categoryId}")
    public List<GenericProductDto> getAllProductsOfASpecificCategory(@PathVariable UUID categoryId) {
        return productService.getAllProductsOfASpecificCategory(categoryId);
    }

}
