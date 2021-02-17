package no.hvl.dat109.bilutleie.repositories;

import no.hvl.dat109.bilutleie.model.Person;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    // replace iterator with list
    List<Person> findAll();

    // query creation
    Person findPersonByNameContainsIgnoreCase(String name);
}
