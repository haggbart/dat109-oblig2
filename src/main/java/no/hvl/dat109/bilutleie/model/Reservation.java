package no.hvl.dat109.bilutleie.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long ccn;
    private long startMileage;
    private long endMilage;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;


    public Reservation(long ccn,Customer customer, Date startDate, Date endDate, Car car) {

        this.ccn = ccn;
        this.startMileage = car.getMileage();
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;

    }
}
