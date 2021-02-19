package no.hvl.dat109.bilutleie.service;


import no.hvl.dat109.bilutleie.model.Customer;
import no.hvl.dat109.bilutleie.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getPersons() {
        return customerRepository.findAll();
    }

}
