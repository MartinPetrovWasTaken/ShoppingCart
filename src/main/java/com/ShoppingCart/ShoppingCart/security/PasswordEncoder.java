package com.ShoppingCart.ShoppingCart.security;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = "123123";
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        System.out.println(encodedPassword);
    }
}
