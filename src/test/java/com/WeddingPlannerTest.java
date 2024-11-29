package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeddingPlannerTest {

    private WeddingPlanner planner;
    private List<Table> tables;
    private List<Party> parties;

    @BeforeEach
    void setUp() {
        // Common setup variables
        planner = new WeddingPlanner();
        tables = new ArrayList<>(Arrays.asList(
                new Table("A", 8),
                new Table("B", 8),
                new Table("C", 7),
                new Table("D", 7)
        ));
        parties = new ArrayList<>(Arrays.asList(
                new Party("Hoying", 3, new String[]{}),
                new Party("Garcia", 2, new String[]{}),
                new Party("Owens", 6, new String[]{"Hoying", "Taylor"}),
                new Party("Smith", 1, new String[]{"Garcia"}),
                new Party("Taylor", 5, new String[]{}),
                new Party("Reese", 7, new String[]{})
        ));
    }

    @Test
    public void testSeatingChartSizeMatchesTableSize() {
        // Given setup vars

        // When
        Map<String, List<Party>> seatingChart = planner.seatGuests(tables, parties);

        // Then
        // Assert that the seatingChart contains exactly 4 entries (one for each table)
        assertEquals(4, seatingChart.size());
    }

    @Test
    public void testNoPartyExceedsTableCapacity() {
        // Given setup vars

        // When
        Map<String, List<Party>> seatingChart = planner.seatGuests(tables, parties);

        // Then
        for (Table table : tables) {
            List<Party> guestsAtTable = seatingChart.get(table.getName());
            int totalGuests = 0;

            // Calculate total guests seated at the current table
            for (Party party : guestsAtTable) {
                totalGuests += party.getSize();
            }

            // Assert that the total guests seated do not exceed the table's capacity
            assertTrue(totalGuests <= table.getCapacity());
        }
    }

    @Test
    public void testAllPartiesWereSeated() {
        // Given setup vars

        // When
        Map<String, List<Party>> seatingChart = planner.seatGuests(tables, new ArrayList<>(parties));

        // Then
        // Collect all seated parties
        List<Party> seatedParties = new ArrayList<>();
        for (List<Party> tableParties : seatingChart.values()) {
            seatedParties.addAll(tableParties);
        }

        // Assert that all parties are seated
        assertEquals(parties.size(), seatedParties.size());
        for (Party party : parties) {
            assertTrue(seatedParties.contains(party));
        }
    }

    @Test
    public void testNoDislikedPartiesSeatedTogether() {
        // Given setup vars

        // When
        Map<String, List<Party>> seatingChart = planner.seatGuests(tables, new ArrayList<>(parties));

        // Then
        for (Table table : tables) {
            List<Party> seatedParties = seatingChart.get(table.getName());

            // Check that no seated party dislikes any other party at the same table
            for (Party seatedParty : seatedParties) {
                List<String> dislikes = Arrays.asList(seatedParty.getDislikes());
                for (Party otherParty : seatedParties) {
                    if (!seatedParty.equals(otherParty)) {
                        assertTrue(!dislikes.contains(otherParty.getName()));
                    }
                }
            }
        }
    }
}
