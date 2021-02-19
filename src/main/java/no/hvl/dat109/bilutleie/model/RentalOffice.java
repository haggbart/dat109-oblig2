package no.hvl.dat109.bilutleie.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class RentalOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "rentalOffice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Car> cars = new HashSet<>();

    public RentalOffice(String phoneNumber, Address address) {
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%s Phone: %s", address, phoneNumber);
    }
}
