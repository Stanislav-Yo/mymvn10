package ru.netology;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Ticket implements Comparable<Ticket> {
    private final String from; // аэропорт откуда
    private final String to; // аэропорт куда
    private final int price; // цена
    private final int timeFrom; // время вылета (по москве)
    private final int timeTo; // время прилёта (по москве)

    public Ticket(String from, String to, int price, int timeFrom, int timeTo) {
        this.from = from;
        this.to = to;
        this.price = price;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    // Вспомогательные методы для корректной работы equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return price == ticket.price && timeFrom == ticket.timeFrom && timeTo == ticket.timeTo && from.equals(ticket.from) && to.equals(ticket.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, price, timeFrom, timeTo);
    }

    @Override
    public int compareTo(Ticket o) {
        if (this.price < o.price) {
            return -1;
        } else if (this.price > o.price) {
            return 1;
        } else {
            return 0;
        }
    }
}
