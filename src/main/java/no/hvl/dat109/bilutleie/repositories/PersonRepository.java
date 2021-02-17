package no.hvl.dat109.bilutleie.repositories;

import no.hvl.dat109.bilutleie.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    // replace iterator with list
    List<Person> findAll();

    // query creation
    Person findPersonByNameContainsIgnoreCase(String name);
}
