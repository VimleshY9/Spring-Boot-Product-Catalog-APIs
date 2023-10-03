package dev.vimlesh.productcatalog.service;

import dev.vimlesh.productcatalog.dto.GenericProductDto;

import dev.vimlesh.productcatalog.exception.NotFoundException;
import dev.vimlesh.productcatalog.model.Product;
import dev.vimlesh.productcatalog.thirdpartyclients.FakeStoreProductClient;
import dev.vimlesh.productcatalog.thirdpartyclients.FakeStoreProductDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    FakeStoreProductClient fakeStoreProductClient;


    FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
    }
    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {

//        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductClient.getProductById(id));
        return null;
    }

    @Override
    public List<GenericProductDto> getProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductClient.getAllProducts()) {
            genericProductDtos.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }
      return genericProductDtos;
    }

    @Override
    public Product createProduct(Product product) {
//        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductClient.createProduct(product));
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(UUID id) throws NotFoundException {
//        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductClient.deleteProduct(id));
        return  null;
    }

    @Override
    public GenericProductDto updateProduct(UUID productId, GenericProductDto productRequest) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProductsOfASpecificCategory(UUID categoryId) {
        return null;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {

        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }


}
