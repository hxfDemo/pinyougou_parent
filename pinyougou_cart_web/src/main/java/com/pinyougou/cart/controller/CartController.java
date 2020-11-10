package com.pinyougou.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.pinyougou.cart.service.CartService;
import com.pinyougou.pojo.group.Cart;
import com.pinyougou.utis.CookieUtil;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Reference
    private CartService cartService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @RequestMapping("/findCartList")
    public List<Cart> findCartList() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("登录名" + name);
        ;
        String cartList = CookieUtil.getCookieValue(request, "cartList", "UTF-8");

        System.out.println("向cookie中查询数据");
        if (cartList == null || cartList.equals("")) {
            cartList = "[]";
        }

        List<Cart> cartList_cookie = JSON.parseArray(cartList, Cart.class);
        if (name.equals("anonymousUser")) {
            return cartList_cookie;
        } else {
            System.out.println("向redis 中查询数据");
            List<Cart> cartList_redis = cartService.findCartListFromRedis(name);

            if (cartList_cookie.size() > 0) {
                List<Cart> carts = cartService.mergeCartList(cartList_cookie, cartList_redis);
                cartService.saveCartListRedis(name, carts);
                CookieUtil.deleteCookie(request, response, "cartList");
                return carts;
            }
            return cartList_redis;
        }
    }



    @RequestMapping("/addGoodsToCartList")
//    @CrossOrigin(origins="http://localhost:8083",allowCredentials="true")
    public Result addGoodsToCartList(Long itemId, Integer num) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8083");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("登录名" + name);
        System.out.println("向cookie中添加数据");
        try {
            List<Cart> cartList = findCartList();
            cartList = cartService.addGoodsToCartList(cartList, itemId, num);
            if (name.equals("anonymousUser")) {
                String cartListString = JSON.toJSONString(cartList);
                CookieUtil.setCookie(request, response, "cartList", cartListString, 3600 * 24, "UTF-8");

            } else {
                System.out.println("向redis中添加数据");
                cartService.saveCartListRedis(name, cartList);

            }

            return new Result(true, "添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }

    }
}
