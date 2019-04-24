package cast.assignment_three.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cast.assignment_three.model.Person;
import cast.assignment_three.sevice.PersonService;

@RestController
@RequestMapping("/api")
public class PersonResource {

	private PersonService personService;

	public PersonResource(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = "person", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> getAllPerson() {
		return personService.findAll();
	}

	@RequestMapping(value = "person", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> createPerson(@RequestBody Person person) throws URISyntaxException {
		try {
			Person result = personService.save(person);
			return ResponseEntity.created(new URI("/api/person/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Person>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "person", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws URISyntaxException {
		if (person.getId() == null) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

		try {
			Person result = personService.update(person);

			return ResponseEntity.created(new URI("/api/person/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
		personService.delete(id);

		return ResponseEntity.ok().build();
	}
}
