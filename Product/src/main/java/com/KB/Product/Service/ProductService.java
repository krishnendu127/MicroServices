package com.KB.Product.Service;

import com.KB.Product.DTO.ProductRequest;
import com.KB.Product.DTO.ProductResponse;
import com.KB.Product.Model.Product;
import com.KB.Product.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved",product.getId());
    }
    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }
    public ProductResponse mapToProductResponse(Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
