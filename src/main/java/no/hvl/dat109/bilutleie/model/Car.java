package no.hvl.dat109.bilutleie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String vin;

    private Integer mileage;

    private String brand;

    private String color;

    @Enumerated(EnumType.STRING)
    private CarCategory category;

    @OneToMany(mappedBy = "car")
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private RentalOffice rentalOffice;

    public Car(String vin, CarCategory category) {
        this.vin = vin;
        this.category = category;
    }

    public Car(String vin, Integer mileage, String brand, String color, CarCategory category) {
        this.vin = vin;
        this.mileage = mileage;
        this.brand = brand;
        this.color = color;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Registration number: %s, category: %s, brand: %s, Office: %s",
                vin, category, brand, rentalOffice);
    }
}
