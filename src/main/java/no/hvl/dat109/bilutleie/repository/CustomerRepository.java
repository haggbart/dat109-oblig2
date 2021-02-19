package no.hvl.dat109.bilutleie.repository;

import no.hvl.dat109.bilutleie.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

        List<Customer> findAll();
}
