package no.hvl.dat109.bilutleie.model;

import lombok.Data;

@Data
public class Offer {

    private CarCategory category;
    private Long days;

    public int costPerDay() {
        return CarCategory.costPerDay(this.category);
    }

    public String description() {
        return CarCategory.description(this.category);
    }

    public Offer(CarCategory category) {
        this.category = category;
    }

    public Offer(CarCategory category, Long days) {
        this.category = category;
        this.days = days;
    }
}
