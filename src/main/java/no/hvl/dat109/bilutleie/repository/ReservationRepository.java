package no.hvl.dat109.bilutleie.repository;

import no.hvl.dat109.bilutleie.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
