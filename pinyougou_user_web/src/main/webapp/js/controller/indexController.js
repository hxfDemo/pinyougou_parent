app.controller('indexController',function ($scope,loginService) {
    $scope.showName=function () {
        loginService.loginService().success(
            function (response) {
       $scope.username = response.loginName;
        })
    }
})