package com;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        ArrayList<Table> tables = new ArrayList<>();
        ArrayList<Party> parties = new ArrayList<>();

        // load the file from our resources folder
        InputStream is = Main.class.getResourceAsStream("/wedding-planner-input.txt");

        if (Objects.isNull(is)) {
            System.out.println("file is null");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // flags to tell us if we are reading party or table data
            boolean readingTableData = false;
            boolean readingPartyData = false;

            // currentLine == "tables:" on the first iteration
            String currentLine = br.readLine();
            while((currentLine != null)) {
                // if we encounter any empty lines, set the next line and skip this one
                if (currentLine.isEmpty()) {
                    currentLine = br.readLine();
                    continue;
                }
                if (currentLine.equals("tables:")) {
                    currentLine = br.readLine();
                    readingTableData = true;
                    readingPartyData = false;
                }

                if (currentLine.equals("parties:")) {
                    currentLine = br.readLine();
                    readingPartyData = true;
                    readingTableData = false;
                }

                if (readingTableData) {
                    String[] currentTable = currentLine.split(" ");
                    String tableName = currentTable[0];
                    int tableCapacity = Integer.parseInt(currentTable[1]);
                    Table table = new Table(tableName, tableCapacity);
                    tables.add(table);
                    currentLine = br.readLine();
                }
                if (readingPartyData) {
                    String[] currentParty = currentLine.split(" ");
                    String partyName = currentParty[0];
                    int partySize = Integer.parseInt(currentParty[1]);
                    Party party = new Party(partyName, partySize, new String[]{});
                    // if the currentParty array is longer than 2 indexes, then we know we have a party with dislikes
                    if (currentParty.length > 2) {
                        // get the dislikes, everything past the second index will be a dislike
                        String[] dislikes = Arrays.copyOfRange(currentParty, 2, currentParty.length);
                        party.setDislikes(dislikes);
                    }
                    parties.add(party);
                    currentLine = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        WeddingPlanner planner = new WeddingPlanner();

        try {
            Map<String, List<Party>> seatingChart = planner.seatGuests(tables, parties);
            System.out.println("Seating Chart: " + seatingChart);
        } catch (Exception e) {
            System.out.println("Exception occurred while trying ot seat parties: " + e);
        }
    }
}