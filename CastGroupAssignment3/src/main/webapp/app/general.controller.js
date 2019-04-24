angular.module("crudApp").controller("GeneralController", GeneralController);

GeneralController.inject = [ '$scope', 'Person' ];

function GeneralController($scope, Person) {

	$scope.Persons = Person.query();

	$scope.person = {};

	$scope.buttonText = "Submit";

	$scope.savePerson = function() {
		if ($scope.person.id !== undefined) {
			Person.update($scope.Person, function() {
				$scope.persons = Person.query();
				$scope.person = {};
				$scope.buttonText = "Submit";
			});
		} else {
			Person.save($scope.person, function() {
				$scope.persons = Person.query();
				$scope.person = {};
			});
		}
	}

	$scope.updatePersonInit = function(person) {
		$scope.buttonText = "Update";
		$scope.person = person;
	}

	$scope.deletePerson = function(person) {
		person.$delete({
			id : person.id
		}, function() {
			$scope.persons = Person.query();
		});
	}

}