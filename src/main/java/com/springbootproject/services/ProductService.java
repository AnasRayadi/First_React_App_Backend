package com.springbootproject.services;

import com.springbootproject.dao.ProductDao;
import com.springbootproject.exception.RequestValidationException;
import com.springbootproject.exception.ResourceNotFoundException;
import com.springbootproject.models.Product;
import com.springbootproject.requests.ProductRegistrationRequest;
import com.springbootproject.requests.ProductUpdateRequest;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }
    public List<Product> getAllProducts(){
        return productDao.selectAllProducts();
    }
    public Product getProduct(Integer id){
        return productDao.selectProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id [%s] not found".formatted(id)));
    }
    public void addProduct(ProductRegistrationRequest productRegistrationRequest){
        var product = Product.builder()
                .title(productRegistrationRequest.title())
                .image(productRegistrationRequest.image())
                .description(productRegistrationRequest.description())
                .date(productRegistrationRequest.date())
                .build();
        productDao.insertProduct(product);
    }
    public void removeProduct(Integer id){
        if(!productDao.existProduct(id)){
            throw new ResourceNotFoundException("Product with id [%s] doesn't exist".formatted(id));
        }
        productDao.deleteProduct(id);
    }

    public void updateProduct(Integer id, ProductUpdateRequest updateRequest){


            Product product = getProduct(id); //check if the person that you want to update exist or not
            boolean change = false;
            if(updateRequest.title()!= null && updateRequest.title() != product.getTitle()){
                product.setTitle(updateRequest.title());
                change = true;
            }
            if (updateRequest.image() != null && updateRequest.image() != product.getImage()){
                product.setImage(updateRequest.image());
                change = true;
            }
            if (updateRequest.description() != null && updateRequest.description() != product.getDescription())
                product.setDescription(updateRequest.description());
                change = true;
            if (!change){
                throw new RequestValidationException("No data changes found!");
            }
            productDao.updateProduct(product);
        }
}
