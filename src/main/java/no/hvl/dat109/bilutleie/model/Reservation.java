package no.hvl.dat109.bilutleie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ccn;
    private Integer startMileage;
    private Integer endMilage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus status = ReservationStatus.RESERVED;

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

    public Reservation(LocalDateTime startDate, LocalDateTime endDate, CarCategory carCategory, Customer customer,
                       RentalOffice pickup, RentalOffice dropoff) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.carCategory = carCategory;
        this.customer = customer;
        this.pickup = pickup;
        this.dropoff = dropoff;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", ccn=" + ccn +
                ", startMileage=" + startMileage +
                ", endMilage=" + endMilage +
                ", startDate=" + startDate.format(DateTimeFormatter.ISO_DATE) +
                ", endDate=" + endDate.format(DateTimeFormatter.ISO_DATE) +
                ", status=" + status +
                ", carCategory=" + carCategory +
                '}';
    }
}
