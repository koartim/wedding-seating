## Problem 2: Wedding Seating
## You work for a wedding planner and are in charge of assigning seating for guests. You are given a list
## of tables (defined by table name - max capacity). You are also given a list of guest parties, along with 
## a number in that party. Also noted is if a party dislikes one or more other parties. If possible, you should
## not seat parties at the same table with a party they dislike. If it is not possible to seat all parties at the same 
## table, the program should return an error.


## 1. Problem: write a function that fills the empty tables with guests that are amiable to one another
## 2. Inputs: a table object, in which each table has a name, and a capacity, a list of parties: each party
## has a name, a size, as well as a list of names being the parties they don't want to sit next to
## 3. Outputs: a list of table objects, each table object contains the table name, and the list of parties seated
## at that table

## Notes on my Solution

## 1.seatGuests(parties, tables): this method contains the core logic for seating guests. I iterate over each table and assign parties 
## to them by adding them to the seatingChart. I tried to optimize the method for large inputs by removing a party from the 
## list of parties once that party has been seated. 

## 2. seatingChart: The seating chart is the return value of seatGuests. In the seatingChart, I use the table name as a key, and the value of that key
## is a list that holds the Party objects seated at that table. While I wasn't able to get the output to match the output in the problem statement, 
## parties are seated in a manner consistent to the constraints. No parties are seated next to anyone they dislike. And all parties are seated at a table.

## 3. checkForDislikes(seatedParties, party, dislikeLookup): this is a helper method I wrote to prevent guests from being 
## seated next to parties they don't like. If any of the seated parties are in the current party's dislike list, we return false
## and that party is not added to that table

## 4. dislikeLookup: to make checking for dislikes efficient, I created a map dislikeLookup that I populate each parties dislikes.
## this allows me to check for party compatibility in constant time, vs having to repeatedly loop through each party's dislike list

## 5. currentCapacity: I used this variable to track the space left at a table. Every time a party is
## seated, I subtract that party's size from the currentCapacity, that way no table is filled past its capacity limit

## 6. Error Handling: I included checks that ensure no party exceeds any individual table's capacity and that total
## guests may not exceed total table capacity. I also included a check to ensure that the inputs for parties and tables 
## are not empty values.

## 7. Unit Testing: I focused on validating the key rules given in the problem statement.
## testSeatingChartSizeMatchesTableSize: ensures the seating chart contains the same number of tables as the input
## testNoPartyExceedsTableCapacity: ensures no table exceeds its total capacity
## testAllPartiesWereSeated: ensures all parties are seated at a table.
## testNoPartiesSeatedNextToDislikes: ensures no party is seated at the same table as someone they dislike.

## Missing Text Input:
## Due to time constraints, I wasn't able to include the text input requirement. I tried to implement it at the very end, but it was
## taking longer than I expected to get it working. I wanted to get this in before Thanksgiving, and I am sitting here
## now about to submit it (11/28/24, 11:29 am). Given a little more time I am sure I would be able to get it to work with a BufferedReader object
## but I really wanted to submit this before the Holiday weekend, so I could not stress about not having submitted this assignment within the 3 day window
## I tried to focus on solving the core of the problem, and getting some fundamental tests in. I apologize for not delivering on this, and
## I hope that you will still find my work acceptable. I will try to refactor the assignment over the break and have it working with text file input

## Thanks for your time!

## Tim Koar
