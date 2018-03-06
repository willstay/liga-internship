package ru.liga.fromuml.domain;

import java.math.BigDecimal;

public abstract class Room {
    private double length;
    private double width;

    public double area() {
        return length * width;
    }

    public abstract BigDecimal repairPrice();

}
