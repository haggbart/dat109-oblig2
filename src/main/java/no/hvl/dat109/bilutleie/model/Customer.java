package no.hvl.dat109.bilutleie.model;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Customer {

    @Id
    private String phoneNumber;

    private String forename;

    private String surname;

    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy="customer")
    private Set<Reservation> reservation;
}
