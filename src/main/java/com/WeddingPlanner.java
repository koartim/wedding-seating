package com;

import java.util.*;

public class WeddingPlanner {
    public Map<String, List<Party>> seatGuests(List<Table> tables, List<Party> parties ) throws IllegalArgumentException {

        // if we have an emppty guestlist, or tablelist, we have nothing to work with
        if (tables.isEmpty() || parties.isEmpty()) {
            throw new IllegalArgumentException("No tables or parties to seat");
        }

        int totalCapacity = 0;
        int totalGuests = 0;

        for (Table table : tables) {
            totalCapacity += table.getCapacity();
        }
        for (Party party : parties) {
            totalGuests += party.getSize();
        }

        // if there are more guests than table capacity available we can't do business
        if (totalCapacity < totalGuests) {
            throw new IllegalArgumentException("total capacity is less than total guests");
        }

        // lookup reference for dislikes
        Map<String, List<String>> dislikeLookup = new HashMap<>();
        for (Party party : parties) {
            dislikeLookup.put(party.getName(), Arrays.asList(party.getDislikes()));
        }

        Map<String, List<Party>> seatingChart = new HashMap<>();

        // instantiate seatingChart, each table name will be a key, will the value being a list of Party objects
        for (Table table : tables) {
            seatingChart.put(table.getName(), new ArrayList<>());
        }

        for (Table table : tables) {
            // I used an iterator here so I could remove a party from the list after that party was seated, resulting in better performance for large inputs
            // because I am removing the parties from the list after seating them I don't have to make another data structure to keep track of what parties are already seated
            Iterator<Party> partyIterator = parties.iterator();
            int currentCapacity = table.getCapacity();
            while (partyIterator.hasNext() && (currentCapacity > 0)) {
                Party party = partyIterator.next();
                boolean seatedPartyIsFriendly = checkForDislikes(seatingChart.get(table.getName()), party, dislikeLookup);
                if ((party.getSize() <= currentCapacity) && seatedPartyIsFriendly) {
                    seatingChart.get(table.getName()).add(party);
                    // decrease the capacity of the current table
                    currentCapacity -= party.getSize();
                    // once a party is seated, I want to remove it from the parties list, so I don't have to iterate over it again
                    partyIterator.remove();
                }
            }
        }
        return seatingChart;
    }

    private boolean checkForDislikes(List<Party> seatedParties, Party party,Map<String, List<String>> dislikeLookup) {
        List<String> dislikedParties = dislikeLookup.get(party.getName());
        for (Party seatedParty : seatedParties) {
            if (dislikedParties.contains(seatedParty.getName())) {
                return false;
            }
        }
        return true;
    }
}
