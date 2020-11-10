//控制层
app.controller('cartController', function ($scope, cartService) {

    $scope.findCartList = function () {
        cartService.findCartList().success(function (response) {
            $scope.cartList = response;
            $scope.totalValue = cartService.sum($scope.cartList);
        })
    }
    $scope.addGoodsToCartList = function (itemId, num) {
        cartService.addGoodsToCartList(itemId, num).success(function (response) {
            if (response.success) {
                $scope.findCartList();
            } else {
                alert(response.message)
            }
        })
    }
    $scope.findAddressList = function () {
        cartService.findAddressList().success(
            function (response) {
                $scope.addressList = response;
            })

    }
    $scope.selectAddress=function (address) {
        $scope.address=address;
    }

    $scope.isSelectAddress=function (aderess){
       if (address=$scope.address){
           return true;
       }  else {
           return false;
       }
    }
});
