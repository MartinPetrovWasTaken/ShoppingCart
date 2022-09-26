package com.ShoppingCart.ShoppingCart.service;

import com.ShoppingCart.ShoppingCart.model.Product;
import com.ShoppingCart.ShoppingCart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>(productRepository.findAll());
        return products;
    }

    public Product getProduct(Long id) {
        return productRepository
                .findById(id)
                .orElse(null);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product("HyperX Alloy Origins Core", 180.00, "images/hyperxOriginCore.png", "klaviatura"));
        products.add(new Product("HyperX Pulsefire FPS Pro", 110.00, "images/pulsfireFpsPro.png", "mishka"));
        products.add(new Product("Logitech G Pro Headset", 230.00, "images/Logitech_G_Pro_Headset.png", "headset"));
        products.add(new Product("Razer Hammerhead True Wireless Pro", 200.00, "images/Razer_Hammerhead_True_Wireless_Pro.png", "mishka"));
        products.add(new Product("Asus Tuf VG279QM", 500.00, "images/Asus_Tuf_VG279QM.png", "monitor"));

        for (Product product : products) {
            if(productRepository.existsByName(product.getName())){

            }else{
                productRepository.save(product);
            }
        }
    }
}
