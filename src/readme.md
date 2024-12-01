## Problem 2 Wedding Seating:
 You work for a wedding planner and are in charge of assigning seating for guests. You are given a list
 of tables (defined by table name - max capacity). You are also given a list of guest parties, along with 
 a number in that party. Also noted is if a party dislikes one or more other parties. If possible, you should
 not seat parties at the same table with a party they dislike. If it is not possible to seat all parties at the same 
 table, the program should return an error.


## 1. Problem: write a function that fills the empty tables with guests that are amiable to one another
## Inputs: 
    a table object, in which each table has a name, and a capacity, a list of parties: each party
    has a name, a size, and a list of names being the parties they don't want to sit next to
## Outputs: 
    a list of table objects, each table object contains the table name, and the list of parties seated at that table

## REFACTOR NOTES:

## Why I refactored my old solution:
    I refactored the old solution because The logic relied on iterators, which made it harder to manage and debug. Also I wanted to try a new approach to more closely resemble the expected output in the problem statement.
    I tried a new approach where I would find complementary parties to fill tables efficiently. I sorted the list from greatest to list, sitting the largest parties first, and then trying to extract smaller parties from the end
    of the list to fill up the tables. It's still not perfect, but there is more even distribution of parties across the tables and I think the logic makes more sense and is more readable.
    I also added support for text files as input using a BufferedReader object. This satisifes the requirement which I excluded at first because I didn't think I would have enough time. (sorry about that)

### 1. seatGuests(parties, tables)
    This method contains the core logic for seating guests. I iterate over each table and assign parties to them by adding them to the seatingChart. To optimize for larger inputs, I remove a party from the list of parties once it has been seated.
    If a party is seated at a table, and there are still seats avilable, I look to the end of the list for smaller parties to seat with them and try to fill the remaining space

### 2. seatingChart
    The seatingChart is the return value of seatGuests. It uses the table name as a key, with the value being a list that holds the Party objects seated at that table. While the output does not match the order in the problem statement, the seating respects the constraints: 
    No parties are seated next to anyone they dislike. All parties are seated at a table.

### 3. checkForDislikes(seatedParties, party, dislikeLookup)
    This helper method ensures that no party is seated next to parties they dislike. It checks the dislikeLookup map to verify that none of the already seated parties are in the current party's dislike list. If a conflict is found, the party is not added to the table.

### 4. dislikeLookup
    To make checking for dislikes efficient, I created a map called dislikeLookup. This map stores each party's dislikes and allows compatibility checks in constant time, avoiding repeated iterations through dislike lists.

### 5. currentCapacity
    This variable tracks the remaining capacity at a table. Each time a party is seated, its size is subtracted from currentCapacity, ensuring no table is filled past its capacity.

### 6. Error Handling
    I included checks for the following:
    No individual party exceeds the capacity of any table.
    Total guests do not exceed total table capacity.
    The input lists for `tables` and `parties` are not empty.

### 7. Unit Testing
    I focused on validating the key rules in the problem statement:
    testSeatingChartSizeMatchesTableSize: Ensures the seating chart contains the same number of tables as the input.
    testNoPartyExceedsTableCapacity: Ensures no table exceeds its total capacity.
    testAllPartiesWereSeated: Ensures all parties are seated at a table.
    testNoDislikedPartiesSeatedTogether: Ensures no party is seated at the same table as someone they dislike.

## Thanks for Your Time!

Tim Koar
