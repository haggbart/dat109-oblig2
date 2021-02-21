package no.hvl.dat109.bilutleie.service;


import no.hvl.dat109.bilutleie.dto.CustomerForDetailsDto;
import no.hvl.dat109.bilutleie.model.Address;
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

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getPersons() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(CustomerForDetailsDto customerDetails) {
        String forename = customerDetails.getForename();
        String surname = customerDetails.getSurname();
        Address address = new Address(customerDetails.getStreet(), customerDetails.getZip(), customerDetails.getCity());
        return new Customer(forename, surname, address);
    }

}
