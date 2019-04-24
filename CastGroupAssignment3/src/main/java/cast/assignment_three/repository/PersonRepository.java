package cast.assignment_three.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cast.assignment_three.model.Person;



public interface PersonRepository extends JpaRepository<Person, Integer> {
	Person findByName(String name);
}