package no.hvl.dat109.bilutleie.dto;

import lombok.Data;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
public class ReservationForLocationTimeDto {

    private final static String DATETIME_PATTERN = "dd/MM/yyyy HH:mm";

    @NotNull
    private RentalOffice pickup;

    private RentalOffice dropoff;

    @NotNull(message = "Velg tid for utlevering")
    @DateTimeFormat(pattern = DATETIME_PATTERN)
    private LocalDateTime startDate;

    @NotNull(message = "Velg tid for levering")
    @DateTimeFormat(pattern = DATETIME_PATTERN)
    private LocalDateTime endDate;
}
