package no.hvl.dat109.bilutleie.model;

public enum CarCategory {
    A, B, C, D;

    public static int costPerDay(CarCategory category) {
        return switch (category) {
            case A -> 485;
            case B -> 701;
            case C -> 836;
            case D -> 676;
        };
    }

    public static String description(CarCategory category) {
        return switch (category) {
            case A -> "Ford Fiesta";
            case B -> "Ford Focus";
            case C -> "Toyota RAV 4x4";
            case D -> "Mercedes-Benz Sprinter";
        };
    }
}
