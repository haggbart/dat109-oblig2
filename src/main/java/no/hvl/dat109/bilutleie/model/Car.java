package no.hvl.dat109.bilutleie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Car {

    @Id
    private Long regNumber;

    private Long mileage;

    private String brand;

    private String color;

    @Enumerated(EnumType.STRING)
    private CarCategory category;

    @OneToMany(mappedBy = "car")
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private RentalOffice rentalOffice;

    public Car(Long regNumber, CarCategory category) {
        this.regNumber = regNumber;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Registration number: %d, category: %s, brand: %s, Office: %s",
                regNumber, category, brand, rentalOffice);
    }
}
