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

    @OneToMany(mappedBy = "rentalOffice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Car> cars = new HashSet<>();

    public RentalOffice(Address add, String pn) {
        address = add; phoneNumber = pn;
    }

    /*
    create table rentaloffice
(
    id          serial,
    address     int,
    phonenumber varchar(45),
    constraint rentaloffice_pk primary key (id),
    constraint address_fk foreign key (address) references address
);
     */

    @Override
    public String toString() {
        return "RentalOffice{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }
}
