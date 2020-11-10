app.controller("indexController",function ($scope,$controller,loginService) {
$scope.showLoginName=function () {
    loginService.loginService().success(
        function (response) {
       $scope.loginName= response.loginName;
    })
}
})