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

    @Enumerated(EnumType.STRING)
    private CarCategory carCategory;

    @OneToMany(mappedBy = "car")
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private RentalOffice rentalOffice;

    public Car(Long reg, CarCategory cc) {
        regNumber = reg;
        carCategory = cc;
        mileage = 0L;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNumber=" + regNumber +
                ", mileage=" + mileage +
                ", carCategory=" + carCategory +
                ", rentalOffice=" + rentalOffice +
                '}';
    }
}
