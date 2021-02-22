package no.hvl.dat109.bilutleie.dto;

import lombok.Data;
import no.hvl.dat109.bilutleie.model.Offer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CustomerForDetailsDto {

    @NotBlank
    private String forename;

    @NotBlank
    private String surname;

    @Email
    private String email;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotNull
    private Integer zip;

    private Offer offer;
}
