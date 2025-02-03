package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AviaSoulsTest {
    Ticket ticket1 = new Ticket(
            "Мск",
            "Екб",
            11_000,
            10_00,
            13_00
    );

    Ticket ticket2 = new Ticket(
            "Мск",
            "Сочи",
            5_000,
            9_00,
            14_00
    );

    Ticket ticket3 = new Ticket(
            "Мск",
            "Екб",
            12_000,
            14_00,
            18_00
    );

    Ticket ticket4 = new Ticket(
            "Мск",
            "Екб",
            9_000,
            6_00,
            8_30
    );

    Ticket ticket5 = new Ticket(
            "Спб",
            "Сочи",
            5_000,
            18_00,
            23_00
    );

    @Test
    public void SortByPrice() {
        Ticket[] expected = {ticket2, ticket4, ticket1, ticket3};
        Ticket[] actual = {ticket1, ticket2, ticket3, ticket4};
        Arrays.sort(actual);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SortWhenPriceEquals() {
        Ticket[] expected = {ticket2, ticket5};
        Ticket[] actual = {ticket2, ticket5};
        Arrays.sort(actual);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchByRouteAndSortByPrice() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);

        Ticket[] expected = new Ticket[]{ticket4, ticket1, ticket3};
        Ticket[] actual = aviaSouls.search("Мск", "Екб");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SortByTimeComparator() {

        TicketTimeComparator comparator = new TicketTimeComparator();

        Assertions.assertEquals(-1, comparator.compare(ticket1, ticket2));
        Assertions.assertEquals(1, comparator.compare(ticket2, ticket1));
        Assertions.assertEquals(0, comparator.compare(ticket2, ticket5));

    }

    @Test
    public void searchAndSortByFlightTime() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = new Ticket[]{ticket4, ticket1, ticket3};
        Ticket[] actual = aviaSouls.searchAndSortBy("Мск", "Екб", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchNoMatches() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] actual = aviaSouls.search("Мск", "Вдк");

        Assertions.assertArrayEquals(new Ticket[0], actual);
    }

    @Test
    public void SearchSingleMatch() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] actual = aviaSouls.search("Мск", "Екб");

        Assertions.assertArrayEquals(new Ticket[]{ticket1, ticket3}, actual);
    }

    @Test
    public void SearchMultipleMatchesSortedByPrice() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);

        Ticket[] actual = aviaSouls.search("Мск", "Екб");
        Ticket[] expected = new Ticket[]{ticket4, ticket1, ticket3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchAndSortByNoMatches() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] actual = aviaSouls.searchAndSortBy("Мск", "Вдк", comparator);

        Assertions.assertArrayEquals(new Ticket[0], actual);
    }

    @Test
    public void SearchAndSortBySingleMatch() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] actual = aviaSouls.searchAndSortBy("Мск", "Екб", comparator);

        Assertions.assertArrayEquals(new Ticket[]{ticket1, ticket3}, actual);
    }

    @Test
    public void SearchAndSortByMultipleMatchesSortedByTime() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] actual = aviaSouls.searchAndSortBy("Мск", "Екб", comparator);
        Ticket[] expected = new Ticket[]{ticket4, ticket1, ticket3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void FindAllWithMultipleTickets() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] actual = aviaSouls.findAll();

        Assertions.assertArrayEquals(new Ticket[]{ticket1, ticket2, ticket3}, actual);
    }

    @Test
    public void SearchAirportFromNotMatching() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] actual = aviaSouls.search("Вдк", "Екб");

        Assertions.assertArrayEquals(new Ticket[0], actual);
    }

    @Test
    public void EqualsSameObject() {

        Assertions.assertEquals(ticket1, ticket1);
    }

    @Test
    public void NullObject() {

        Assertions.assertNotEquals(null, ticket1);
    }

    @Test
    public void EqualsDifferentType() {

        Object other = new Object();

        Assertions.assertNotEquals(ticket1, other);
    }

    @Test
    public void EqualsDifferentValues() {

        Ticket differentTicket = new Ticket(
                "Мск",
                "Кзн",
                15_000,
                16_00,
                19_00
        );

        Assertions.assertNotEquals(ticket1, differentTicket);
    }

    @Test
    public void EqualsSameValues() {

        Ticket sameAsTicket1 = new Ticket(
                "Мск",
                "Екб",
                11_000,
                10_00,
                13_00
        );

        Assertions.assertEquals(ticket1, sameAsTicket1);
    }

    @Test
    public void SameHashCode() {

        Ticket sameAsTicket1 = new Ticket(
                "Мск",
                "Екб",
                11_000,
                10_00,
                13_00
        );

        Assertions.assertEquals(ticket1.hashCode(), sameAsTicket1.hashCode());
    }

    @Test
    public void HashCodeDifferentObjects() {

        Ticket differentTicket = new Ticket(
                "Мск",
                "Кзн",
                15_000,
                16_00,
                19_00
        );

        Assertions.assertNotEquals(ticket1.hashCode(), differentTicket.hashCode());
    }

    @Test
    public void EqualsWithDifferentPrice() {

        Ticket ticket1DifPrice = new Ticket(
                "Мск",
                "Екб",
                12_000,
                10_00,
                13_00
        );

        Assertions.assertNotEquals(ticket1, ticket1DifPrice);
    }

    @Test
    public void EqualsWithDifferentTimeFrom() {

        Ticket ticket1DifTimeFrom = new Ticket(
                "Мск",
                "Екб",
                11_000,
                11_00,
                13_00
        );

        Assertions.assertNotEquals(ticket1, ticket1DifTimeFrom);
    }

    @Test
    public void EqualsWithDifferentTimeTo() {

        Ticket ticket1DifTimeTo = new Ticket(
                "Мск",
                "Екб",
                11_000,
                10_00,
                14_00
        );

        Assertions.assertNotEquals(ticket1, ticket1DifTimeTo);
    }

    @Test
    public void EqualsWithDifferentFrom() {

        Ticket ticket1DifFrom = new Ticket(
                "Спб",
                "Екб",
                11_000,
                10_00,
                13_00
        );

        Assertions.assertNotEquals(ticket1, ticket1DifFrom);
    }

    @Test
    public void EqualsWithDifferentTo() {

        Ticket ticket1DifTo = new Ticket(
                "Мск",
                "Кзн",
                11_000,
                10_00,
                13_00
        );
        Assertions.assertNotEquals(ticket1, ticket1DifTo);
    }
}
