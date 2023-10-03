package dev.vimlesh.productcatalog.service;

import dev.vimlesh.productcatalog.dto.GenericProductDto;
import dev.vimlesh.productcatalog.model.Price;
import dev.vimlesh.productcatalog.model.Product;
import dev.vimlesh.productcatalog.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Primary
@Service("SelfServiceProductImpl")
public class SelfServiceProductImpl implements ProductService{

    private ProductRepository productRepository;

    public SelfServiceProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto getProductById(UUID id) {
        Optional<Product> p = productRepository.findById(id);
        return p.map(this::convertFakeStoreProductIntoGenericProduct).orElse(null);
    }

    @Override
    public List<GenericProductDto> getProducts() {
        List<Product> p = productRepository.findAll();
        List<GenericProductDto> genericProductDtosList = new ArrayList<>();
        for(Product product:p){
            genericProductDtosList.add(convertFakeStoreProductIntoGenericProduct(product));
        }

        return genericProductDtosList;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public GenericProductDto deleteProductById(UUID id) {
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()){
            return null;
        }
        productRepository.deleteById(id);
        return convertFakeStoreProductIntoGenericProduct(p.get());
    }

    @Override
    public GenericProductDto updateProduct(UUID productId, GenericProductDto productRequest) {
        Optional<Product> p = productRepository.findById(productId);
        if(p.isEmpty()){
            return null;
        }
        Product product = p.get();
        product.setTitle(productRequest.getTitle());
        product.setPrice(new Price("USD", productRequest.getPrice()));
        productRepository.save(product);

        return p.map(this::convertFakeStoreProductIntoGenericProduct).orElse(null);
    }

    @Override
    public List<GenericProductDto> getAllProductsOfASpecificCategory(UUID categoryId) {
        List<Product> p = productRepository.findAllByCategoryUuid(categoryId);
        List<GenericProductDto> genericProductDtosList = new ArrayList<>();
        for(Product product:p){
            genericProductDtosList.add(convertFakeStoreProductIntoGenericProduct(product));
        }

        return genericProductDtosList;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(Product product) {

        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.getPrice().getCurrency());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice((long) product.getPrice().getPrice());
        genericProductDto.setCategory(product.getCategory().getName());

        return genericProductDto;
    }
}
