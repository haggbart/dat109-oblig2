package no.hvl.dat109.bilutleie.repository;

import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findAll();

    @Query("select r from Reservation r where r.pickup = :office and :start < r.endDate and :end > r.startDate")
    List<Reservation> findReservationsByOverlap(@Param("office") RentalOffice office,
                                                @Param("start")LocalDateTime start,
                                                @Param("end") LocalDateTime end);
}
