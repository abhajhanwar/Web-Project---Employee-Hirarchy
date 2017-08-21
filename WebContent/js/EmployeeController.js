
var myApp = angular.module("myModule",[]);

var myController = function($scope, $http){
	
	// using $http service to get data from REST web service
	
	$http.get('http://localhost:8085/EmployeeHierarchy/webapi/employees')
	.then(function(data){
		
		var employeesJsonList = [data.data];
		$scope.employeesJsonList= employeesJsonList ;
		
		
	})

};
myApp.controller("myController", myController);