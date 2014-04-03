tipApp=angular.module("tip", []);
tipApp.controller("tipCtrl", function($scope){
	$scope.totalCost=null;
	$scope.tax=null;
	$scope.percents=[5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20];
})