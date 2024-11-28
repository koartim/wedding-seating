package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Table a = new Table("A", 8);
        Table b = new Table("B", 8);
        Table c = new Table("C", 7);
        Table d = new Table("D", 7);

        Party hoying = new Party("Hoying", 3, new String[] {});
        Party garcia = new Party("Garcia", 2, new String[] {});
        Party owens = new Party("Owens", 6, new String[] {"Hoying", "Taylor"});
        Party smith = new Party("Smith", 1, new String[] {"Garcia"});
        Party taylor = new Party("Taylor", 5, new String[] {});
        Party reese = new Party("Reese", 7, new String[] {});

        ArrayList<Party> parties = new ArrayList<>();
        parties.add(hoying);
        parties.add(garcia);
        parties.add(owens);
        parties.add(smith);
        parties.add(taylor);
        parties.add(reese);

        ArrayList<Table> tables = new ArrayList<>();
        tables.add(a);
        tables.add(b);
        tables.add(c);
        tables.add(d);

        WeddingPlanner planner = new WeddingPlanner();

        try {
            Map<String, List<Party>> seatingChart = planner.seatGuests(tables, parties);
            System.out.println("Seating Chart: " + seatingChart);
        } catch (Exception e) {
            System.out.println("Exception occurred while trying ot seat parties: " + e);
        }
    }
}