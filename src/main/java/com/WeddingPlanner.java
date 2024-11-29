package com;

import java.util.*;

public class WeddingPlanner {
    public Map<String, List<Party>> seatGuests(List<Table> tables, List<Party> parties ) throws IllegalArgumentException {

        // if we have an empty guestlist, or tablelist, we have nothing to work with
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

        for (Table table : tables) {
            seatingChart.put(table.getName(), new ArrayList<>());
        }
        // sort the parties by size, greatest to least
        parties.sort((p1, p2) -> Integer.compare(p2.getSize(), p1.getSize()));

        for (Table table : tables) {
            int currentCapacity = table.getCapacity();
            int i = 0;
            while (i < parties.size() && currentCapacity > 0) {
                System.out.println("parties " + parties);
                // get the current party
                Party party = parties.get(i);
                // get the parties currently seated at the current table
                List<Party> seatedParties = seatingChart.get(table.getName());
                // check if those parties are compatible with the current party, and the partySize fits at the table
                if (checkForDislikes(seatedParties, party,dislikeLookup)
                        && (party.getSize() <= currentCapacity)) {
                    // if the party is compatible and can fit at the table, seat them
                    seatedParties.add(party);
                    currentCapacity -= party.getSize(); // decrease the currentCapacity by the party size
                    // remove the current party now that we have seated them
                    parties.remove(i);
                } else {
                    // if this party is not compatible, move to the next party
                    i++;
                }
                // if we still have room at the table, and our list is not empty yet, check for a complement party
                boolean foundComplement = false; // tracks if a complement party is found
                while (currentCapacity > 0 && !parties.isEmpty()) {
                    // since we have sorted the list in descending order by size,
                    // the largest parties will take up the most space, look to the end of the list for smaller party to fill the remaining seats
                    Party lastParty = parties.get(parties.size() - 1);
                    // check to see if the currently seated parties are compatible with this party, and if this party can fit at the remaining seats in the table
                    if (checkForDislikes(seatedParties, lastParty, dislikeLookup)
                            && (lastParty.getSize() <= currentCapacity)) {
                        // add the party to the table, and update the current capacity again, then remove the newly added party
                        seatedParties.add(lastParty);
                        currentCapacity -= lastParty.getSize();
                        parties.remove(parties.size() - 1);
                        foundComplement = true;
                    } else {
                        break; // exit the inner loop if no suitable party is found
                    }
                }
                // if we can't find another compatible party, and there is still room at the table, break out of the outer loop
                // without this we enter an infinite loop because currentCapacity won't be decremented futher
                if (!foundComplement && currentCapacity > 0) {
                    break;
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
