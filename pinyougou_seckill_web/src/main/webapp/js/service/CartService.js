app.service("cartService", function ($http) {
    this.findCartList = function () {
        return $http.get("cart/findCartList.do")
    }
    this.addGoodsToCartList = function (itemId, num) {
        return $http.get('cart/addGoodsToCartList.do?itemId=' + itemId + '&num' + num)
    }
    this.sum = function (cartList) {
        var totalValue = {totalNum: 0, totalMoney: 0.00};
        for (var i = 0; cartList.length; i++) {
            var cart = cartList[i];
            for (var j = 0; j < cart.orderItemList.length; j++) {
                var orderItem = cart.orderItemList[j];
                totalValue.totalNum += orderItem.num;
                totalValue.totalMoney += orderItem.totalFree;
            }
        }
        return totalValue;
    }
    this.findAddressList = function () {
    return $http.get('address/findListByLoginUser.do')
    }
})