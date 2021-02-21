package no.hvl.dat109.bilutleie.model;

public enum CarCategory {
    A, B, C, D;

    public static int getPrice(CarCategory category) {
        return switch (category) {
            case A -> 2000;
            case B -> 4000;
            case C -> 4500;
            case D -> 5000;
        };
    }
}
