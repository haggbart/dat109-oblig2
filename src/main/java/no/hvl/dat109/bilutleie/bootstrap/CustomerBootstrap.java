package no.hvl.dat109.bilutleie.bootstrap;

import no.hvl.dat109.bilutleie.service.CustomerService;

public class CustomerBootstrap {

    private final CustomerService customerService;

    public CustomerBootstrap(CustomerService customerService) {
        this.customerService = customerService;
    }
}
