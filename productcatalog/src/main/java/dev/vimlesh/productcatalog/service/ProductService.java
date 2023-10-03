package dev.vimlesh.productcatalog.service;

import dev.vimlesh.productcatalog.dto.GenericProductDto;
import dev.vimlesh.productcatalog.exception.NotFoundException;
import dev.vimlesh.productcatalog.model.Product;

import java.util.List;
import java.util.UUID;


public interface ProductService {
    GenericProductDto getProductById(UUID id) throws NotFoundException;
    List<GenericProductDto> getProducts();

    Product createProduct(Product product);

    GenericProductDto deleteProductById(UUID id) throws NotFoundException;

    GenericProductDto updateProduct(UUID productId, GenericProductDto productRequest);

    List<GenericProductDto> getAllProductsOfASpecificCategory(UUID categoryId);
}
