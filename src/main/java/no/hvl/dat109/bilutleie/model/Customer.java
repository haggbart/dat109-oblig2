package no.hvl.dat109.bilutleie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String forename;

    private String surname;

    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy="customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Reservation> reservation = new HashSet<>();

    public Customer(String forename, Address address) {
        this.forename = forename;
        this.address = address;
    }

    public Customer(String forename, String surname, Address address) {
        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("id = %d, %s %s %s", id, forename, surname, email);
    }
}
