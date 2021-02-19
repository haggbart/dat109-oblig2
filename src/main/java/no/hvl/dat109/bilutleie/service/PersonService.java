package no.hvl.dat109.bilutleie.service;

import no.hvl.dat109.bilutleie.model.Person;
import no.hvl.dat109.bilutleie.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService  {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person findFirst(String name) {
        return personRepository.findPersonByNameContainsIgnoreCase(name);
    }
}
