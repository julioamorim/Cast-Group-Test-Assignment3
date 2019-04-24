angular.module('crudApp').factory('Person', Person);

Person.$inject = [ '$resource' ];

function Person($resource) {
	var resourceUrl = 'api/person/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}