<!DOCTYPE html>
<html ng-app="myModule">
<head>
<meta charset="ISO-8859-1">
<title>Angular</title>
<script src="lib/angular.js"></script>
<script src="js/EmployeeController.js"></script>

<!-- Recursive template for employee tree -->

<script type="text/ng-template" id="employeeTree.html">
<ul >
	<li ng-repeat = "employee in employeesJsonList">
		{{employee.name}}

	<div ng-include="'employeeTree.html'" onload="employeesJsonList=employee.subordinates"></div>
	</li>

</ul>
</script>

</head>

<body ng-controller="myController">
<div ng-include="'employeeTree.html'"></div>
</body>

</html>
