package com.cart_service.service;


import com.cart_service.dto.AddToCartRequest;
import com.cart_service.entity.Cart;

public interface CartService {

    public Cart addToCart(String uuid, AddToCartRequest addRequestToCat);
    public Cart getCart(String uuid);
    public void clearCart(String uuid);
}
