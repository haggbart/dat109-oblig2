package no.hvl.dat109.bilutleie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @Enumerated(value = EnumType.STRING)
    private CarCategory carCategory;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private RentalOffice pickup;

    @ManyToOne
    private RentalOffice dropoff;

}
