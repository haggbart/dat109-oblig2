package no.hvl.dat109.bilutleie;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Casual {

    @Test
    void test() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        var now = LocalDateTime.now();
        System.out.println(now.format(formatter));
    }
}
