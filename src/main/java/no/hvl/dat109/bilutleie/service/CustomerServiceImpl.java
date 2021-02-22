package no.hvl.dat109.bilutleie.service;


import no.hvl.dat109.bilutleie.dto.CustomerForDetailsDto;
import no.hvl.dat109.bilutleie.model.Address;
import no.hvl.dat109.bilutleie.model.Customer;
import no.hvl.dat109.bilutleie.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getPersons() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return save(customer);
    }

    @Override
    public Customer add(CustomerForDetailsDto customerDto) {
        var customer = new Customer();
        mapper.map(customerDto, customer);
        customer.setAddress(new Address(customerDto.getStreet(), customerDto.getZip(), customerDto.getCity()));
        customer = save(customer);
        return customer;
    }
}
