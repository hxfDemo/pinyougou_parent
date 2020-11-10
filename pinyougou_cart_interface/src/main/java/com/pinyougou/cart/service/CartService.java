package com.pinyougou.cart.service;

import com.pinyougou.pojo.group.Cart;

import java.util.List;

public interface CartService {
//    添加商品到购物车
    public List<Cart> addGoodsToCartList(List<Cart> list, Long itemId, Integer num);
public List<Cart> findCartListFromRedis(String username);
public void saveCartListRedis(String username,List<Cart> cartList);
public List<Cart> mergeCartList(List<Cart> cartList1,List<Cart> cartList2);
}