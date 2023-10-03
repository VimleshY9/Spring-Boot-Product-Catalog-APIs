package dev.vimlesh.productcatalog.thirdpartyclients;

import dev.vimlesh.productcatalog.dto.GenericProductDto;
import dev.vimlesh.productcatalog.exception.NotFoundException;
import lombok.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductClient {
    private RestTemplateBuilder restTemplateBuilder;

    private String fakeStoreApiUrl = "https://fakestoreapi.com";


    private String fakeStoreProductsApiPath = "/products";

    private String specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
    private String productRequestsBaseUrl = fakeStoreApiUrl + fakeStoreProductsApiPath; ;

    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder
                                          ) {
        this.restTemplateBuilder = restTemplateBuilder;

    }


    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                productRequestsBaseUrl, product, FakeStoreProductDto.class
        );

        return response.getBody();
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
//        FakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }

//        response.getStatusCode()

        return fakeStoreProductDto;
//        return null;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDto[].class);

        List<GenericProductDto> answer = new ArrayList<>();

        return Arrays.stream(response.getBody()).toList();
    }

    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();


        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE,
                requestCallback, responseExtractor, id);

        return response.getBody();
    }

}
