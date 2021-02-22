package no.hvl.dat109.bilutleie.service;

import no.hvl.dat109.bilutleie.dto.CustomerForDetailsDto;
import no.hvl.dat109.bilutleie.model.Customer;

import java.util.List;

public interface CustomerService {
    
    Customer save(Customer customer);

    List<Customer> getPersons();

    Customer createCustomer(Customer customer);

    Customer add(CustomerForDetailsDto customerDto);
}
