package no.hvl.dat109.bilutleie.model;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Customer {

    @Id
    private String phoneNumber;

    private String firstname;

    private String lastname;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy="customer")
    private Set<Reservation> reservation;

}
