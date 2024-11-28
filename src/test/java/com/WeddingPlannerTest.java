package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeddingPlannerTest {

    private WeddingPlanner planner;
    private List<Table> tables = new ArrayList<>();
    private List<Party> parties = new ArrayList<>();
    private Map<String, List<Party>> seatingChart = new HashMap<>();

    @BeforeEach
     void setUp() {
        // common vars
        planner = new WeddingPlanner();
        tables = new ArrayList<>(Arrays.asList(
                new Table("A", 8),
                new Table("B", 6),
                new Table("C", 7),
                new Table("D", 7)
        ));
        parties = new ArrayList<>(Arrays.asList(
                new Party("Hoying",3, new String []{}),
                new Party("Garcia",2, new String []{}),
                new Party("Ownens",6, new String []{"Hoying", "Taylor"}),
                new Party("Smith", 1, new String[]{"Garcia"}),
                new Party("Taylor", 5, new String[]{}),
                new Party("Reese", 7, new String[]{})
        ));
    }

    @Test
    public void testSeatingChartSizeMatchesTableSize() {
        // given setUp vars

        // when
        seatingChart = planner.seatGuests(tables, parties);

        // then
        // assert that the seatingChart is correct, there should only be 4 tables
        assertEquals(4, seatingChart.size());
    }

    @Test
    public void testNoPartyExceedsTableCapacity() {
        // given setup vars

        // when
        seatingChart = planner.seatGuests(tables, parties);

        // then
        for (Table table: tables) {
            List<Party> guests = seatingChart.get(table.getName());

            int totalGuests = 0;
            for (Party party: guests) {
                totalGuests += party.getSize();
            }
            // the total of number of guests should not be greater than capacity of the table
            assertTrue(totalGuests <= table.getCapacity());
        }
    }

    @Test
    public void testAllPartiesWereSeated() {
        // given setup vars

        // when
        seatingChart = planner.seatGuests(tables, new ArrayList<>(parties));

        List<Party> seatedParties = new ArrayList<>();
        for (List<Party> guests : seatingChart.values()) {
            seatedParties.addAll(guests);
        }

        // then, assert seatedParties is the same size as the input list
        assertEquals(parties.size(), seatedParties.size());
        for (Party party: seatedParties) {
            // all parties should be seated at a table
            assertTrue(seatedParties.contains(party));
        }
    }

    @Test
    public void testNoPartiesSeatedNextToDislikes() {
        // given setup vars
        seatingChart = planner.seatGuests(tables, new ArrayList<>(parties));

        // when checking the seating chart
        for (String tableName : seatingChart.keySet()) {
            List<Party> seatedParties = seatingChart.get(tableName);

        // then, for each party at the table, check that they don't dislike any other party at the same table
            for (Party party : seatedParties) {
                for (Party otherParty : seatedParties) {
                    if (!party.equals(otherParty)) { // Skip checking against itself
                        List<String> dislikes = Arrays.asList(party.getDislikes());
                        assertTrue(!dislikes.contains(otherParty.getName()));
                    }
                }
            }
        }
    }
}
