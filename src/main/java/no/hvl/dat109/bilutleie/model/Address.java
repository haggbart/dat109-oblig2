package no.hvl.dat109.bilutleie.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private Integer zipcode;
    private String city;

    public Address(String street, Integer zipcode, String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    //    @OneToMany(mappedBy = "address")
//    private Set<Address> addresses;
    /*
    create table address
(
    id      serial,
    street  varchar(45),
    zipcode int,
    city    varchar(45),
    constraint address_pk primary key (id)
);
     */
}
