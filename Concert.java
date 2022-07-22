import java.util.*;
import java.io.*;

public class Concert {
    /**
     * gets a list of people as a file and organizes the data into a linked list
     * @param FileName- file name to extract data from
     * @return linkedList with all the data
     */
    public static LinkedList getFromFile(String FileName) throws FileNotFoundException {
        // variables to use
        String content; // line from file
        File file = new File(FileName); // file
        LinkedList linkedList = new LinkedList(); // linked list to return
        String[] data; // temp string to save data from file
        Scanner scan = new Scanner(file); // scanner to read from file

        // go over file and save data into the linked list
        while (scan.hasNextLine()){
            content = scan.nextLine();
            data = content.split(",");
            linkedList.add(data);
        }

        return linkedList;
    }

    /**
     * gets a list of people as a file and organizes the data into a hash closed table
     * @param file_path- file name
     * @return HashClosed table with the information of the people
     */
    public static HashClosed registerCrowd(String file_path) throws FileNotFoundException{
        //variables
        LinkedList list = getFromFile(file_path); // linked list built from file
        HashClosed table = new HashClosed(list.getSize()); // table to return
        Node temp = list.getHead(); // temp node

        //go over list and add to table
        while (temp != null){
            table.insert(temp.getData());
            temp=temp.getNext();
        }
        return table;
    }

    /**
     * helper function calculate the number of seats in the concert.
     * @param table that represents the concert
     * @return number of seats
     */
    private static int numberOfSeats(HashClosed table){
        int[] sizeOfLists = table.getNodesSize(); // returns the size of each linked list in table pre index respectively
        int sum = 0;

        // go over the list of sizes and sum them up
        for (int sizeOfList : sizeOfLists) {
            sum = sum + sizeOfList;
        }

        return sum;
    }

    /**
     * helper function that organizes people registered that have arrived to one linked list
     * @param people people that have arrived
     * @param registered table of people registered
     * @return linked list of people that have arrived and have a ticket
     */
    private static LinkedList seatsFilled(LinkedList people, HashClosed registered){
        LinkedList seatsTaken = new LinkedList(); // counter
        Node temp = people.getHead();

        // go over table and see if person from list is registered
        while (temp!= null){
            if (registered.search(temp.getData())[0]== 1){seatsTaken.add(temp.getData());}
            temp=temp.getNext();
        }

        return seatsTaken;
    }

    /**
     * helper function that sorts people are not registered according to their ticket:
     *      notRegistered[0] - vip ticket
     *      notRegistered[1] - golden ring ticket
     *      notRegistered[2] - inner ticket
     *      notRegistered[3] - outer ticket
     * @param arrived list of people who have arrived
     * @param registered table of people who have registered
     * @return return array of linked list that have been sorted according to ticket type
     */
    private static LinkedList[] notRegistered(LinkedList arrived, HashClosed registered){
        LinkedList[] notRegistered = new LinkedList[4];
        // initialize linked lists
        for (int i = 0; i<4; i++){notRegistered[i] = new LinkedList();}
        Node temp = arrived.getHead();

        // go over table and see if person from list is registered, if not, add to linked list according to ticket
        while (temp!= null){
            if (registered.search(temp.getData())[0]== 0){ // if they are not in registered table
                String ticket = temp.getData()[3];
                if(Objects.equals(ticket, "VIP")){notRegistered[0].add(temp.getData());}
                else if(Objects.equals(ticket, "GOLDEN_RING")){notRegistered[1].add(temp.getData());}
                else if(Objects.equals(ticket, "INNER_RING")){notRegistered[2].add(temp.getData());}
                else {notRegistered[3].add(temp.getData());}
            }
            temp=temp.getNext();
        }
        return notRegistered;
    }

    /**
     * sell tickets to people that have arrived-> check minimum between number of people ot sell to and empty seats
     * amount of tickets sold is the minimum between them.
     * sell according to tickets value: from the most expensive to the less:
     * VIP-0, GOLDER_RING-1, INNER_RING-2, OUTER_RING-3, being 0 most expensive and 3 the least.
     * @param emptySeats number of tickets to sell
     * @param notRegistered people who have not registered to the concert
     * @param registered people who have registered
     */
    private static void sellTickets(int emptySeats, LinkedList[] notRegistered, LinkedList registered, int seatsTot) {
        int ticketType = 0; // sell according to ticket value
        int min = Math.min(emptySeats,sizeLinkListArray(notRegistered));// check minimum

        for (int i = 0; i < min; i++) {
            if (notRegistered[ticketType].getHead() != null) {
                registered.add(notRegistered[ticketType].getHead().getData());
                notRegistered[ticketType].removeHead();
            } else {
                i--;
                ticketType++;
                if (ticketType == 4) {
                    break;
                }
            }
        }
    }

    /**
     * helper function that sums linked list array
     * @param linkedLists given linked list
     * @return sum of all linked lists in array
     */
    private static int sizeLinkListArray (LinkedList[] linkedLists){
        int sum = 0;
        for (LinkedList linkedList : linkedLists) {
            sum = sum + linkedList.getSize();
        }
        return sum;
    }

    /**
     * helper function that extracts IDs of registered people.
     * @param haveTickets people who are registered to the concert, also people who bought tickets at reception
     * @return array of integers of the IDs.
     */
    private static int[] extractID(LinkedList haveTickets){
        Node temp = haveTickets.getHead();
        LinkedList ids = new LinkedList(); // temp linked list to that final array won't have 0's.

        // save ids of registered people
        while (temp!=null){
            ids.add(temp.getData());
            temp = temp.getNext();
        }

        // crate array of int with ids
        int[] IDs = new int[ids.getSize()];
        Node id = ids.getHead();
        for (int i =0; i< IDs.length; i++){
            IDs[i] = Integer.parseInt(id.getData()[0]);
            id = id.getNext();
        }

        return IDs;
    }

    /**
     * sorts array using insertion sort from the smallest to the largest element
     * @param array given array to sort
     * @return sorted array from the smallest to the largest
     */
    private static int[] insertionSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while(j >= 0 && current < array[j]) {
                array[j+1] = array[j];
                j--;
            }
            // at this point we've exited, so j is either -1, or it's at the first element where current >= a[j]
            array[j+1] = current;
        }
        return array;
    }

    /**
     * function gets a linked list of people that have arrived at the reception and a HashClosed table of people that have
     * registered to the concert.
     * this function checks if there are any empty seats left at the concert, if there are, then the reception sells them
     * and updates the registered table.
     * then takes all the registered IDs, sorts them from the smallest to the largest and returns them.
     * this function uses function helpers:
     *      numberOfSeats - calculates number of seats of the concert = number of registered
     *      seatsFilled - calculated how many claimed their tickets/seats
     *      notRegistered - organizes the non-registered people by ticket type
     *      extractID - save the ID from data of the person into an array
     *      insertionSort- sorts given array from the smallest to the largest
     * @param file_path file path to extract linked list of people who have arrived
     * @param registered HashClosed table that have registered to the concert
     * @return sorted ID array of integers
     */
    public static int[] reception(String file_path, HashClosed registered) throws FileNotFoundException {
        //variables
        int seatsN = numberOfSeats(registered); // number of seats N
        LinkedList arrived = getFromFile(file_path);
        LinkedList haveTicket = seatsFilled(arrived,registered);
        int emptySeats = seatsN - haveTicket.getSize();
        LinkedList[] notRegistered = notRegistered(arrived,registered);

        // sell empty seats by going over lists that are organized according to their ticket
        // every ticket sold is added to the table and removed from notRegistered linked list array
        sellTickets(emptySeats, notRegistered, haveTicket, seatsN);

        return insertionSort(extractID(haveTicket));
    }

    /**
     * returns the average steps of searching a given list of people in HashClosed table
     * @param file_path create a linked list of people to search for from this file
     * @param registered HashClosed table to search for
     * @return average of steps to search for given people in given table
     */
    public static int reception_AverageSteps(String file_path, HashClosed registered) throws FileNotFoundException {
        //variables
        LinkedList crowd = getFromFile(file_path);
        int sumOfSteps = 0;
        Node temp = crowd.getHead();

        //search people from list in registered table
        while (temp!=null){
            int steps = registered.search(temp.getData())[1];
            sumOfSteps = sumOfSteps + steps;
            temp = temp.getNext();
        }
        return  sumOfSteps/crowd.getSize(); // calculation of average
    }

    /**
     * seats people with tickets, according to given hash function.
     * returns statistic of steps to empty seat: (number of taken seats a person visits before seated)
     * index 1 - first half
     * index 2 - first three quarters
     * index 3 -  first (seats minus sqrt)
     * index 4 - remaining, after selected people in index 3
     * @param sortedCrowed list of ids that represent the people that have a ticket
     * @param registered table of seats in concert - sum of people in table is the number of seats in total
     * @param functionNum which has function to use to seat the people
     * @return return statistics as described before
     */
    public static int[] seatingArrangement(int[] sortedCrowed, HashClosed registered, int functionNum){
        int seats = numberOfSeats(registered);
        int people = sortedCrowed.length;
        HashOpen concert = new HashOpen(seats);
        int N1 = 0, N2 = 0,N3 = 0, N4 = 0; // sum of steps
        int steps; // number of taken seats a person visits before seated
        int square = (int) Math.sqrt(people);
        int Xfirst = people - square; // help determine X first people to sum for index 3

        // place each person in their seat and calculate the sum of N[i] accordingly
        for (int i = 0; i < people; i++){
            steps = concert.insert(sortedCrowed[i],functionNum);
            if (i < people/2){ N1 = N1 + steps;}
            if (i < (3*people)/4){N2 = N2 + steps;}
            if (i < Xfirst){N3 = N3 + steps;}
            if(i >= Xfirst) { N4 = N4 + steps;}
        }
        return new int[]{N1, N2, N3, N4};


    }
}

