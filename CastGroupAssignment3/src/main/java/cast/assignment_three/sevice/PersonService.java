package cast.assignment_three.sevice;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cast.assignment_three.model.Person;
import cast.assignment_three.repository.PersonRepository;


@Service
public class PersonService {
	private PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person save(Person person) {
		if (person.getId() != null && personRepository.exists(person.getId())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return personRepository.save(person);
	}

	public Person update(Person person) {
		if (person.getId() != null && !personRepository.exists(person.getId())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return personRepository.save(person);
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Person findOne(Integer id) {
		return personRepository.findOne(id);
	}

	public void delete(Integer id) {
		personRepository.delete(id);
	}
}
