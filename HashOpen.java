public class HashOpen {
    private final int[] table;
    private final int sizeTable;
    private int elements;

    /**
     * Constructor
     * @param m gets m and sets it as size of hash table
     */
    public HashOpen(int m){
        sizeTable = m;
        table = new int[sizeTable];
        elements = 0;
    }

    /**
     * helper function - hash function 1
     * h1(k)= k % N
     * N - size of table
     * @param id use as k in h1(k)
     * @return return cell number to insert id in table
     */
    private int HashFunction1(int id){
        return id % sizeTable;
    }

    /**
     * helper function - hash function 2
     * first reverse id then use has function 1
     * @param id given id to find cell in table
     * @return cell to insert id
     */
    private int HashFunction2(int id){
        int reversedID = 0;

        // reverse id
        for(;id != 0; id /= 10) {
            int digit = id % 10;
            reversedID = reversedID * 10 + digit;
        }

        return HashFunction1(reversedID);

    }

    /**
     * helper function that checks if seat is empty
     * @param cell seat number
     * @return true if empty, false if not
     */
    private boolean emptySeat(int cell){return table[cell] == 0;}

    /**
     * adds id to table according to cell determined by has function.
     * @param id to insert to table
     * @param hashFunc which has function to use, 1 or 2.
     */
    public int insert(int id, int hashFunc) {
        elements++;
        int chairs = 0; // taken seats passed
        int cell;

        //use has function required to set cell number
        if (hashFunc == 1) {cell = HashFunction1(id);}
        else {cell = HashFunction2(id);}

        // check to see if seat is empty
        if (emptySeat(cell)){
            table[cell] = id;
            return chairs;
        }

        // if seat is not empty
        int steps = 1;
        chairs++;
        while (chairs < sizeTable){ // check if all seats are taken

            // check with + steps
            if (cell + steps < sizeTable) { // check if index is out of tables limit
                if (emptySeat(cell + steps)) {
                    cell = cell + steps;
                    break;
                }
            }
            else {chairs --;}
            // check with - steps
            if(cell - steps >= 0 ){ // check if index is out of tables limit
                if (emptySeat(cell - steps)) {
                    chairs++;
                    cell = cell - steps;
                    break;
                }
            }
            else {chairs --;}

            //if none are empty then continue to next step
            steps++;
            chairs = chairs+2;
        }

        table[cell] = id;

        return chairs;
    }

    /**
     *returns number of cells that have elements
     * @return elements
     */
    public int getNumberElements(){return elements;}

    /**
     *
     * @return table size
     */
    public int getSize(){return sizeTable;}
}
