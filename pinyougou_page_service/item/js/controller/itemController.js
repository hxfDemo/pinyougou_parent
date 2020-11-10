//控制层
app.controller('itemController', function ($scope,$http) {

    $scope.specificationItems = {};

        //数量操作
        $scope.addNum=function(x){
            $scope.num=$scope.num+x;
            if($scope.num<1){
                $scope.num=1;
            }
        }

    $scope.selectSpec = function (key, value) {
        $scope.specificationItems[key] = value;
        searchSku();
    }
    $scope.isSelected = function (key, value) {
        if ($scope.specificationItems[key] == value) {
            return true;
        }
        return false;
    }
    $scope.loadSku=function(){
        $scope.sku=skuList[0];
        $scope.specificationItems= JSON.parse(JSON.stringify($scope.sku.spec)) ;
    }
    matchObject = function (map1, map2) {
        for (var k in map1) {
            if (map1[k] != map2[k]) {
                return false;
            }
        }
        for (var k in map2) {//取出k
            if (map2[k] != map1[k]) {
                return false;
            }
        }
        return true;
    }
    searchSku=function(){
        for(var i=0;i<skuList.length;i++ ){
            if( matchObject(skuList[i].spec ,$scope.specificationItems ) ){
                $scope.sku=skuList[i];
                return ;
            }
        }
        $scope.sku={id:0,title:'--------',price:0};//如果没有匹配的
    }

    $scope.addToCart=function () {
      $http.get('http:localhost:8088/cart/addGoodsToCartList.do?itemId='+$scope.sku.id
      +"&num="+$scope.num).success(
          function (response) {
if (response.success) {
    location.href="http://localhost:8088/cart.html";
}else {
    alert(response.message)
}
      })
    }
});
