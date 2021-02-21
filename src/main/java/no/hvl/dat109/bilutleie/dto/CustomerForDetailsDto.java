package no.hvl.dat109.bilutleie.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerForDetailsDto {

    @NotBlank
    private String forename;

    private String surname;

    private String email;

//    private Address address;
}
