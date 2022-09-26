package com.ShoppingCart.ShoppingCart.controller;

import com.ShoppingCart.ShoppingCart.model.Product;
import com.ShoppingCart.ShoppingCart.repository.ProductRepository;
import com.ShoppingCart.ShoppingCart.service.ProductServiceImpl;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String getProducts(Model model){
        model.addAttribute("products", productService.getProducts());
        return "products";
    }
}
